package org.sprint3.model.repository;

import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.REST.CandidaturaRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidaturaWebRepository {

    CandidaturaRestRepository candidaturaRestRepository;

    public CandidaturaWebRepository() {
        candidaturaRestRepository = new CandidaturaRestRepository();
    }


    public boolean createCandidatura(CandidaturaRestDTO candidatura) throws Exception {
        boolean create= candidaturaRestRepository.createCandidatura(candidatura);
        return create;
    }

    public Optional<List<CandidaturaRestDTO>> findAllCandidaturas() {
        Optional<List<CandidaturaRestDTO>> lista = candidaturaRestRepository.findAllCandidaturas();

        return lista;
    }

    public Optional<CandidaturaRestDTO> getCandidaturaById (int codCandidatura){
        Optional<CandidaturaRestDTO> candidatura = candidaturaRestRepository.findById(codCandidatura);

        return candidatura;
    }

    public boolean updateEstadoCandidatura(CandidaturaRestDTO candidaturaParcial) throws Exception {

        boolean alterou = candidaturaRestRepository.updateEstadoCandidatura (candidaturaParcial);
        return  alterou;
    }
}
