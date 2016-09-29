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
public class CustomRendererTableSinonimos extends DefaultTableCellRenderer {

    private Font fontePadrao = new Font("monospaced", Font.BOLD, 12);
    private Color corSelecao = null;
    TableCellRenderer render;
    Border b;
    private int[] linhas;
    private int numeroPrimeiraLinha;
    int zebrado  = 242;
    int sinonimo = 150;
    Color bg = null;
    Color corZebrado  = new ColorUIResource(zebrado, zebrado, zebrado);
    Color corSinonimo = new Color(sinonimo, sinonimo, sinonimo);

    public CustomRendererTableSinonimos(TableCellRenderer r, Color corSelecao, int[] linhas, int numeroPrimeiraLinha) {
        render = r;
        this.linhas = linhas;
        this.corSelecao = corSelecao;
        this.numeroPrimeiraLinha = numeroPrimeiraLinha;
    }

    public int[] getLinhas() {
        return linhas;
    }

    public int getNumeroPrimeiraLinha() {
        return numeroPrimeiraLinha;
    }

    public void setNumeroPrimeiraLinha(int numeroPrimeiraLinha) {
        this.numeroPrimeiraLinha = numeroPrimeiraLinha;
    }

    public void setLinhas(int[] linhas) {
        this.linhas = linhas;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus, int row,
            int column) {

        JComponent result = (JComponent) render.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (isSelected) {
            if (table.getSelectedRowCount() == 1) {
                bg = corSelecao;
                result.setForeground(Color.white);
                result.setBackground(bg);
            } else {
                if (row == numeroPrimeiraLinha) {
                    bg = corSelecao;
                    result.setForeground(Color.white);
                    result.setBackground(bg);
                } else {
                    result.setForeground(Color.white);
                    result.setBackground(corSinonimo);

                }
            }
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
