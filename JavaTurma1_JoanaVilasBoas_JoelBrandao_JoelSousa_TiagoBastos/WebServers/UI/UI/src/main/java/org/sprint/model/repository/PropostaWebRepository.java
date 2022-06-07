package org.sprint.model.repository;

import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.PropostaRestRepository;

import java.util.List;
import java.util.Optional;

public class PropostaWebRepository {

    PropostaRestRepository propostaRestRepository;

    public PropostaWebRepository() {
        propostaRestRepository= new PropostaRestRepository();
    }

    public boolean criarNovaProposta(PropostaRestDTO novo) throws Exception {
        return propostaRestRepository.createProposta(novo);
    }


}
