package org.sprint3.model.service;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.repository.EdicaoWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EdicaoService {

    EdicaoWebRepository edicaoWebRepository;


    public EdicaoService() {
        edicaoWebRepository = new EdicaoWebRepository();
    }

    public Optional<EdicaoRestDTO> getEdicaoByCodRUC(int codRUC) {
        Optional<EdicaoRestDTO> opEdicao = edicaoWebRepository.findByEdicaoByCodRUC(codRUC);

        return opEdicao;
    }

    public Optional<EdicaoRestDTO> getEdicaoByCodEstudante(int codEstudante) {
        Optional<EdicaoRestDTO> opEdicao = edicaoWebRepository.findByEdicaoByCodRUC(codEstudante);

        return opEdicao;
    }

    public List<String> getListaEdicoesByCodRUC(int codRuc) {
        List<String> edicoes = new ArrayList<>();

        List<EdicaoRestDTO> lista = edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
        for (EdicaoRestDTO a : lista) {
            String sigla = a.getSigla();
            String ano = a.getAno();
            String edicao = String.format(sigla + ": " + ano);
            edicoes.add(edicao);
        }
        return edicoes;
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
