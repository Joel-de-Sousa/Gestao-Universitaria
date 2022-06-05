package org.sprint.model.repository;

import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.AnoLetivoRestRepository;

import java.util.List;
import java.util.Optional;

public class AnoLetivoWebRepository {

    AnoLetivoRestRepository anoLetivoRestRepository;

    public AnoLetivoWebRepository() {
        anoLetivoRestRepository= new AnoLetivoRestRepository();
    }

    public boolean criarNovoAnoLetivo(AnoLetivoRestDTO novo) throws Exception {
       return anoLetivoRestRepository.createAnoLetivo(novo);

    }


    public List<AnoLetivoRestDTO> getListaAnosLetivos(){

        Optional<List<AnoLetivoRestDTO>> lista =anoLetivoRestRepository.getAllAno();
        if (lista.isPresent()){
            List<AnoLetivoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            return null;
    }
}
