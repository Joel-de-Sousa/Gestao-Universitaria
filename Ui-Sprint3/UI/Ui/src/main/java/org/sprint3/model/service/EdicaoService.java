package org.sprint3.model.service;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.repository.EdicaoWebRepository;

import java.util.ArrayList;
import java.util.List;

public class EdicaoService {

    EdicaoWebRepository edicaoWebRepository;


    public EdicaoService() {
        edicaoWebRepository = new EdicaoWebRepository();
    }

    public List<String> getListaEdicoesByCodRUC(){
        List<String> edicoes=new ArrayList<>();

        List<EdicaoRestDTO> lista =edicaoWebRepository.getListaEdicoesByCodRUC();
        for (EdicaoRestDTO a: lista) {
            String sigla= a.getSigla();
            String ano = a.getAno();
            String edicao = String.format(sigla+": "+ano);
            edicoes.add(edicao);
        }
        return edicoes;
    }
}
