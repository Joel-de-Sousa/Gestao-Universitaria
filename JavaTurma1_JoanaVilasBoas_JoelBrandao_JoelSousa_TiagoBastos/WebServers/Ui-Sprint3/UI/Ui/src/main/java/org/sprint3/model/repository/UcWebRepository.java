package org.sprint3.model.repository;


import org.sprint3.model.DTO.UcRestDTO;
import org.sprint3.model.repository.REST.UcRestRepository;

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



    public boolean criarNovaUC(UcRestDTO novo) throws Exception {

            return ucRestRepository.createUc(novo);



    }
}
