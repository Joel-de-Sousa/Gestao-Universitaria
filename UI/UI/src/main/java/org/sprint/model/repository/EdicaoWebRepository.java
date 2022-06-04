package org.sprint.model.repository;

import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.EdicaoRestRepository;

public class EdicaoWebRepository {

    EdicaoRestRepository edicaoRestRepository;

    public EdicaoWebRepository() {
        edicaoRestRepository=new EdicaoRestRepository();
    }

    public boolean criarNovaEdicao(EdicaoRestDTO novo){
        if (edicaoRestRepository.createEdicao(novo).isPresent()){
            return true;
        }
        return false;
    }
}
