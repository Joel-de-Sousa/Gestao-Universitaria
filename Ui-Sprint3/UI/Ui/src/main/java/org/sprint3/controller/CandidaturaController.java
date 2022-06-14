package org.sprint3.controller;

import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.repository.REST.CandidaturaRestRepository;

public class CandidaturaController {

    CandidaturaRestRepository candidaturaRestRepository;

    public CandidaturaController() {
        candidaturaRestRepository = new CandidaturaRestRepository();
    }


    public boolean criarNovoCandidatura(int codProposta, int codEstudante) throws Exception {
        CandidaturaRestDTO candidatura = new CandidaturaRestDTO(codProposta, codEstudante);
        boolean create= candidaturaRestRepository.createCandidatura(candidatura);
        return create;
    }
}
