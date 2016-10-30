/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.view.SRSJTree;

/**
 *
 * @author helcio.soares
 */
import static controle.analiseTexto.AnalisePeriodo.tagger;
import entity.Atributo;
import entity.Tabela;
import entity.tools.TipoTabela;
import controle.grammar.SrsGrammarParser;
import java.awt.Component;
import java.util.Enumeration;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import org.antlr.v4.runtime.CommonToken;
import visao.view.tools.Constante;

public class TreeExplorarCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

// Você pode usar o objeto value para avaliar o nó que está sedo mostrado e editar apenas um nó em especial
        if (!value.toString().equals(Constante.RAIZ)) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object objeto = node.getUserObject();
            Integer valorNo = 0;
            if (objeto instanceof Tabela) {
                Tabela no = (Tabela) objeto;
                valorNo = no.getIdTipoTabela();
            } else if (objeto instanceof TipoTabela) {
                TipoTabela no = (TipoTabela) objeto;
                valorNo = no.getId();
            } else if (objeto instanceof TreeProblemasNode) {
                TreeProblemasNode no = (TreeProblemasNode) objeto;
                if (no.getBaseEntity() instanceof Atributo) {
                    Atributo a = (Atributo) no.getBaseEntity();
                    if (a.getId() != null && a.getIdIsr() != null
                            && a.getIdIsrOrigem() != null
                            && !a.getIdIsr().equals(a.getIdIsrOrigem())) {
                        valorNo = Constante.REQUISITOS_DE_ARMAZENAMENTO;
                        no.setNomeOriginal(a.getNome());
                        no.setLabel(a.getIdIsrOrigem().getNome());
                    } else {
                        valorNo = no.getTipoNode();
                    }
                } else {
                    valorNo = no.getTipoNode();
                }
            }

            switch (valorNo) {
                case Constante.CONCEITO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/conceito.png")));
                    break;
                case Constante.USUARIO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/atorTransparente.png")));
                    break;
                case Constante.INTERFACE_USUARIO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/office-icone-5766-16.png")));
                    break;
                case Constante.REQUISITOS_DE_ARMAZENAMENTO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/classe.png")));
                    break;
                case Constante.FUNCAODOPRODUTO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/engine-kbackgammon-icone-8807-16.png")));
                    break;
                case Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/not-found.png")));
                    break;
                case Constante.TERMO_NAO_ENCONTRADO_LEXICO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/not-found.png")));
                    break;
                case Constante.CASO_DE_USO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/system-users.png")));
                    break;
                case Constante.BUTTON:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/iconButton.png")));
                    break;
                case Constante.COMBOBOX:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/combobox.png")));
                    break;
                case Constante.TEXTFIELD_OUT:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/textfield-out-16.png")));
                    break;
                case Constante.TEXTFIELD_IN:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/textfield-in-16.png")));
                    break;
                case Constante.ATRIBUTO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/atributo.png")));
                    break;
                case Constante.ATRIBUTO_OK:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/atributo-ok-16.png")));
                    break;
                case Constante.ATRIBUTO_APAGADO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/erased-16.png")));
                    break;
                case Constante.ATRIBUTO_DUPLICADO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/warning.png")));
                    break;
                case Constante.CHECK:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/check-ok-16.png")));
                    break;
            }

        }
        return this;
    }

    /**
     * Função que busca um nó, com base no objeto que está dentro dele (no seu
     * caso o objeto que vale "Numbers")
     *
     * @param node Um nó qualquer. Use o root para procurar pela árvore toda.
     * @param node O objeto a ser procurado nesse nó, ou em qualquer filho dele.
     * @return O nó que contém o objeto cujo valor deseja, ou null, caso nenhum
     * nó seja encontrado.
     */
    public DefaultMutableTreeNode findNode(DefaultMutableTreeNode node, Object userObject) {
        //Verifica se ele mesmo não é o nó procurado.  
        if (node.toString().equals(userObject)) {
            return node;
        }

        //Se não for, verifica se seus filhos contém o nó procurado  
        for (Enumeration e = node.children(); e.hasMoreElements();) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) e.nextElement();
            DefaultMutableTreeNode node1 = findNode(child, userObject);
            if (node1 != null) {
                return node1;
            }
        }
        return null;
    }
}
