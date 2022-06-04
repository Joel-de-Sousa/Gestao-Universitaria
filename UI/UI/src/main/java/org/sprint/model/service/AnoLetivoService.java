package org.sprint.model.service;

import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.AnoLetivoWebRepository;
import org.sprint.model.repository.REST.AnoLetivoRestRepository;

import java.util.ArrayList;
import java.util.List;

public class AnoLetivoService {

    AnoLetivoWebRepository anoLetivoWebRepository;


    public AnoLetivoService() {
        anoLetivoWebRepository= new AnoLetivoWebRepository();
    }

    public List<String> getListaAnos(){
        List<String> anos=new ArrayList<>();
        List<AnoLetivoRestDTO> lista =anoLetivoWebRepository.getListaAnosLetivos();
        for (AnoLetivoRestDTO a: lista) {
            anos.add(a.getAno());
        }
        return anos;
    }


    public boolean criarNovoAnoLetivo(String ano){
        AnoLetivoRestDTO novo= new AnoLetivoRestDTO(ano);
        boolean valid=anoLetivoWebRepository.criarNovoAnoLetivo(novo);
        return valid;
    }
}
