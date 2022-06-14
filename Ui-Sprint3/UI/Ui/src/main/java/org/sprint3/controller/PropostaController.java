package org.sprint3.controller;

import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.REST.PropostaRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropostaController {

    PropostaRestRepository propostaRestRepository;
    public PropostaController() {
        propostaRestRepository = new PropostaRestRepository();
    }

    public List<String> getAllPropostas (){
        Optional<List<PropostaRestDTO>> lista = propostaRestRepository.findAllPropostas();
        List<String> listaString = new ArrayList<>();
        if (lista.isPresent()){

            for (PropostaRestDTO a:lista.get()) {
                listaString.add(String.format(a.getCodProposta()+"-"+a.getTitulo()));

            }
            return listaString;
        }else
            return null;
    }

    /*public UtilizadorRestDTO getUtilizadorById (int codUtilizador){
        Optional<UtilizadorRestDTO> utilizador = utilizadorRestRepository.findById(codUtilizador);

        UtilizadorRestDTO utilizador2 = utilizador.get();

        return utilizador2;
    }*/
}
