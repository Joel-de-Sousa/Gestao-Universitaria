package org.sprint.model.repository;

import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.AnoLetivoRestRepository;

public class AnoLetivoWebRepository {

    AnoLetivoRestRepository anoLetivoRestRepository;

    public AnoLetivoWebRepository() {
        anoLetivoRestRepository= new AnoLetivoRestRepository();
    }

    public boolean criarNovoAnoLetivo(AnoLetivoRestDTO novo){
        if (anoLetivoRestRepository.createAnoLetivo(novo).isPresent()){
            return true;
        }
        return false;
    }
}
