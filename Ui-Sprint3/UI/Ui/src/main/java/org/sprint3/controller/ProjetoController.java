package org.sprint3.controller;

import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.REST.ProjetoRestRepository;
import org.sprint3.model.repository.REST.UtilizadorRestRepository;

import java.util.Optional;

public class ProjetoController {

    ProjetoRestRepository projetoRestRepository;
    public ProjetoController() {
        projetoRestRepository = new ProjetoRestRepository();
    }

    public ProjetoRestDTO getProjetoByCodEstudante (int codEstudante){
        Optional<ProjetoRestDTO> projeto = projetoRestRepository.findByCodEstudante(codEstudante);

        ProjetoRestDTO projeto2 = projeto.get();

        return projeto2;
    }
}
