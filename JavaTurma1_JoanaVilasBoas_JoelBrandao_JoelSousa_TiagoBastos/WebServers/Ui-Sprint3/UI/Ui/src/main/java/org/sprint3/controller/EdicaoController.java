package org.sprint3.controller;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.repository.REST.EdicaoRestRepository;
import org.sprint3.model.service.EdicaoService;

import java.util.List;
import java.util.Optional;

public class EdicaoController {
    EdicaoService edicaoService;

    public EdicaoController() {
        edicaoService = new EdicaoService();
    }

    public EdicaoRestDTO getEdicaoByCodRUC (int codRUC){
        Optional<EdicaoRestDTO> opEdicao = edicaoService.getEdicaoByCodRUC(codRUC);

        EdicaoRestDTO edicao = opEdicao.get();

        return edicao;
    }

    public String getEdicaoByCodUtilizador (int codUtilizador){
        Optional<EdicaoRestDTO> opEdicao = edicaoService.getEdicaoByCodRUC(codUtilizador);

        EdicaoRestDTO edicao = opEdicao.get();

        String sigla= edicao.getSigla();
        String ano = edicao.getAno();
        String edicaoString = String.format(sigla+" "+ano);

        return edicaoString;
    }

    public int getCodEdicaoEstudante (int codUtilizador){
        Optional<EdicaoRestDTO> opEdicao = edicaoService.getEdicaoByCodRUC(codUtilizador);

        EdicaoRestDTO edicao = opEdicao.get();

        int codEdicao = edicao.getCodEdicao();
        return codEdicao;
    }

    public List<String> getListaEdicoesByCodRUC(int codRUC) {
        return edicaoService.getListaEdicoesByCodRUC(codRUC);
    }

    public boolean criarNovaEdicao(int ano, int uc) throws Exception {
        boolean create=edicaoService.criarNovaEdicao(ano,uc);
        return create;
    }

    public List<String> getListaEdicoes() {
        return edicaoService.getListaEdicoes();
    }

}
