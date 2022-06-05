package org.sprint.model.repository;

import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.EdicaoRestRepository;

import java.util.List;
import java.util.Optional;

public class EdicaoWebRepository {

    EdicaoRestRepository edicaoRestRepository;

    public EdicaoWebRepository() {
        edicaoRestRepository=new EdicaoRestRepository();
    }

    public boolean criarNovaEdicao(EdicaoRestDTO novo) throws Exception {
        return edicaoRestRepository.createEdicao(novo);

    }


    public List<EdicaoRestDTO> getListaEdicoes(){

        Optional<List<EdicaoRestDTO>> lista =edicaoRestRepository.getAllEdicoes();
        if (lista.isPresent()){
            List<EdicaoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            return null;
    }
}
