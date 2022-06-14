package org.sprint3.controller;

import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.REST.UtilizadorRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilizadorController {

    UtilizadorRestRepository utilizadorRestRepository;
    public UtilizadorController() {
        utilizadorRestRepository = new UtilizadorRestRepository();
    }


    public UtilizadorRestDTO getUtilizadorById (int codUtilizador){
        Optional<UtilizadorRestDTO> utilizador = utilizadorRestRepository.findById(codUtilizador);

        UtilizadorRestDTO utilizador2 = utilizador.get();

        return utilizador2;
    }

    public List<String> getAllUtilizadores (){
        Optional<List<UtilizadorRestDTO>> lista =utilizadorRestRepository.findAllUtilizadores();
        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()){
            for (UtilizadorRestDTO a:lista.get()) {
                listaString.add(String.format(a.getCodUtilizador()+"-"+a.getNome()+" "+a.getSobrenome()));
            }
            return listaString;
        }else
            return null;
    }

}