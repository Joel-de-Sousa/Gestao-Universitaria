package org.sprint.model.service;

import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.EdicaoWebRepository;

public class EdicaoService {
    EdicaoWebRepository edicaoWebRepository;


    public EdicaoService() {
        edicaoWebRepository= new EdicaoWebRepository();
    }

    public boolean criarNovaEdicao(int ano , int uc){
        EdicaoRestDTO novo= new EdicaoRestDTO(ano,uc);
        boolean valid=edicaoWebRepository.criarNovaEdicao(novo);
        return valid;
    }
}
