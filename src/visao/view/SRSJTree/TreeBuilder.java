/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao.view.SRSJTree;

import dados.DAO.DAO;
import dados.DAO.DicionarioDAO;
import entidades.Atributo;
import entidades.CasoDeUso;
import entidades.Isr;
import entidades.Projeto;
import entidades.Tabela;
import entidades.Conceito;
import entidades.SinonimoDominio;
import entidades.tools.TipoTabela;
import controle.intellisense.ElementoFrase;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import visao.view.tools.Constante;
import visao.view.Editor;

/**
 *
 * @author helcio.soares
 */
public class TreeBuilder {

    private DicionarioDAO dicionarioDAO;
    private Projeto projetoSelecionado;
    private DAO<Atributo> atributoDAO = new DAO(Atributo.class);
    private DAO<SinonimoDominio> sinonimoDominioDAO = new DAO(SinonimoDominio.class);
    private DAO<Conceito> conceitoDAO = new DAO(Conceito.class);
    private DAO<Isr> isrDAO = new DAO(Isr.class);

    final String QUERY = "select a FROM Atributo a    \n"
            + "where a.idProjeto = :idProjeto \n"
            + "and   a.idIsr     = :idIsr     ";

    public TreeBuilder() {

    }

    public TreeBuilder(DicionarioDAO dicionarioDAO, Projeto projetoSelecionado) {
        this.dicionarioDAO = dicionarioDAO;
        this.projetoSelecionado = projetoSelecionado;
    }

    public void desenharRaiz(DefaultTreeModel treeModelErros, DefaultTreeModel treeModelAvisos,
            DefaultTreeModel treeModelExplorar) {

        Editor.jTreeErros.setRootVisible(false);
        Editor.jTreeErros.setModel(treeModelErros);
        Editor.expandTree(Editor.jTreeErros);

        try {
            DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) treeModelAvisos.getRoot();
            DefaultMutableTreeNode raizAviso = (DefaultMutableTreeNode) raiz.getChildAt(0);
            if (raizAviso.getChildCount() > 0) {
                treeModelAvisos = new DefaultTreeModel(raizAviso);
            }
        } catch (Exception e) {
            //           e.printStackTrace();
        }

        Editor.jTreeAvisos.setRootVisible(false);
        Editor.jTreeAvisos.setModel(treeModelAvisos);
        Editor.expandTree(Editor.jTreeAvisos);

        Editor.jTreeExplorar.setRootVisible(false);
        Editor.jTreeExplorar.setModel(treeModelExplorar);
        Editor.expandTree(Editor.jTreeExplorar);

    }

    public ArrayList<DefaultMutableTreeNode> construirListaAvisos(ArrayList<String> elementosFrase, HashMap<String, ElementoFrase> hashElementosFrase) throws Exception {

        ArrayList<DefaultMutableTreeNode> temp = new ArrayList<DefaultMutableTreeNode>();
        DefaultMutableTreeNode raizAvisos = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.AVISOS_, Constante.AVISOS, null));
        DefaultMutableTreeNode noElementosNaoIdentificadoLexico = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_LEXICO_, Constante.TERMO_NAO_ENCONTRADO_LEXICO, null));
        DefaultMutableTreeNode noElementosNaoIdentificadoSecao = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES, Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S, null));
        DefaultMutableTreeNode noRaizSinonimo = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SINONIMO_, Constante.SINONIMO_S, null));
        DefaultMutableTreeNode noNaoIdentificadoLexico = null;
        DefaultMutableTreeNode noNaoIdentificadoSecoes = null;
        DefaultMutableTreeNode noSinonimos = null;

        for (String elementoDaFrase : elementosFrase) {
            String lemmaElementoDaFrase = Constante.recuperarLemmaDaPalavra(elementoDaFrase);
            List<String> listaSinonimo = dicionarioDAO.retornaSinonimos(lemmaElementoDaFrase, projetoSelecionado);
            List<Tabela> listaTabela = dicionarioDAO.recuperarTabelaPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
            Tabela tabela = (listaTabela.size() > 0) ? listaTabela.get(0) : null;

            Isr isr = null;
            CasoDeUso casoDeUso = null;
            Atributo atributo = null;

            if (tabela == null) {
                isr = dicionarioDAO.recuperarIsrPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
                if (isr == null) {
                    casoDeUso = dicionarioDAO.recuperarCasoDeUsoPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
                    if (casoDeUso == null) {
                        atributo = dicionarioDAO.recuperarAtributoPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
                        if (atributo == null) {
                            Conceito conceitoBanco = dicionarioDAO.recuperarConceitoPorLemmaNome(lemmaElementoDaFrase, projetoSelecionado.getId());
                            if (conceitoBanco == null) {
                                noNaoIdentificadoLexico = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_LEXICO_, elementoDaFrase, null));
                                noElementosNaoIdentificadoLexico.add(noNaoIdentificadoLexico);
                            } else {
                                noNaoIdentificadoSecoes = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES, elementoDaFrase, null));
                                noElementosNaoIdentificadoSecao.add(noNaoIdentificadoSecoes);
                            }
                        }
                    }
                }
            }

            for (String lemmaElementoDaFrase2 : listaSinonimo) {
                Conceito c = conceitoDAO.buscaPorNomeLemma(lemmaElementoDaFrase2, projetoSelecionado.getId());
                if (c != null) {
                    noSinonimos = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SINONIMO_, elementoDaFrase + " = " + c.getDe(), null));
                    if (!existeNo(noRaizSinonimo, elementoDaFrase + " = " + c.getDe())) {
                        noRaizSinonimo.add(noSinonimos);
                    }
                }
            }

        }

        if (noNaoIdentificadoLexico == null && noNaoIdentificadoSecoes == null && noSinonimos == null) { //aqui
            raizAvisos = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.SEM_AVISO_, Constante.SEM_AVISO, null));
        } else {
            if (noNaoIdentificadoSecoes != null) {
                raizAvisos.add(noElementosNaoIdentificadoSecao);
            }
            if (noNaoIdentificadoLexico != null) {
                raizAvisos.add(noElementosNaoIdentificadoLexico);
            }
            if (noSinonimos != null) {
                raizAvisos.add(noRaizSinonimo);
            }
        }
        temp.add(raizAvisos);
        temp.add(construirArvoreExplorar(elementosFrase, hashElementosFrase));
        return temp;
    }

    public DefaultMutableTreeNode construirArvoreExplorar(ArrayList<String> elementosFrase, HashMap<String, ElementoFrase> hashElementosFrase) throws Exception {

        DefaultMutableTreeNode raizExplorar = new DefaultMutableTreeNode(Constante.RAIZ);
        DefaultMutableTreeNode nodePai = null;
        DefaultMutableTreeNode nodeFilho = null;
        DefaultMutableTreeNode nodeTemp = null;

        HashMap<Integer, DefaultMutableTreeNode> hashNode = new HashMap<Integer, DefaultMutableTreeNode>();

        for (Map.Entry<Integer, TipoTabela> entrySet : Constante.hashTipoTabela.entrySet()) {
            Integer idTipoTabela = entrySet.getKey();
            TipoTabela tipoTabela = entrySet.getValue();
            if (tipoTabela.getCarregaArvore() == 1) {
                nodeTemp = new DefaultMutableTreeNode(tipoTabela);
                nodeTemp.setUserObject(tipoTabela);
                hashNode.put(idTipoTabela, nodeTemp);
            }
        }

        for (String elementoDaFrase : elementosFrase) {

            String lemmaElementoDaFrase = Constante.recuperarLemmaDaPalavra(elementoDaFrase);
            List<String> list = dicionarioDAO.retornaSinonimos(lemmaElementoDaFrase, projetoSelecionado);
            List<Tabela> listaTabela = dicionarioDAO.recuperarTabelaPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());

            if (listaTabela.isEmpty()) {
                for (String sinonimoDominio : list) {
                    listaTabela = dicionarioDAO.recuperarTabelaPorNomeLemma(sinonimoDominio, projetoSelecionado.getId());
                }
            }

            for (Tabela tabela : listaTabela) { //Modificado em 26/12/2015

                DefaultMutableTreeNode nodeNeto;
                tabela.setNome(elementoDaFrase);
                nodeFilho = new DefaultMutableTreeNode(tabela);
                nodePai = hashNode.get(tabela.getIdTipoTabela());
                nodeFilho.setUserObject(tabela);

                if (tabela.getIdTipoTabela().equals(Constante.INTERFACE_USUARIO)) {

                    ElementoFrase e = hashElementosFrase.get(tabela.getIdTipoTabela() + elementoDaFrase.toLowerCase());
                    if (e != null) {
                        ArrayList<Tabela> elementos = e.getFilhos();
                        for (Tabela neto : elementos) {
                            nodeNeto = new DefaultMutableTreeNode(neto);
                            nodeFilho.add(nodeNeto);
                        }
                    }
                }
                if (Constante.findNode(nodePai, tabela.getNome()) == null) {
                    nodePai.add(nodeFilho);
                }
                hashNode.put(tabela.getIdTipoTabela(), nodePai);
            }

            Tabela tabela = null;
            Isr isr = dicionarioDAO.recuperarIsrPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
            if (isr == null) {
                int i = 0;
                while (i <= list.size() - 1 && isr == null) {
                    String sinonimoDominio = list.get(i);
                    isr = dicionarioDAO.recuperarIsrPorNomeLemma(sinonimoDominio, projetoSelecionado.getId());
                    i++;
                }
            }

            if (isr != null) {
                DefaultMutableTreeNode nodeNeto;
                tabela = new Tabela(-1 * isr.getId());
                tabela.setNome(elementoDaFrase);
                tabela.setIdTipoTabela(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                nodeFilho = new DefaultMutableTreeNode(tabela);
                nodePai = hashNode.get(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                ElementoFrase e = hashElementosFrase.get(Constante.REQUISITOS_DE_ARMAZENAMENTO + elementoDaFrase.toLowerCase());
                if (e != null) {
                    ArrayList<Tabela> elementos = e.getFilhos();
                    for (Tabela neto : elementos) {
                        nodeNeto = new DefaultMutableTreeNode(neto);
                        nodeFilho.add(nodeNeto);
                    }
                }

                nodeFilho.setUserObject(tabela);
                //               if (Constante.findNode(nodePai, tabela.getNome()) == null) {
                nodePai.add(nodeFilho);
                //               }
                hashNode.put(Constante.REQUISITOS_DE_ARMAZENAMENTO, nodePai);
            }

            CasoDeUso casoDeUso = dicionarioDAO.recuperarCasoDeUsoPorNomeLemma(lemmaElementoDaFrase, projetoSelecionado.getId());
            if (casoDeUso == null) {
                int i = 0;
                while (i <= list.size() - 1 && casoDeUso == null) {
                    String sinonimoDominio = list.get(i);
                    casoDeUso = dicionarioDAO.recuperarCasoDeUsoPorNomeLemma(sinonimoDominio, projetoSelecionado.getId());
                    i++;
                }
            }

            if (casoDeUso != null) {
                tabela = new Tabela(-1 * casoDeUso.getId());
                tabela.setNome(elementoDaFrase);
                tabela.setIdTipoTabela(Constante.CASO_DE_USO);
                nodeFilho = new DefaultMutableTreeNode(tabela);
                nodePai = hashNode.get(Constante.CASO_DE_USO);

                nodeFilho.setUserObject(tabela);
                if (Constante.findNode(nodePai, tabela.getNome()) == null) {
                    nodePai.add(nodeFilho);
                }
                hashNode.put(Constante.CASO_DE_USO, nodePai);
            }
        }

        for (Map.Entry<Integer, DefaultMutableTreeNode> entrySet : hashNode.entrySet()) {
            Integer key = entrySet.getKey();
            DefaultMutableTreeNode value = entrySet.getValue();
            raizExplorar.add(value);
        }

        return raizExplorar;
    }

    public Root construirArvoreAtributos(ArrayList<String> candidatosAAtributo, Isr isr) throws Exception {

        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));
        DefaultMutableTreeNode raizCandidatoAAtributo = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_LEXICO_, Constante.TERMO_NAO_ENCONTRADO_NAS_SECOES_S, null));
        DefaultMutableTreeNode raizEhAtributo = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ATRIBUTO, "Atributos", null));
        DefaultMutableTreeNode raizAtributoNaoUtilizado = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.ATRIBUTO_APAGADO, Constante.ATRIBUTO_APAGADO_S, null));
        DefaultMutableTreeNode raizE = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));
        DefaultMutableTreeNode raizA = new DefaultMutableTreeNode(new TreeProblemasNode(Constante.RAIZ_, Constante.RAIZ, null));

        Map<String, Object> params = new HashMap<>();
        params.put("idIsr", isr);
        params.put("idProjeto", projetoSelecionado);

        List<String> candidatosAAtributoLemma = Constante.trocarPorListaDeLemma(candidatosAAtributo);
        List<String> atributos = new ArrayList<>();
        List<String> atributosLemma = new ArrayList<>();

        List<String> maior;
        List<String> menor;
        List<String> outro = new ArrayList<>();
        HashMap<String, Atributo> atributosHashSet = new HashMap<>();
        for (Atributo atributo1 : atributoDAO.filtrarPorCampos(params, QUERY)) {
            if (atributo1.getValidado() != 0) {
                atributosLemma.add(atributo1.getNome());
            }
            atributosHashSet.put(atributo1.getNome(), atributo1);
        }
        int quemEHOOutro = 0;

        if (candidatosAAtributoLemma.size() > atributosLemma.size()) {
            // Outro contém candidados a atributos
            maior = new ArrayList(candidatosAAtributo);
            menor = new ArrayList(atributosLemma);
        } else {
            // Outro contém atributos excluídos
            maior = new ArrayList(atributosLemma);
            menor = new ArrayList(candidatosAAtributo);
            quemEHOOutro = 1;
        }

        for (String at1 : maior) {

            if (menor.contains(at1)) {
                Atributo a = atributosHashSet.get(at1);
                if (a.getValidado() == 2) {
                    a.setValidado(1);
                    atributoDAO.atualiza(a);
                }
                atributos.add(at1);
                menor.remove(at1);
            } else {
                outro.add(at1);
            }

        }

        adicionarNos(raizEhAtributo, atributos, Constante.ATRIBUTO_OK, atributosHashSet, isr);

        if (quemEHOOutro == 0) {
            adicionarNos(raizCandidatoAAtributo, outro, Constante.TERMO_NAO_ENCONTRADO_LEXICO_, atributosHashSet, isr);
            adicionarNos(raizAtributoNaoUtilizado, menor, Constante.ATRIBUTO_APAGADO, atributosHashSet, isr);
        } else {
            adicionarNos(raizAtributoNaoUtilizado, outro, Constante.ATRIBUTO_APAGADO, atributosHashSet, isr);
            adicionarNos(raizCandidatoAAtributo, menor, Constante.TERMO_NAO_ENCONTRADO_LEXICO_, atributosHashSet, isr);
        }

        raiz.add(raizEhAtributo);
        if (raizAtributoNaoUtilizado.getChildCount() > 0) {
            raiz.add(raizAtributoNaoUtilizado);
        }

        raiz.add(raizCandidatoAAtributo);
        DefaultTreeModel treeModel = new DefaultTreeModel(raiz);

        DefaultTreeModel treeModelErros = new DefaultTreeModel(raizE);
        DefaultTreeModel treeModelAvisos = new DefaultTreeModel(raizA);
        this.desenharRaiz(treeModelErros, treeModelAvisos, treeModel);
        Root root = new Root(treeModelErros, treeModelAvisos, treeModel);
        return root;
    }

    private void adicionarNos(DefaultMutableTreeNode raizLocal, List<String> atributos, int tipoNo, HashMap<String, Atributo> atributosHashSet, Isr isr) throws Exception {
        for (String atributo : atributos) {
            Atributo atributo1 = atributosHashSet.get(atributo);
            if (atributo1 == null) {
                atributo1 = new Atributo();
                atributo1.setNome(atributo);
                atributo1.setIdProjeto(projetoSelecionado);
                atributo1.setNomeLemma(Constante.recuperarLemmaDaPalavra(atributo));
                atributo1.setIdIsr(isr);
            }

            if (tipoNo == Constante.ATRIBUTO_APAGADO) {
                atributo1.setValidado(2);
                atributoDAO.atualiza(atributo1);
            }

            TreeProblemasNode sourceNo = new TreeProblemasNode(Constante.TERMO_NAO_ENCONTRADO_LEXICO_, atributo, atributo1);
            sourceNo.setBaseEntity(atributo1);
            if (tipoNo == Constante.ATRIBUTO_OK && atributo1.getValidado() == 1 && atributo1.getIdIsrOrigem() == null) {
                sourceNo.setTipoNode(Constante.ATRIBUTO_DUPLICADO);
            } else {
                sourceNo.setTipoNode(tipoNo);
                if (atributo1.getIdIsr() != null && atributo1.getIdIsrOrigem() != null && atributo1.getIdIsr() != atributo1.getIdIsrOrigem() && tipoNo == Constante.ATRIBUTO_OK) {
                    sourceNo.setTipoNode(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                } else {
                    Isr isr1 = isrDAO.buscaPorNomeLemma(atributo1.getNomeLemma(), projetoSelecionado.getId());
                    if (isr1 != null && tipoNo == Constante.ATRIBUTO_OK) {
                        sourceNo.setTipoNode(Constante.REQUISITOS_DE_ARMAZENAMENTO);
                    }
                }
            }
            DefaultMutableTreeNode no = new DefaultMutableTreeNode(sourceNo);
            raizLocal.add(no);

        }

    }

    private boolean existeNo(DefaultMutableTreeNode noRaizSinonimo, String chave) {
        for (Enumeration e = noRaizSinonimo.children(); e.hasMoreElements();) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) e.nextElement();
            if (child.toString().equals(chave)) {
                return true;
            }
        }
        return false;
    }

}
