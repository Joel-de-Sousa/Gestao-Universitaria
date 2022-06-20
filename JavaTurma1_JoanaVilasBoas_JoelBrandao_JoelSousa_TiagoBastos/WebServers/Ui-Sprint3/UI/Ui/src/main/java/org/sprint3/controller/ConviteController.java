package org.sprint3.controller;

import org.sprint3.model.DTO.ConviteRestDTO;
import org.sprint3.model.repository.REST.ConviteRestRepository;
import org.sprint3.model.repository.REST.PropostaRestRepository;
import org.sprint3.model.service.ConviteService;

import java.util.List;

public class ConviteController {

    ConviteService conviteService;

    public ConviteController() {
        conviteService = new ConviteService();
    }


    public boolean criarNovoConvite(int codEstudante, int codDocente, int codProjeto) throws Exception {
        boolean create= conviteService.criarNovoConvite(codEstudante, codDocente, codProjeto);
        return create;
    }

    public List<String> getAllConvitesByCodDocente(int codDocente) {
        return conviteService.getListaConvitesByCodDocente(codDocente);
    }

    public ConviteRestDTO getConviteByCodConvite(int codConvite){
        return conviteService.getConviteByCodConvite(codConvite);
    }

    public boolean alterarEstadoConvite(int codConvite, String estado) throws Exception {

        boolean alterou = conviteService.alterarEstadoConviteAceite(codConvite, estado);
        return alterou;
    }

}
