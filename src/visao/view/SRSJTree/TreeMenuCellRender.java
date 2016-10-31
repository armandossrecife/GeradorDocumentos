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
import entidades.entity.Atributo;
import entidades.entity.Tabela;
import entidades.entity.tools.TipoTabela;
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

public class TreeMenuCellRender extends DefaultTreeCellRenderer implements TreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);

        if (value.toString().equals("Objetivo/Escopo")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/subject-16.png")));
        }
        if (value.toString().equals("Referências")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/reference-16.png")));
        }
        if (value.toString().equals("Definições e siglas")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/glossary-16.png")));
        }
        if (value.toString().equals("Funções do produto")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/engine-kbackgammon-icone-8807-16.png")));
        }
        if (value.toString().equals("Atores e sist. externos")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/atorTransparente.png")));
        }
        if (value.toString().equals("Interfaces de usuário")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/office-icone-5766-16.png")));
        }
        if (value.toString().equals("Requisitos de armazenamento")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/classe.png")));
        }
        if (value.toString().equals("Casos de uso")) {
            setIcon(new ImageIcon(getClass().getResource("/imagens/system-users.png")));
        }
        return this;
    }
}
