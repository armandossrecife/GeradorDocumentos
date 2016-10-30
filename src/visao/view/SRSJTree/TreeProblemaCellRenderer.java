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

public class TreeProblemaCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

// Você pode usar o objeto value para avaliar o nó que está sedo mostrado e editar apenas um nó em especial
        if (!value.toString().equals(Constante.RAIZ)) {
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
            Object objeto = node.getUserObject();
            Integer tipoNo = 0;
            if (objeto instanceof TreeProblemasNode) {
                TreeProblemasNode no = (TreeProblemasNode) objeto;
                tipoNo = no.getTipoNode();
            }
            switch (tipoNo) {
                case Constante.ERRO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/error.png")));
                    break;
                case Constante.TERMO_ENCONTRADO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/erroEncontrado.png")));
                    break;
                case Constante.AVISOS_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/warning.png")));
                    break;

                case Constante.FORA_DO_PADRAO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/foraDoPadrao.png")));
                    break;
                case Constante.TERMO_NAO_ENCONTRADO_LEXICO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/not-found.png")));
                    break;
                case Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/not-found.png")));
                    break;
                case Constante.TERMO_ESPERADO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/check.png")));

                case Constante.SEM_AVISO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/check.png")));

                case Constante.SEM_ERRO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/check.png")));
                    break;
                case Constante.CASO_DE_USO:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/system-users.png")));
                    break;
                case Constante.CONCEITO1:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/conceito.png")));
                    break;
                case Constante.SINONIMO_:
                    setIcon(new ImageIcon(getClass().getResource("/imagens/sinonimo-16.png")));
                    break;           }

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
    public DefaultMutableTreeNode findNode(DefaultMutableTreeNode node, String userObject) {
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
