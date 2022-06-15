package org.sprint3.model.repository;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.repository.REST.EdicaoRestRepository;

import java.util.List;
import java.util.Optional;

public class EdicaoWebRepository {
    EdicaoRestRepository edicaoRestRepository;

    public EdicaoWebRepository() {
        edicaoRestRepository=new EdicaoRestRepository();
    }
    public List<EdicaoRestDTO> getListaEdicoesByCodRUC(){

        Optional<List<EdicaoRestDTO>> lista =edicaoRestRepository.getAllEdicoesByCodRUC();
        if (lista.isPresent()){
            List<EdicaoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            return null;
    }
}
