/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SRSJTree;

import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author helcio.soares
 */
public class Root {
        public DefaultTreeModel treeModelErros; 
        public DefaultTreeModel treeModelAvisos;     
        public DefaultTreeModel treeModelExplorar;     


    public Root(DefaultTreeModel treeModelErros, DefaultTreeModel treeModelAvisos, DefaultTreeModel treeModelExplorar) {
        this.treeModelErros = treeModelErros;
        this.treeModelAvisos = treeModelAvisos;
        this.treeModelExplorar = treeModelExplorar;
    }
       
        
}
