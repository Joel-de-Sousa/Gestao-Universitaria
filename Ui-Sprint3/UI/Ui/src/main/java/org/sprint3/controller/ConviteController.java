package org.sprint3.controller;

import org.sprint3.model.repository.REST.ConviteRestRepository;
import org.sprint3.model.repository.REST.PropostaRestRepository;

public class ConviteController {

    ConviteRestRepository conviteRestRepository;

    public ConviteController() {
        conviteRestRepository = new ConviteRestRepository();
    }


    /*public boolean criarNovoConvite(int codEstudante, int codDocente, int codProjeto) throws Exception {
        boolean create=ucService.criarNovaUC(sigla,denominacao);
        return create;
    }*/
}
