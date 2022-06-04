package org.sprint.controllers;

import org.sprint.model.service.EdicaoService;

public class EdicaoController {

    EdicaoService edicaoService;

    public EdicaoController() {
        edicaoService=new EdicaoService();
    }

    public boolean criarNovaEdicao(int ano, int uc){
        boolean create=edicaoService.criarNovaEdicao(ano,uc);
        return create;
    }
}
