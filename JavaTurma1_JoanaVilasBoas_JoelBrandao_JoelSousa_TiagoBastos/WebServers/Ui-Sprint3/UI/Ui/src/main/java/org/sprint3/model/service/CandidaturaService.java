package org.sprint3.model.service;

import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.CandidaturaWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidaturaService {

    CandidaturaWebRepository candidaturaWebRepository;

    public CandidaturaService() {
        candidaturaWebRepository = new CandidaturaWebRepository();
    }


    public boolean criarNovaCandidatura(int codProposta, int codEstudante) throws Exception {
        CandidaturaRestDTO candidatura = new CandidaturaRestDTO(codProposta, codEstudante);
        boolean create = candidaturaWebRepository.createCandidatura(candidatura);
        return create;
    }


    public List<String> getAllCandidaturas() {
        Optional<List<CandidaturaRestDTO>> lista = candidaturaWebRepository.findAllCandidaturas();

        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()) {
            for (CandidaturaRestDTO a : lista.get()) {
                if (a.getEstado().equals("PENDENTE")) {
                    listaString.add(String.format(a.getCodCandidatura() + "- " + a.getNomeEstudante() + " " + a.getSobrenomeEstudante()));
                }
            }
            return listaString;
        } else
            return null;
    }


    public CandidaturaRestDTO getCandidaturaById (int codCandidatura){
        Optional<CandidaturaRestDTO> candidatura = candidaturaWebRepository.getCandidaturaById(codCandidatura);

        CandidaturaRestDTO candidatura2 = candidatura.get();

        return candidatura2;
    }

    public CandidaturaRestDTO getCandidaturaByCodEstudante (int codEstudante){
        Optional<CandidaturaRestDTO> candidatura = candidaturaWebRepository.getCandidaturaByCodEstudante(codEstudante);

        CandidaturaRestDTO candidatura2 = candidatura.get();

        return candidatura2;
    }

    public boolean alterarEstadoCandidatura (int codCand, String estado) throws Exception {

        CandidaturaRestDTO candidaturaParcial = new CandidaturaRestDTO(codCand, estado);
        boolean alterou = candidaturaWebRepository.updateEstadoCandidatura(candidaturaParcial);
        return  alterou;
    }
}
