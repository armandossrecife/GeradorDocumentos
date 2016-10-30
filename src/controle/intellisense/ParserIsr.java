/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.intellisense;

import dados.DAO.DAO;
import dados.DAO.DicionarioDAO;
import visao.view.SRSJTree.Root;
import visao.view.SRSJTree.TreeBuilder;
import visao.view.SRSJTree.TreeProblemasNode;
import controle.analiseTexto.AnalisePeriodo;
import entity.Atributo;
import entity.Isr;
import entity.Projeto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.apache.poi.hssf.record.formula.functions.Irr;
import visao.view.tools.Constante;
import view.Editor;

/**
 *
 * @author helcio.soares
 */
public class ParserIsr extends AbstractParser {

    private TreeBuilder treeBuilder;

    private Projeto projetoSelecionado;
    private DicionarioDAO dicionarioDAO = new DicionarioDAO();
    private Isr isr;

    public ParserIsr(Projeto projetoSelecionado, Isr isr) {
        this.projetoSelecionado = projetoSelecionado;
        this.treeBuilder = new TreeBuilder(dicionarioDAO, projetoSelecionado);
        this.isr = isr;
    }

    public Root validarAtributos(String sentenca) throws Exception {

        AnalisePeriodo analisePeriodo = new AnalisePeriodo(sentenca);
        ArrayList<String> candidatosAAtributo = analisePeriodo.recuperarAtributos(sentenca);
        candidatosAAtributo = Constante.eliminarRepetidosPorLemma(candidatosAAtributo, 1);
        return treeBuilder.construirArvoreAtributos(candidatosAAtributo, this.isr);
    }

}
