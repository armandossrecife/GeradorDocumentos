/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import tools.Constante;

//TODO Tentar fazer a seleção como nas tabelas do word
//
/**
 *
 * @author helcio.soares
 */
//Custom renderer - do what the natural renderer would do, just add a border
public class CustomRendererTableDicionario extends DefaultTableCellRenderer {

    private Font fontePadrao = new Font("monospaced", Font.BOLD, 12);
    private Color corSelecao = null;
    TableCellRenderer render;
    Border b;

    public CustomRendererTableDicionario(TableCellRenderer r, Color top) {
        corSelecao = top;
        render = r;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
        int corte = (table.getModel().getRowCount() * Constante.CORTE) / 100;

        JComponent result = (JComponent) render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Color bg = null;
        Color corZebrado = new ColorUIResource(242, 242, 242);
        Color corMaior = new Color(202, 50, 50);

        if (isSelected) {
            bg = corSelecao;
            result.setForeground(Color.white);
            result.setBackground(bg);
            return result;
        } else {
            if (row % 2 == 0) {
                result.setForeground(Color.black);
                result.setBackground(Color.white);
                return result;
            } else {
                result.setForeground(Color.black);
                result.setBackground(corZebrado);
                return result;
            }
        }

    }
}
