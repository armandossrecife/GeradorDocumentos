/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.border.Border;
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
public class CustomRenderer extends DefaultTableCellRenderer {

    private Font fontePadrao = new Font("monospaced", Font.BOLD, 12);
    private Color corSelecao;
    TableCellRenderer render;
    Border b;
    public CustomRenderer(TableCellRenderer r, Color corSelecao) {
        render = r;
        this.corSelecao = corSelecao;
        Color top = Color.black;
            //It looks funky to have a different color on each side - but this is what you asked
        //You can comment out borders if you want too. (example try commenting out top and left borders)
        b = BorderFactory.createCompoundBorder();
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 0, 0, top));
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 1, 0, top));
        b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(0, 0, 0, 1, top));

    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {
        int corte = (table.getModel().getRowCount() * Constante.CORTE) / 100;

        JComponent result = (JComponent) render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        result.setBorder(b);
        table.setIntercellSpacing(new Dimension(0,0));//Get rid of cell spacing
        table.setRowHeight(20);
        Color bg;
        if (!isSelected) {
            bg = Color.white;
        } else {
            bg = corSelecao;
        }
        
        result.setForeground(Color.black);
        result.setBackground(bg);
        return result;
    }
}
