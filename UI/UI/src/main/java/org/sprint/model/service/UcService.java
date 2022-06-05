package org.sprint.model.service;

import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.UcWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UcService {

    UcWebRepository ucWebRepository;


    public UcService() {
        ucWebRepository=new UcWebRepository();
    }


    public List<String> getListaSiglas(){
        List<String> siglas=new ArrayList<>();
        List<UcRestDTO> lista =ucWebRepository.getListaUc();
        for (UcRestDTO a: lista) {
            siglas.add(a.getSigla());
        }
        return siglas;
    }
    public List<String> getListaUnidadeCurricular(){
        List<String> unidadeCurricular=new ArrayList<>();
        String siglaDenon="";
        List<UcRestDTO> lista =ucWebRepository.getListaUc();
        for (UcRestDTO a: lista) {
            siglaDenon=String.format(a.getSigla()+"-"+a.getDenominacao());
            unidadeCurricular.add(siglaDenon);
        }
        return unidadeCurricular;
    }

    public String getDenominacao(String sigla){
        List<UcRestDTO> lista =ucWebRepository.getListaUc();
        String denominacao="";
        for (UcRestDTO a: lista) {
            if (a.getSigla().equals(sigla)){
                denominacao=a.getDenominacao();}
        }
        return denominacao;
    }


    public boolean criarNovaUC(String sigla , String denominacao) throws Exception {
        UcRestDTO novo= new UcRestDTO(sigla,denominacao);
        boolean valid=ucWebRepository.criarNovaUC(novo);
        return valid;
    }


}
