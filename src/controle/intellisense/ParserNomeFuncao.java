/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools |
 Templates
 * and open the template in the editor.
 */
package controle.intellisense;

import dados.DAO.DicionarioDAO;
import entidades.entity.Projeto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.text.JTextComponent;
import visao.view.tools.Constante;

/**
 *
 * @author helcio.soares
 */
public class ParserNomeFuncao extends AbstractParser {

    private Projeto projetoSelecionado;
    public ParserNomeFuncao(Projeto projetoSelecionado) {
        this.projetoSelecionado = projetoSelecionado;
    }

    public DefaultListModel avaliaFrase(String frase) {
        List<String> listaOpcoes = new ArrayList<>();
        DefaultListModel listIntellisense = new DefaultListModel();
        listIntellisense = new DefaultListModel();
        listaOpcoes = buildListIntellisense(Constante.FUNCIONALIDADE, projetoSelecionado.getId());
        listIntellisense = listToDefaultListModel(listaOpcoes);
        return listIntellisense;
    }


}
