package org.sprint3.model.service;


import org.sprint3.model.DTO.AnoLetivoRestDTO;
import org.sprint3.model.repository.AnoLetivoWebRepository;

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


    public boolean criarNovoAnoLetivo(String ano) throws Exception {
        AnoLetivoRestDTO novo= new AnoLetivoRestDTO(ano);
        boolean valid=anoLetivoWebRepository.criarNovoAnoLetivo(novo);
        return valid;
    }
}
