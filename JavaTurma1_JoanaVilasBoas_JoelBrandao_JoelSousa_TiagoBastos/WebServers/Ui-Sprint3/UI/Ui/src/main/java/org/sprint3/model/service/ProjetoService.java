package org.sprint3.model.service;

import org.sprint3.model.DTO.ConviteRestDTO;
import org.sprint3.model.DTO.JuriRestDTO;
import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.repository.ProjetoWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoService {


    ProjetoWebRepository projetoWebRepository;
    public ProjetoService() {
        projetoWebRepository = new ProjetoWebRepository();
    }

    public ProjetoRestDTO getProjetoByCodEstudante (int codEstudante){
        Optional<ProjetoRestDTO> projeto = projetoWebRepository.getProjetoByCodEstudante (codEstudante);

        ProjetoRestDTO projeto2 = projeto.get();

        return projeto2;
    }

    public ProjetoRestDTO getProjetoByCodOrientador (int codOrientador){
        Optional<ProjetoRestDTO> projeto = projetoWebRepository.getProjetoByCodOrientador (codOrientador);

        ProjetoRestDTO projeto2 = projeto.get();

        return projeto2;
    }

    public ProjetoRestDTO getProjetoByCodProjeto (int CodProjeto){
        Optional<ProjetoRestDTO> projeto = projetoWebRepository.getProjetoByCodProjeto (CodProjeto);

        ProjetoRestDTO projeto2 = projeto.get();

        return projeto2;
    }

    public List<String> getAllProjetos (){
        Optional<List<ProjetoRestDTO>> lista = projetoWebRepository.getAllProjetos();

        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()){
            for (ProjetoRestDTO a:lista.get()) {
                listaString.add(String.format(a.getCodProjeto()+"-"+a.getTituloProjeto()+"-"+a.getMomentoAvaliacao()));
            }
            return listaString;
        }else
            return null;
    }

    public List<String> getListaProjetosByCodOrientador (int codOrientador){
        Optional<List<ProjetoRestDTO>> lista = projetoWebRepository.getListaProjetosByCodOrientador (codOrientador);

        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()){
            for (ProjetoRestDTO a:lista.get()) {
                listaString.add(String.format(a.getCodProjeto()+"-"+a.getTituloProjeto()+"-"+a.getMomentoAvaliacao()));
            }
            return listaString;
        }else
            return null;
    }

    public List<String> getAllProjetosDeEdicao (int edicao){
        Optional<List<ProjetoRestDTO>> lista = projetoWebRepository.getAllProjetosDeEdicao (edicao);

        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()){
            for (ProjetoRestDTO a:lista.get()) {
                listaString.add(String.format(a.getCodProjeto()+"-"+a.getTituloProjeto()+"-"+a.getMomentoAvaliacao()));
            }
            return listaString;
        }else
            return null;
    }

    public boolean criarNovoJuri (String orientador, String arguente, String presidente) throws Exception {
        JuriRestDTO juri = new JuriRestDTO (orientador,arguente,presidente);
        boolean create= projetoWebRepository.createJuri (juri);
        return create;
    }
}
