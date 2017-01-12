/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.view;

import dados.DAO.CriarBanco;
import visao.tools.IJframe;
import dados.DAO.DAO;
import controle.dicionario.Dicionario;
import entidades.entity.Projeto;
import visao.view.SRSJTree.TreeExplorarCellRenderer;
import visao.view.SRSJTree.TreeMenuCellRender;
import visao.view.SRSJTree.TreeProblemaCellRenderer;
import visao.view.SRSJTree.TreeProblemasNode;
import java.awt.Dimension;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.KeyCode.T;
import javax.swing.DropMode;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import visao.view.tools.Constante;
import visao.view.SRSJTree.TreeTransferHandler;
import controle.analiseTexto.ClassificadorAtributo;
import controle.documento.MakeDocSRS;
import entidades.entity.Atributo;
import entidades.entity.Conceito;
import entidades.entity.Tabela;
import controle.importar.Importador;
import static controle.intellisense.ParserDescricaoCasoDeUso.novaFrase;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import static visao.view.tools.Constante.iniciaTipos;

import visao.tools.DicionarioView;

/**
 *
 * @author helcio.soares
 */
public class Editor extends javax.swing.JFrame implements TreeSelectionListener {

    private HashMap<String, Class> menuItems = new HashMap<String, Class>();
    private ProjetoView telaProjeto;
    private DicionarioView dicionarioView;
    public Projeto projetoSelecionado;
    public IJframe iFrameAtivo; 
    public static Color bg;
    private JMenuItem classificarComoAtributo;
    private JMenuItem naoClassificarComoAtributo;
    private JMenu classificarOrigemAtributo;
    private DefaultMutableTreeNode nodeAtribute;
    private ClassificadorAtributo classificadorAtributo;
    private static JLabel titulo = new JLabel();
    private JFrame frame;
    public static Logger logger = Logger.getLogger("SRSEDITOR.log");
    /**
     * Creates new form Editor
     */
    public Editor() throws IOException, ClassNotFoundException {
        Constante.iniciaTipos();
        CriarBanco cb = new CriarBanco();
        cb.criarBanco();
        telaProjeto = new ProjetoView(this, rootPaneCheckingEnabled);
        initComponents();
        //buttonValidar.setEnabled(false);
        //buttonvalidacaoSimples.setEnabled(false);
        

        // jSplitPane3.getRightComponent().setPreferredSize(new Dimension(100, 700));
        jSplitPane2.setEnabled(false);
        jSplitPane2.setDividerSize(0);
        jSplitPane4.setDividerLocation(180);
        jSplitPane4.setEnabled(false);
        jSplitPane4.setDividerSize(0);
        this.pack();
        //jSplitPane3.setEnabled(false);
        // jSplitPane3.setDividerSize(0);
        menuItems.put("Objetivo/Escopo", IntroducaoView.class);
        menuItems.put("Referências", ReferenciaView.class);
        menuItems.put("Definições e siglas", TabelaView.class);
        menuItems.put("Funções do produto", TabelaView.class);
        menuItems.put("Atores e sist. externos", TabelaView.class);
        menuItems.put("Interfaces de usuário", TabelaView.class);
        menuItems.put("Requisitos de armazenamento", IsrView.class);
        menuItems.put("Casos de uso", CasoDeUsoView.class);
        treeMenu.addTreeSelectionListener(this);
        expandTree(treeMenu);
        DAO<Projeto> projetoDAO = new DAO(Projeto.class);
        projetoSelecionado = projetoDAO.listaTodos().get(0);
        Constante.projetoSelecionado = projetoSelecionado;
        classificadorAtributo = new ClassificadorAtributo(projetoSelecionado);
        this.setTitle(Constante.NOMEDOPRODUTO + projetoSelecionado.getDe());
        //exibirTelaProjeto();
        Constante.carregarBanco();
        iniciarTreeExplorar();

        classificarComoAtributo = new JMenuItem("Atributo");
        naoClassificarComoAtributo = new JMenuItem("Não classificar");
        frame = this;
        titulo.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                frame.setTitle((String) evt.getNewValue());
            }
        });
    }

    private void iniciarTreeExplorar() {
        jTreeExplorar.setDragEnabled(true);
        jTreeExplorar.setDropMode(DropMode.ON_OR_INSERT);
        jTreeExplorar.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        jTreeExplorar.setCellRenderer(new TreeExplorarCellRenderer());
        //Acrescentar a linha abaixo na outra versão do esr-editor
        jTreeAvisos.setCellRenderer(new TreeProblemaCellRenderer());
        jTreeErros.setCellRenderer(new TreeProblemaCellRenderer());
        treeMenu.setCellRenderer(new TreeMenuCellRender());
        //  jTreeExplorar.setTransferHandler(new TreeTransferHandler());

    }

    public void valueChanged(TreeSelectionEvent ev) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeMenu.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }
        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            JPanel panelTempo;
            try {
                panelTempo = (JPanel) menuItems.get(node.toString()).newInstance();
                iFrameAtivo = (IJframe) panelTempo;
                int i1[] = treeMenu.getSelectionRows();
                int i2 = i1[0];
                iFrameAtivo.setProjeto(projetoSelecionado, i2 - 2);
                jSplitPane3.setLeftComponent(panelTempo);
                panelTempo.repaint();
            } catch (InstantiationException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        jToolBar1 = new javax.swing.JToolBar();
        buttonAdd2 = new javax.swing.JButton();
        buttonAdd1 = new javax.swing.JButton();
        buttonAdd3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        buttonAdd = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        buttonSave = new javax.swing.JButton();
        buttonValidar = new javax.swing.JButton();
        buttonvalidacaoSimples = new javax.swing.JButton();
        jSplitPane3 = new javax.swing.JSplitPane();
        rodape = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTreeErros = new javax.swing.JTree();
        jTextArea1 = new javax.swing.JTextArea();
        jSplitPane4 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeMenu = new javax.swing.JTree();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTreeExplorar = new javax.swing.JTree();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTreeAvisos = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);

        buttonAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/documents-folders-full-icone-7256-32.png"))); // NOI18N
        buttonAdd2.setToolTipText("Projetos");
        buttonAdd2.setFocusable(false);
        buttonAdd2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdd2.setIconTextGap(0);
        buttonAdd2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdd2ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonAdd2);

        buttonAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/dictionary-icone-4876-32.png"))); // NOI18N
        buttonAdd1.setToolTipText("Cosntuiir léxico do domínio");
        buttonAdd1.setFocusable(false);
        buttonAdd1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdd1.setIconTextGap(0);
        buttonAdd1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdd1ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonAdd1);

        buttonAdd3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/print-icone-7817-32.png"))); // NOI18N
        buttonAdd3.setToolTipText("Imprimir documento de ERS");
        buttonAdd3.setFocusable(false);
        buttonAdd3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdd3.setIconTextGap(0);
        buttonAdd3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdd3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAdd3ActionPerformed(evt);
            }
        });
        jToolBar1.add(buttonAdd3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Info-icon.png"))); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jSplitPane1.setDividerLocation(230);
        jSplitPane1.setDividerSize(4);
        jSplitPane1.setToolTipText("");

        jSplitPane2.setDividerLocation(60);
        jSplitPane2.setDividerSize(4);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jToolBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.setFloatable(false);

        buttonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/add-square-icone-4404-32.png"))); // NOI18N
        buttonAdd.setFocusable(false);
        buttonAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonAdd.setIconTextGap(0);
        buttonAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonAdd);

        buttonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/square-delete-icone-6092-32.png"))); // NOI18N
        buttonDelete.setFocusable(false);
        buttonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonDelete.setIconTextGap(0);
        buttonDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDeleteActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonDelete);

        buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save-document-icone-9747-32.png"))); // NOI18N
        buttonSave.setFocusable(false);
        buttonSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonSave.setIconTextGap(0);
        buttonSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonSave);

        buttonValidar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/check-yes-ok-icone-7166-32.png"))); // NOI18N
        buttonValidar.setFocusable(false);
        buttonValidar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonValidar.setIconTextGap(0);
        buttonValidar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonValidar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonValidarActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonValidar);

        buttonvalidacaoSimples.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/checked-do-list-icone-6376-32.png"))); // NOI18N
        buttonvalidacaoSimples.setFocusable(false);
        buttonvalidacaoSimples.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buttonvalidacaoSimples.setIconTextGap(0);
        buttonvalidacaoSimples.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonvalidacaoSimples.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonvalidacaoSimplesActionPerformed(evt);
            }
        });
        jToolBar2.add(buttonvalidacaoSimples);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jSplitPane2.setTopComponent(jPanel1);

        jSplitPane3.setDividerLocation(600);
        jSplitPane3.setDividerSize(4);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jTreeErros.setBackground(new java.awt.Color(245, 245, 245));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeErros.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeErros.setDragEnabled(true);
        jTreeErros.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        jTreeErros.setRootVisible(false);
        jScrollPane2.setViewportView(jTreeErros);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
        );

        rodape.addTab("Saída", jPanel3);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        rodape.addTab("", jTextArea1);

        jSplitPane3.setRightComponent(rodape);

        jSplitPane2.setRightComponent(jSplitPane3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        jSplitPane4.setDividerSize(4);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Seções");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Introdução");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Objetivo/Escopo");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Referências");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Definições e siglas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Atores e sist. externos");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Interfaces de usuário");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Requisitos de armazenamento");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Funções do produto");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Casos de uso");
        treeNode1.add(treeNode2);
        treeMenu.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeMenu.setRootVisible(false);
        jScrollPane1.setViewportView(treeMenu);

        jSplitPane4.setLeftComponent(jScrollPane1);

        jTreeExplorar.setBackground(new java.awt.Color(245, 245, 245));
        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeExplorar.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeExplorar.setDragEnabled(true);
        jTreeExplorar.setRootVisible(false);
        jTreeExplorar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeExplorarMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTreeExplorar);

        jTabbedPane1.addTab("Explorar", jScrollPane3);

        jTreeAvisos.setBackground(new java.awt.Color(245, 245, 245));
        treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTreeAvisos.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeAvisos.setDragEnabled(true);
        jTreeAvisos.setRootVisible(false);
        jScrollPane5.setViewportView(jTreeAvisos);

        jTabbedPane1.addTab("Avisos", jScrollPane5);

        jSplitPane4.setRightComponent(jTabbedPane1);

        jSplitPane1.setLeftComponent(jSplitPane4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed
        if(iFrameAtivo!=null){
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            iFrameAtivo.incluir();
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }else{
            JOptionPane.showMessageDialog(null, "Escolha algum dos itens para adicionar");
        }
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        if(iFrameAtivo!=null){
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            iFrameAtivo.salvar();
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }else{
            JOptionPane.showMessageDialog(null, "Escolha algum dos itens para salvar");
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    private void buttonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDeleteActionPerformed
        if(iFrameAtivo!=null){
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            iFrameAtivo.deletar();
            getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }else{
            JOptionPane.showMessageDialog(null, "Escolha algum dos itens para deletar");
        }
    }//GEN-LAST:event_buttonDeleteActionPerformed

    private void buttonAdd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdd1ActionPerformed
        // TODO add your handling code here:
        dicionarioView = new DicionarioView(this, rootPaneCheckingEnabled, projetoSelecionado);
        dicionarioView.setProjetoSelecionado(projetoSelecionado);
        dicionarioView.setLocationRelativeTo(null);
        dicionarioView.setVisible(true);


    }//GEN-LAST:event_buttonAdd1ActionPerformed

    private void buttonAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdd2ActionPerformed
        // TODO add your handling code here:
        exibirTelaProjeto();
    }//GEN-LAST:event_buttonAdd2ActionPerformed

    private void buttonValidarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonValidarActionPerformed
        // TODO add your handling code here:
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        iFrameAtivo.validarSentenca();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_buttonValidarActionPerformed

    private void jTreeExplorarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeExplorarMouseClicked
        int row = jTreeExplorar.getClosestRowForLocation(evt.getX(), evt.getY());
        for (JTextArea texto : FluxoCasoDeUsoView.mapTextAtrea) {
            Highlighter h = texto.getHighlighter();
            h.removeAllHighlights();
        }

        if (SwingUtilities.isRightMouseButton(evt)) {
            jTreeExplorar.setSelectionRow(row);
            TreePath nodepath = jTreeExplorar.getSelectionPath();
            nodeAtribute = (DefaultMutableTreeNode) nodepath.getLastPathComponent();

            if (nodeAtribute.getParent().toString().equals(Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S)) {
                construirPopupMenu(projetoSelecionado, Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
            if (nodeAtribute.getParent().toString().equals(Constante.ATRIBUTO_S)
                    || nodeAtribute.getParent().toString().equals(Constante.ATRIBUTO_APAGADO_S)) {
                construirPopupMenu(projetoSelecionado, Constante.ATRIBUTO_S);
                jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } else {
            int selRow = jTreeExplorar.getRowForLocation(evt.getX(), evt.getY());
            TreePath selPath = jTreeExplorar.getPathForLocation(evt.getX(), evt.getY());
            if (selRow != -1) {
                if (evt.getClickCount() == 1) {
                    //mySingleClick(selRow, selPath);
                } else if (evt.getClickCount() == 2) {
                    System.out.println("Duplo click");
                    myDoubleClick(selRow, selPath);
                }
            }
        }

    }//GEN-LAST:event_jTreeExplorarMouseClicked

    private void buttonAdd3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAdd3ActionPerformed
        // TODO add your handling code here:
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        new MakeDocSRS(projetoSelecionado);
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_buttonAdd3ActionPerformed

    private void buttonvalidacaoSimplesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonvalidacaoSimplesActionPerformed
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        iFrameAtivo.validarTodasSentenca();
        getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));        // TODO add your handling code here:
    }//GEN-LAST:event_buttonvalidacaoSimplesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String texto = " O Gerador de documento de requisitos é uma ferramenta desenvolvida"
                + "\n no mestrado do Hélcio De Abreu Soares pela Universidade Federal do Piauí."
                + "\n O seu trabalho foi propor uma metodologia para auxiliar a escrita de documentos"
                + "\n de requisitos completos, consistentes e não ambíguos, denominada HELP4ERS."
                + "\n No momento esta fazendo parte dos projetos do Estágio Supervisionado no NTI,"
                + "\n tendo como primeiro colaborador o graduando Wellington Teixeira Coimbra."
                + "\n O objetivo desse estágio é fazer melhorias do projeto para que possa estar "
                + "\n apto a ajudar cada vez mais pessoas no desenvolvimento de seus projetos";
        JOptionPane.showMessageDialog(null, texto);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonAdd1;
    private javax.swing.JButton buttonAdd2;
    private javax.swing.JButton buttonAdd3;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonSave;
    private javax.swing.JButton buttonValidar;
    private javax.swing.JButton buttonvalidacaoSimples;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    public static javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public static javax.swing.JTree jTreeAvisos;
    public static javax.swing.JTree jTreeErros;
    public static javax.swing.JTree jTreeExplorar;
    private javax.swing.JTabbedPane rodape;
    private static javax.swing.JTree treeMenu;
    // End of variables declaration//GEN-END:variables

    public static void expandTree(javax.swing.JTree tree) {
        try {
            for (int row = 0; row <= tree.getRowCount(); row++) {
                tree.expandRow(row);
            }
        } catch (Exception e) {
            //tratar erro  
        }
    }

    public void collapseAll(javax.swing.JTree tree) {
        int row = tree.getRowCount() - 1;
        while (row >= 0) {
            tree.collapseRow(row);
            row--;
        }
    }

    private void exibirTelaProjeto() {

        telaProjeto.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        telaProjeto.setResizable(false);
        telaProjeto.setLocationRelativeTo(null);
        telaProjeto.setVisible(true);
        projetoSelecionado = Constante.projetoSelecionado;
        titulo.setText(Constante.NOMEDOPRODUTO + projetoSelecionado.getDe());
        valueChanged(null);
    }

    public void construirPopupMenu(Projeto projetoSelecionadoSelecionado, String label) {

        ActionListener anActionListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                TreeProblemasNode no = (TreeProblemasNode) nodeAtribute.getUserObject();
                try {
                    no = classificadorAtributo.classificarAtributo(evt, no);
                } catch (Exception ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        jPopupMenu1.removeAll();
        classificarComoAtributo.setDisplayedMnemonicIndex(0);
        naoClassificarComoAtributo.setDisplayedMnemonicIndex(Constante.CONCEITO);

        classificarComoAtributo.addActionListener(anActionListener);
        naoClassificarComoAtributo.addActionListener(anActionListener);

        classificarOrigemAtributo = new JMenu("Definir origem");

        if (label.equals(Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S)) {
            jPopupMenu1.add(classificarComoAtributo);
        } else {
            jPopupMenu1.add(naoClassificarComoAtributo);
            TreePath nodepath = jTreeExplorar.getSelectionPath();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodepath.getLastPathComponent();
            TreeProblemasNode userObjetc = (TreeProblemasNode) node.getUserObject();
            if (userObjetc.getTipoNode() == Constante.ATRIBUTO_DUPLICADO) {
                List<String> lista = classificadorAtributo.recuperarIsrAtributo(userObjetc.getLabel());
                for (String lista1 : lista) {
                    JMenuItem jMenuItem = new JMenuItem(lista1);
                    classificarOrigemAtributo.add(jMenuItem);
                    jMenuItem.setDisplayedMnemonicIndex(2);
                    jMenuItem.addActionListener(anActionListener);
                }
                Atributo a = (Atributo) userObjetc.getBaseEntity();
                JMenuItem jMenuItem = new JMenuItem(a.getIdIsr().getNome());
                for (int i = 0; i <= classificarOrigemAtributo.getItemCount() - 1; i++) {
                    JMenuItem jMenuItem1 = classificarOrigemAtributo.getItem(i);
                    if (jMenuItem.getText().equals(jMenuItem1.getText())) {
                        classificarOrigemAtributo.add(jMenuItem);
                    }
                }
                jMenuItem.setDisplayedMnemonicIndex(2);
                jMenuItem.addActionListener(anActionListener);
                classificarOrigemAtributo.add(jMenuItem);

                jPopupMenu1.add(classificarOrigemAtributo);
            }
        }

    }

    private void myDoubleClick(int selRow, TreePath selPath) {
        DefaultMutableTreeNode no = (DefaultMutableTreeNode) selPath.getLastPathComponent();
        try {
            Tabela t = (Tabela) no.getUserObject();
            for (JTextArea texto : FluxoCasoDeUsoView.mapTextAtrea) {
                Highlighter h = texto.getHighlighter();
                try {
                    int i = 0;
                    String t1 = texto.getText().toLowerCase();
                    h.removeAllHighlights();
                    int iAnterior = 0;
                    while (i >= 0 && (i + t.getNome().length()) < (texto.getText().length() - 1)) {
                        i = t1.indexOf(t.getNome().toLowerCase());
                        if (i > 0) {
                            h.addHighlight(iAnterior + i, iAnterior + i + t.getNome().length(), new DefaultHighlighter.DefaultHighlightPainter(new Color(230, 230, 230)));        // TODO add your handling code here:
                            t1 = t1.substring(i + t.getNome().length());
                            iAnterior = i + iAnterior + t.getNome().length();
                        }

                    }
                } catch (BadLocationException ex) {
                    Logger.getLogger(Editor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
        }

    }

    public  static void setTitulo(String texto) {
        titulo.setText(texto);

    }

}
