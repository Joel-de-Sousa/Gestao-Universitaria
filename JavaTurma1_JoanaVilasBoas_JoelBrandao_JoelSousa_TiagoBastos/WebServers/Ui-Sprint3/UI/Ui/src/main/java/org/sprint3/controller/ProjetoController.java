package org.sprint3.controller;

import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.service.ProjetoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoController {

    ProjetoService projetoService;
    public ProjetoController() {
        projetoService = new ProjetoService();
    }

    public ProjetoRestDTO getProjetoByCodEstudante (int codEstudante){
        ProjetoRestDTO projeto = projetoService.getProjetoByCodEstudante(codEstudante);

        return projeto;
    }

    public ProjetoRestDTO getProjetoByCodOrientador (int codOrientador){
        ProjetoRestDTO projeto = projetoService.getProjetoByCodOrientador(codOrientador);
        return projeto;
    }

    public ProjetoRestDTO getProjetoByCodProjeto (int CodProjeto){
        ProjetoRestDTO projeto = projetoService.getProjetoByCodProjeto(CodProjeto);
        return projeto;
    }

    public List<String> getAllProjetos (){
        List<String> lista = projetoService.getAllProjetos();
        if(lista!=null)
        return lista;
        else return null;
    }

    public List<String> getListaProjetosByCodOrientador (int codOrientador){
        List<String> lista = projetoService.getListaProjetosByCodOrientador(codOrientador);
        if(lista!=null)
            return lista;
        else return null;
    }

    public List<String> getAllProjetosDeEdicao (int edicao){
        List<String> lista = projetoService.getAllProjetosDeEdicao(edicao);
        if(lista!=null)
            return lista;
        else return null;
    }

    public boolean criarNovoJuri (String orientador, String arguente, String presidente) throws Exception {
        boolean create= projetoService.criarNovoJuri (orientador, arguente, presidente);
        return create;
    }

    public boolean alterarEstadoSubmissao (int codSubmissao, String estado) throws Exception {

        boolean alterou = projetoService.alterarEstadoSubmissao (codSubmissao, estado);
        return alterou;
    }
}
