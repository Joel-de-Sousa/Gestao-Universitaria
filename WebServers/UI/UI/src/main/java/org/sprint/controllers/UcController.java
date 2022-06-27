package org.sprint.controllers;

import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;
import org.sprint.model.service.UcService;

import java.util.ArrayList;
import java.util.List;

public class UcController {

    UcService ucService;

    public UcController() {
        ucService = new UcService();
    }

    public List<String> getListaSiglas() {

        List<String> siglas = ucService.getListaSiglas();
        return siglas;
    }
    public List<String> getListaUnidadeCurricular() {

        List<String> uc = ucService.getListaUnidadeCurricular();
        return uc;
    }

    public String getDenominacao(String sigla) {

        String denominacao = ucService.getDenominacao(sigla);
        return denominacao;
    }


    public boolean criarNovaUC(String sigla, String denominacao) throws Exception {
        boolean create=ucService.criarNovaUC(sigla,denominacao);
        return create;
    }
}
