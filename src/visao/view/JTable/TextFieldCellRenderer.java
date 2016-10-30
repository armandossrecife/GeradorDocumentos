/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.view.JTable;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author helcio.soares
 */
public class TextFieldCellRenderer extends JTextField implements TableCellRenderer {  
  
   public TextFieldCellRenderer() {  
//      setL setLineWrap(true);  
//      setWrapStyleWord(true);  
   }  
  
   public Component getTableCellRendererComponent(   
                    JTable jTable,   
                    Object obj,   
                    boolean isSelected,   
                    boolean hasFocus,   
                    int row,   
                    int column)   
   {  
      // set color & border here  
      setText((obj == null) ? "" : obj.toString());  
      setSize( jTable.getColumnModel().getColumn(column).getWidth(),  
                  getPreferredSize().height);  
      if (jTable.getRowHeight(row) != getPreferredSize().height)   
      {  
         jTable.setRowHeight(row, getPreferredSize().height);  
      }  
      return this;  
   }  
}  