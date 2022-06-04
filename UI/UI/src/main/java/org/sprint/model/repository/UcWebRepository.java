package org.sprint.model.repository;

import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UcWebRepository {
    UcRestRepository ucRestRepository;
    public UcWebRepository() {
        ucRestRepository= new UcRestRepository();
    }

    public List<UcRestDTO> getListaUc(){

        Optional<List<UcRestDTO>>lista =ucRestRepository.getAllUc();
       if (lista.isPresent()){
           List<UcRestDTO> listaDTO=lista.get();
           return listaDTO;
       }else
           return null;
    }



    public boolean criarNovaUC(UcRestDTO novo){
        if (ucRestRepository.createUc(novo).isPresent()){
            return true;
        }
        return false;
    }
}
