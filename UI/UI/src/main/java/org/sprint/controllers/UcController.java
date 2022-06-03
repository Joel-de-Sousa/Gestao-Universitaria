package org.sprint.controllers;

import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;

import java.util.ArrayList;
import java.util.List;

public class UcController {

    UcRestRepository ucRestRepository= new UcRestRepository();
    public UcController() {
    }

    public List<String> getListaSiglas(){

        List<UcRestDTO> lista =ucRestRepository.getAllUc().get();
        List<String> siglas=new ArrayList<>();
        for (UcRestDTO a: lista) {
            siglas.add(a.getSigla());

        }


        return siglas;
    }

    public String getDenominacao(String value){
        List<UcRestDTO> lista =ucRestRepository.getAllUc().get();
        String denominacao="" ;
        for (UcRestDTO a: lista) {
            if (a.getSigla().equals(value)){
            denominacao=a.getDenominacao();}
        }
       return denominacao;
    }


    public boolean criarNovaUC(String sigla, String denominacao){
        UcRestDTO novo= new UcRestDTO(sigla,denominacao);
        return ucRestRepository.createUc(novo).isPresent();
    }
}
