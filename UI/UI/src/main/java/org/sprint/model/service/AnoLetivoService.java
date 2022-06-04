package org.sprint.model.service;

import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.AnoLetivoWebRepository;
import org.sprint.model.repository.REST.AnoLetivoRestRepository;

public class AnoLetivoService {

    AnoLetivoWebRepository anoLetivoWebRepository;


    public AnoLetivoService() {
        anoLetivoWebRepository= new AnoLetivoWebRepository();
    }

    public boolean criarNovoAnoLetivo(String ano){
        AnoLetivoRestDTO novo= new AnoLetivoRestDTO(ano);
        boolean valid=anoLetivoWebRepository.criarNovoAnoLetivo(novo);
        return valid;
    }
}
