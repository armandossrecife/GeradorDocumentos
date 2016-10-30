/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.tools;

import dados.DAO.DAO;
import dados.DAO.DicionarioDAO;
import visao.view.JTable.CustomRenderer;
import visao.view.JTable.CustomRendererTableDicionario;
import controle.dicionario.Dicionario;
import entity.Atributo;
import entity.CasoDeUso;
import entity.Projeto;
import entity.Tabela;
import entity.Conceito;
import entity.Isr;
import entity.tools.TipoTabela;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import controle.nGramas.NGrama;
import visao.view.tools.Constante;
import view.Editor;

/**
 *
 * @author helcio.soares
 */
public class DicionarioView extends javax.swing.JDialog {

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;
    private Dicionario dicionario;
    private Projeto projetoSelecionado;
    private JMenu copiarPara;
    private JMenuItem copiarParaConceito;
    private JMenuItem copiarParaFuncionalidade;
    private JMenuItem copiarParaUsuario;
    private JMenuItem copiarParaInterfaceUsuario;
    private JMenuItem copiarParaRequisitosDeArmazenamento;
    private JMenuItem copiarParaCasoDeUSo;
    private JMenu copiarParaAtributo;
    private JMenuItem excluir;
    private JTable tabelaSelecionada;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private int numeroPrimeiraLinha = -1;
    private SinonimoView sinonimoView;
    private HashMap<String, JTable> hashDeTabelas = new HashMap<String, JTable>();
    private HashMap<Integer, List<Conceito>> hashDeListas = new HashMap<Integer, List<Conceito>>();
    private ActionListener anActionListener;

    /**
     * Creates new form DicionarioView
     */
    public DicionarioView(java.awt.Frame parent, boolean modal, Projeto projetoSelecionado1) {
        super(parent, modal);
        this.projetoSelecionado = projetoSelecionado1;
        dicionario = new Dicionario();
        initComponents();
        // Close the dialog when Esc is pressed

        Color bg = jTConceitoComplexos.getSelectionBackground();
        CustomRendererTableDicionario cr = new CustomRendererTableDicionario(jTConceito.getDefaultRenderer(Object.class), bg);
        jTConceito.setDefaultRenderer(Object.class, cr);

        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        anActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int[] linha = tabelaSelecionada.getSelectedRows();
                int coluna = tabelaSelecionada.getSelectedColumn();
                JMenuItem j = (JMenuItem) e.getSource();

                for (int idx = 0; idx < linha.length; idx++) {
                    String sConceito = (String) tabelaSelecionada.getModel().getValueAt(linha[idx], coluna);
                    Conceito conceito = dicionarioDAO.recuperarConceitoPorNome(sConceito, projetoSelecionado.getId());
                    try {
                        dicionario.gravarTabela(conceito, projetoSelecionado, j);
                        int n = conceito.getUtilizado();
                        conceito.setUtilizado(n++);
                        dicionario.conceitoDAO.atualiza(conceito);
                    } catch (Exception ex) {
                        Logger.getLogger(DicionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        carregarTabelas();
        construirPopupMenu(this.projetoSelecionado);
        hashDeTabelas.put(jTFuncionalidade.getName(), jTFuncionalidade);
        hashDeTabelas.put(jTConceito.getName(), jTConceito);
        hashDeTabelas.put(jTConceitoComplexos.getName(), jTConceitoComplexos);
        hashDeTabelas.put(jTVerbo.getName(), jTVerbo);
        hashDeTabelas.put(jTClassificacao.getName(), jTClassificacao);
//        hashDeTabelas.put(jTSigla.getName(), jTSigla);
    }

    public Projeto getProjetoSelecionado() {
        return projetoSelecionado;
    }

    public void setProjetoSelecionado(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenu1 = new javax.swing.JMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTConceito = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTVerbo = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTConceitoComplexos = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTFuncionalidade = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTClassificacao = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTIrs = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTAtributo = new javax.swing.JTable();

        jMenu1.setText("jMenu1");

        setTitle("Lexico do domínio");
        setModal(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTConceito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Conceito"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTConceito.setName("101"); // NOI18N
        jTConceito.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTConceito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTConceitoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTConceito);
        jTConceito.getAccessibleContext().setAccessibleName("101");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add-book-icone-5659-32.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setIconTextGap(0);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sinonimos.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setIconTextGap(0);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jTVerbo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ações"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTVerbo.setName("103"); // NOI18N
        jTVerbo.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTVerbo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTConceitoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTVerbo);

        jTConceitoComplexos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Conceitos compostos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTConceitoComplexos.setName("102"); // NOI18N
        jTConceitoComplexos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTConceitoComplexos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTConceitoMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTConceitoComplexos);

        jTFuncionalidade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Funcionalidades"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTFuncionalidade.setName("105"); // NOI18N
        jTFuncionalidade.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTFuncionalidade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTConceitoMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTFuncionalidade);

        jTClassificacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classificação"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTClassificacao.setName("200"); // NOI18N
        jTClassificacao.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTClassificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTClassificacaoMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTClassificacao);

        jTIrs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rec. Armazenamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTIrs.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {

                DefaultTableModel dtm = (DefaultTableModel) jTIrs.getModel();

                int selected = jTIrs.getSelectedRow();
                if (selected > -1){
                    String nomeIsr = (String) dtm.getValueAt(selected,0);
                    String lemma = null;
                    try {
                        lemma = Constante.recuperarLemmaDaPalavra(nomeIsr);
                    } catch (Exception ex) {
                        Logger.getLogger(DicionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Isr isr = dicionarioDAO.isrDAO.buscaPorNomeLemma(lemma, projetoSelecionado.getId());
                    List<Atributo> listaAtributos = dicionarioDAO.carregarAtributosPorIrs(projetoSelecionado.getId(),isr);

                    dtm = (DefaultTableModel) jTAtributo.getModel();
                    dtm.setNumRows(0);
                    for (Atributo atributo : listaAtributos) {
                        dtm.addRow(new Object[]{atributo.getNome()});
                    }
                }
            }
        });
        jScrollPane2.setViewportView(jTIrs);

        jTAtributo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dados armazenados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTAtributo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            construirDicionario(projetoSelecionado);
        } catch (Exception ex) {
            Logger.getLogger(DicionarioView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void jTConceitoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTConceitoMouseClicked
        // TODO add your handling code here:
        tabelaSelecionada = (JTable) evt.getComponent();
        int i = tabelaSelecionada.getSelectedColumn();
        int[] linhas = tabelaSelecionada.getSelectedRows();
        clearSelection(tabelaSelecionada.getName());
        if (evt.getButton() == MouseEvent.BUTTON3 && i >= 0) {

            habilitarOpcoes();
            jPopupMenu1.show(tabelaSelecionada, evt.getX(), evt.getY());
        }
        carregarIrs();
        carregaClassificacao();
    }//GEN-LAST:event_jTConceitoMouseClicked

    private void habilitarOpcoes() {
        copiarPara.removeAll();
        copiarPara.add(copiarParaConceito);

        if (tabelaSelecionada == jTConceito || tabelaSelecionada == jTConceitoComplexos) {
            copiarPara.add(copiarParaUsuario);
            copiarPara.add(copiarParaInterfaceUsuario);
            copiarPara.add(copiarParaRequisitosDeArmazenamento);
            copiarPara.add(copiarParaAtributo);
            montarPopUpIsr();
        } else {
            copiarParaAtributo.setEnabled(false);
        }

        if (tabelaSelecionada == jTFuncionalidade) {
            copiarPara.add(copiarParaInterfaceUsuario);
            copiarPara.add(copiarParaFuncionalidade);
            copiarPara.add(copiarParaCasoDeUSo);
        }

    }

    private void montarPopUpIsr() {
        DAO<Isr> irsDAO = new DAO(Isr.class);
        List<Isr> listIsr = irsDAO.filtraPorProjeto(projetoSelecionado);
        //copiarParaAtributo.getI
        for (Isr isr : listIsr) {
            int i = 0;
            boolean achou = false;
            while (i <= copiarParaAtributo.getItemCount() - 1 && achou == false) {
                JMenuItem jMenuItem1 = copiarParaAtributo.getItem(i);
                achou = isr.getNome().equals(jMenuItem1.getText());
                i++;
            }
            if (!achou) {
                JMenuItem jMenuItem = new JMenuItem(isr.getNome());
                copiarParaAtributo.add(jMenuItem);
                jMenuItem.setDisplayedMnemonicIndex(0);
                jMenuItem.addActionListener(anActionListener);
            }
        }
        copiarParaAtributo.setEnabled(!listIsr.isEmpty());
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        sinonimoView = new SinonimoView(this, rootPaneCheckingEnabled, projetoSelecionado);
        sinonimoView.setProjetoSelecionado(projetoSelecionado);
        sinonimoView.setLocationRelativeTo(null);
        sinonimoView.setVisible(true);
        carregarTabelas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTClassificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTClassificacaoMouseClicked
        tabelaSelecionada = jTClassificacao;
        clearSelection(tabelaSelecionada.getName());
        selecionarConceitosClassificados(tabelaSelecionada.getSelectedRow());
    }//GEN-LAST:event_jTClassificacaoMouseClicked

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTAtributo;
    private javax.swing.JTable jTClassificacao;
    private javax.swing.JTable jTConceito;
    private javax.swing.JTable jTConceitoComplexos;
    private javax.swing.JTable jTFuncionalidade;
    private javax.swing.JTable jTIrs;
    private javax.swing.JTable jTVerbo;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables

    private int returnStatus = RET_CANCEL;

    private void construirDicionario(Projeto idProjeto) throws Exception {
        try {
            dicionario.construirDicionario(idProjeto);
            carregarTabelas();
        } catch (IOException ex) {
            Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void adicionaJtable(JTable tabela, List<Conceito> listaOrdenada) {

        DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
        dtm = (DefaultTableModel) tabela.getModel();
        dtm.setNumRows(0);
        for (Conceito conceitoOrdenado : listaOrdenada) {
            dtm.addRow(new Object[]{conceitoOrdenado.getDe(), ""});
        }
    }

    private void carregarTabelas() {
        List<Conceito> lista = dicionarioDAO.carregarConceitos(Constante.CONCEITO_SIMPLES, this.projetoSelecionado.getId());
        DefaultTableModel dtm = (DefaultTableModel) jTConceito.getModel();
        dtm.setNumRows(0);
        int i = 1;
        for (Conceito coneito : lista) {
            dtm.addRow(new Object[]{coneito.getDe(), ""});
            i++;
        }
        hashDeListas.put(101, lista);

        lista = dicionarioDAO.carregarConceitos(Constante.CONCEITO_COMPOSTO, this.projetoSelecionado.getId());
        adicionaJtable(jTConceitoComplexos, lista);
        hashDeListas.put(102, lista);

        lista = dicionarioDAO.carregarConceitos(Constante.ACAO, this.projetoSelecionado.getId());
        dtm = (DefaultTableModel) jTVerbo.getModel();
        dtm.setNumRows(0);
        for (Conceito verbo : lista) {
            dtm.addRow(new Object[]{verbo.getDe(), ""});
        }
        hashDeListas.put(103, lista);

        lista = dicionarioDAO.carregarConceitos(Constante.FUNCIONALIDADE, this.projetoSelecionado.getId());
        adicionaJtable(jTFuncionalidade, lista);
        hashDeListas.put(105, lista);

        dtm = (DefaultTableModel) jTClassificacao.getModel();
        dtm.setNumRows(0);
        for (int l = 1; l <= 7; l++) {
            dtm.addRow(new Object[]{Constante.hashTipoTabela.get(l).getDe()});
        }
        carregarIrs();
//        dtm = (DefaultTableModel) jTSigla.getModel();
//        dtm.setNumRows(0);
//        lista = dicionarioDAO.carregarConceitos(Constante.SIGLA, this.projetoSelecionado.getId());
//        for (Conceito siglaBanco : lista) {
//            String sigla = siglaBanco.getDe().substring(0, siglaBanco.getDe().indexOf("#"));
//            String descricao = siglaBanco.getDe().substring(siglaBanco.getDe().indexOf("#") + 1, siglaBanco.getDe().length());
//            dtm.addRow(new Object[]{sigla, descricao});
//        }
//        hashDeListas.put(104, lista);
    }

    private void carregarIrs() {
        List<Isr> listaIsr = dicionarioDAO.isrDAO.filtraPorProjeto(projetoSelecionado);

        DefaultTableModel dtm = (DefaultTableModel) jTIrs.getModel();
        dtm.setNumRows(0);
        for (Isr isr : listaIsr) {
            dtm.addRow(new Object[]{isr.getNome()});
        }
    }

    public void construirPopupMenu(Projeto projetoSelecionadoSelecionado) {

        copiarPara = new JMenu("Classificar");
        copiarParaConceito = new JMenuItem("Definições e Siglas");
        copiarParaUsuario = new JMenuItem("Atores e sist. externos");
        copiarParaInterfaceUsuario = new JMenuItem("Interfaces de usuário");
        copiarParaRequisitosDeArmazenamento = new JMenuItem("Requisitos de armazenamento");
        copiarParaAtributo = new JMenu("Dado armazenado");
        copiarParaFuncionalidade = new JMenuItem("Funcionalidades");
        copiarParaCasoDeUSo = new JMenuItem("Casos de uso");

        copiarParaConceito.setDisplayedMnemonicIndex(Constante.CONCEITO);
        copiarParaFuncionalidade.setDisplayedMnemonicIndex(Constante.FUNCIONALIDADE - 100);
        copiarParaUsuario.setDisplayedMnemonicIndex(Constante.USUARIO);
        copiarParaInterfaceUsuario.setDisplayedMnemonicIndex(Constante.INTERFACE_USUARIO);
        copiarParaRequisitosDeArmazenamento.setDisplayedMnemonicIndex(Constante.REQUISITOS_DE_ARMAZENAMENTO);
        copiarParaCasoDeUSo.setDisplayedMnemonicIndex(Constante.CASO_DE_USO);

        copiarParaConceito.addActionListener(anActionListener);
        copiarParaFuncionalidade.addActionListener(anActionListener);
        copiarParaUsuario.addActionListener(anActionListener);
        copiarParaInterfaceUsuario.addActionListener(anActionListener);
        copiarParaRequisitosDeArmazenamento.addActionListener(anActionListener);
        copiarParaCasoDeUSo.addActionListener(anActionListener);

        excluir = new JMenuItem("Excluir");
        jPopupMenu1.add(copiarPara);
        jPopupMenu1.add(excluir);

    }

    public void carregaClassificacao() {

        int[] linha = tabelaSelecionada.getSelectedRows();
        int coluna = tabelaSelecionada.getSelectedColumn();
        ListSelectionModel selectionModel = jTClassificacao.getSelectionModel();

        if (linha.length == 1) {
            String sConceito = (String) tabelaSelecionada.getModel().getValueAt(linha[0], coluna);
            Conceito conceito = dicionarioDAO.recuperarConceitoPorNome(sConceito, projetoSelecionado.getId());

            List<Integer> listaTiposRecuperados = dicionarioDAO.recuperarTipoPorNomeLemma(conceito.getNomeLemma(), projetoSelecionado.getId());

            for (int i = 0; i <= listaTiposRecuperados.size() - 1; i++) {
                int j = listaTiposRecuperados.get(i);
                if (j == 0) {
                    selectionModel.setSelectionInterval(j - 1, j - 1);
                } else if (j == Constante.ATRIBUTO) {
                    selectionModel.addSelectionInterval(j - 5, j - 5);
                } else if (j == Constante.CASO_DE_USO) {
                    selectionModel.addSelectionInterval(j-1, j-1);
                } else if (j >= 0) {
                    selectionModel.addSelectionInterval(j - 1, j - 1);
                }
            }
        }
    }

    private void selecionarConceitosClassificados(int linhaTabelaClassificacao) {
        List<String> listaTabelasPorTipo = dicionarioDAO.carregarTabelaPorTipoComoString(linhaTabelaClassificacao + 1, projetoSelecionado.getId(), "nomeLemma");
        switch (linhaTabelaClassificacao) {
            case 3:
                List<Isr> listIsr = dicionarioDAO.isrDAO.filtraPorProjeto(projetoSelecionado);
                for (Isr isr : listIsr) {
                    if (!listaTabelasPorTipo.contains(isr.getNomeLemma())) {
                        listaTabelasPorTipo.add(isr.getNomeLemma());
                    }
                }
                break;

            case 5:
                List<CasoDeUso> listCasoDeUso = dicionarioDAO.casoDeUsoDAO.filtraPorProjeto(projetoSelecionado);
                for (CasoDeUso casoDeUso : listCasoDeUso) {
                    if (!listaTabelasPorTipo.contains(casoDeUso.getNomeLemma())) {
                        listaTabelasPorTipo.add(casoDeUso.getNomeLemma());
                    }
                }
                break;
            case 6:
                List<Atributo> listAtributo = dicionarioDAO.atributoDAO.filtraPorProjeto(projetoSelecionado);
                for (Atributo atributo : listAtributo) {
                    if (!listaTabelasPorTipo.contains(atributo.getNomeLemma())) {
                        listaTabelasPorTipo.add(atributo.getNomeLemma());
                    }
                }
                break;
        }

        int[] n = {0, 0, 0, 0, 0, 0, 0, 0};
        for (String lemma : listaTabelasPorTipo) {
            Conceito c = dicionarioDAO.recuperarConceitoPorLemmaNome(lemma, projetoSelecionado.getId());
            if (c != null) {
                int tipoTabela = linhaTabelaClassificacao + 1;
                ListSelectionModel selectionModel = hashDeTabelas.get(c.getIdTipoConceito().toString()).getSelectionModel();
                List<Conceito> listaDeConceitos = hashDeListas.get(c.getIdTipoConceito());
                for (int i = 0; i <= listaDeConceitos.size() - 1; i++) {
                    Conceito cLista = listaDeConceitos.get(i);
                    if (lemma.equals(cLista.getNomeLemma())) {
                        if (n[tipoTabela] == 0) {
                            selectionModel.setSelectionInterval(i, i);
                        } else {
                            selectionModel.addSelectionInterval(i, i);
                        }
                        n[tipoTabela] = n[tipoTabela] + 1;
                        break;
                    }
                }
            }
        }

    }

    private void clearSelection(String nomeTabela) {
        if (!nomeTabela.equals(jTClassificacao.getName())) {
            jTClassificacao.clearSelection();
        }
        if (!nomeTabela.equals(jTConceito.getName())) {
            jTConceito.clearSelection();
        }
        if (!nomeTabela.equals(jTConceitoComplexos.getName())) {
            jTConceitoComplexos.clearSelection();
        }
        if (!nomeTabela.equals(jTFuncionalidade.getName())) {
            jTFuncionalidade.clearSelection();
        }
//        if (!nomeTabela.equals(jTSigla.getName())) {
//            jTSigla.clearSelection();
//        }
        if (!nomeTabela.equals(jTVerbo.getName())) {
            jTVerbo.clearSelection();
        }
    }
}
