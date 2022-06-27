package org.sprint3.controller;

import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.REST.CandidaturaRestRepository;
import org.sprint3.model.service.CandidaturaService;

import java.util.List;

public class CandidaturaController {

    CandidaturaService candidaturaService;

    public CandidaturaController() {
        candidaturaService = new CandidaturaService();
    }


    public boolean criarNovoCandidatura(int codProposta, int codEstudante) throws Exception {
        boolean create= candidaturaService.criarNovaCandidatura(codProposta, codEstudante);
        return create;
    }

    public List<String> getAllCandidaturas(){
        List<String> lista = candidaturaService.getAllCandidaturas();
        return lista;
    }

    public CandidaturaRestDTO getCandidaturaById(int codCandidatura) {
        CandidaturaRestDTO candidatura = candidaturaService.getCandidaturaById(codCandidatura);
        return candidatura;
    }

    public CandidaturaRestDTO getCandidaturaByCodEstudante(int codEstudante) {

        CandidaturaRestDTO candidatura = candidaturaService.getCandidaturaByCodEstudante(codEstudante);
        return candidatura;
    }

    public boolean alterarEstadoCandidatura (int codCand, String estado) throws Exception {

        boolean alterou = candidaturaService.alterarEstadoCandidatura(codCand, estado);
        return alterou;
    }
}
