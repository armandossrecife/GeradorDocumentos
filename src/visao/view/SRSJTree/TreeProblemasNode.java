/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.view.SRSJTree;

import entidades.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 *
 * @author helcio.soares
 */
public class TreeProblemasNode extends DefaultMutableTreeNode{

    private int tipoNode;
    private String label;
    private int startIndex;
    private int stopIndex;
    private int charPositionLine;
    private int line;    
    private String nomeOriginal;
    private BaseEntity baseEntity;
    
    public TreeProblemasNode(int tipoNode, String label, BaseEntity baseEntity) {
        this.tipoNode   = tipoNode;
        this.label      = label;
        this.baseEntity = baseEntity;
    }

    public TreeProblemasNode(int tipoNode, String label, int startIndex, int stopIndex, int charPositionLine, int line) {
        this.tipoNode = tipoNode;
        this.label = label;
        this.startIndex = startIndex;
        this.stopIndex = stopIndex;
        this.charPositionLine = charPositionLine;
        this.line = line;
    }

    public int getTipoNode() {
        return tipoNode;
    }

    public void setTipoNode(int tipoNode) {
        this.tipoNode = tipoNode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStopIndex() {
        return stopIndex;
    }

    public void setStopIndex(int stopIndex) {
        this.stopIndex = stopIndex;
    }

    public int getCharPositionLine() {
        return charPositionLine;
    }

    public void setCharPositionLine(int charPositionLine) {
        this.charPositionLine = charPositionLine;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public BaseEntity getBaseEntity() {
        return baseEntity;
    }

    public void setBaseEntity(BaseEntity baseEntity) {
        this.baseEntity = baseEntity;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }
    
    @Override
    public String toString() {
        return label;
    }
}
