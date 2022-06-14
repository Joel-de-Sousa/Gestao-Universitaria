package org.sprint3.controller;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.REST.EdicaoRestRepository;
import org.sprint3.model.repository.REST.UtilizadorRestRepository;
import org.sprint3.model.service.EdicaoService;

import java.util.List;
import java.util.Optional;

public class EdicaoController {

    EdicaoRestRepository edicaoRestRepository;
    EdicaoService edicaoService;

    public EdicaoController() {
        edicaoRestRepository = new EdicaoRestRepository();
        edicaoService = new EdicaoService();
    }


    public EdicaoRestDTO getEdicaoByCodRUC (int codRUC){
        Optional<EdicaoRestDTO> edicao = edicaoRestRepository.findByEdicaoByCodRUC(codRUC);

        EdicaoRestDTO edicao2 = edicao.get();

        return edicao2;
    }

    public List<String> getListaEdicoesByCodRUC() {
        return edicaoService.getListaEdicoesByCodRUC();
    }
}
