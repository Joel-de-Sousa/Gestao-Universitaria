package org.sprint3.model.repository;

import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.REST.UtilizadorRestRepository;

import java.util.List;
import java.util.Optional;

public class UtilizadorWebRepository {

    UtilizadorRestRepository utilizadorRestRepository;

    public UtilizadorWebRepository() {
        utilizadorRestRepository = new UtilizadorRestRepository();
    }

    public Optional<UtilizadorRestDTO> getUtilizadorById (int codUtilizador){

        Optional<UtilizadorRestDTO> opUtilizador = utilizadorRestRepository.findById(codUtilizador);

        if ( opUtilizador.isPresent() ) {
            return opUtilizador;
        }
        else
            return Optional.empty();
    }


    public Optional<List<UtilizadorRestDTO>> findAllDocentes (){
        Optional<List<UtilizadorRestDTO>> lista =utilizadorRestRepository.findAllUtilizadores();

        if (lista.isPresent()){
            return lista;
        }else
            return Optional.empty();
    }
}
