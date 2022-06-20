package org.sprint3.controller;

import org.sprint3.model.service.UcService;

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
