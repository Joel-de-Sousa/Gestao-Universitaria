package org.sprint3.model.service;

import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.UtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilizadorService {

    UtilizadorWebRepository utilizadorWebRepository;

    public UtilizadorService() {
        utilizadorWebRepository = new UtilizadorWebRepository();
    }

    public UtilizadorRestDTO getUtilizadorById (int codUtilizador){

        Optional<UtilizadorRestDTO> opUtilizador = utilizadorWebRepository.getUtilizadorById(codUtilizador);

        UtilizadorRestDTO utilizador = opUtilizador.get();

        return utilizador;
    }

    public List<String> getAllDocentes (){

        Optional<List<UtilizadorRestDTO>> lista =utilizadorWebRepository.findAllDocentes ();
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
