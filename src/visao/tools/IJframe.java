/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.tools;

import entidades.Projeto;
import javax.swing.text.JTextComponent;

/**
 *
 * @author helcio.soares
 */
public interface IJframe {
    public void setProjeto(Projeto projeto, int idTipoTabela);
    public void validarSentenca();
    public void validarTodasSentenca();
    public void incluir();
    public void salvar();
    public void deletar();
    public void refresh();

}
