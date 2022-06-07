package org.sprint.controllers;

import lombok.Getter;
import org.sprint.model.service.EdicaoService;
import org.sprint.model.service.PropostaService;

import java.util.List;

public class PropostaController {
    PropostaService propostaService;
    EdicaoService edicaoService;

    public PropostaController() {
        propostaService = new PropostaService();
        edicaoService = new EdicaoService();
    }

    public List<String> getListaEdicoes() {
        return edicaoService.getListaEdicoes();
    }

    public boolean criarNovaProposta(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) throws Exception {
        boolean create = propostaService.criarNovaProposta(codUtilizador, nifOrganizacao, codEdicao, titulo, problema, objetivo);
        return create;
    }
}
