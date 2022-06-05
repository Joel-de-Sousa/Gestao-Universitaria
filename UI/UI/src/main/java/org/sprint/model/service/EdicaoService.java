package org.sprint.model.service;

import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.EdicaoWebRepository;

import java.util.ArrayList;
import java.util.List;

public class EdicaoService {
    EdicaoWebRepository edicaoWebRepository;


    public EdicaoService() {
        edicaoWebRepository= new EdicaoWebRepository();
    }

    public boolean criarNovaEdicao(int ano , int uc) throws Exception {
        EdicaoRestDTO novo= new EdicaoRestDTO(ano,uc);
        boolean valid=edicaoWebRepository.criarNovaEdicao(novo);
        return valid;
    }

    public List<String> getListaEdicoes(){
        List<String> edicoes=new ArrayList<>();
        List<EdicaoRestDTO> lista =edicaoWebRepository.getListaEdicoes();
        for (EdicaoRestDTO a: lista) {
            String sigla= a.getSigla();
            String ano = a.getAno();
            String edicao = String.format(sigla+": "+ano);
            edicoes.add(edicao);
        }
        return edicoes;
    }


}
