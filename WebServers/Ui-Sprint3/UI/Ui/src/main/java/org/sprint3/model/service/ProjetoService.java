package org.sprint3.model.service;

import org.sprint3.model.DTO.*;
import org.sprint3.model.repository.ProjetoWebRepository;
import org.sprint3.model.repository.PropostaWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoService {


    ProjetoWebRepository projetoWebRepository;
    PropostaWebRepository propostaWebRepository;
    public ProjetoService() {
        projetoWebRepository = new ProjetoWebRepository();
    }

    public ProjetoRestDTO getProjetoByCodEstudante (int codEstudante){

        Optional<ProjetoRestDTO> projeto = projetoWebRepository.getProjetoByCodEstudante (codEstudante);

        if(projeto.isPresent()){
        ProjetoRestDTO projeto2 = projeto.get();

        return projeto2;}
        else return null;
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
                Optional<PropostaRestDTO> prop = propostaWebRepository.getPropostaById(a.getCodProposta());
                listaString.add(String.format(a.getCodProjeto()+"-"+prop.get().getTitulo()));
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
                Optional<PropostaRestDTO> prop = propostaWebRepository.getPropostaById(a.getCodProposta());

                listaString.add(String.format(a.getCodProjeto()+"-"+prop.get().getTitulo()));
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
                Optional<PropostaRestDTO> prop = propostaWebRepository.getPropostaById(a.getCodProposta());

                listaString.add(String.format(a.getCodProjeto()+"-"+prop.get().getTitulo()));
            }
            return listaString;
        }else
            return null;
    }

    public boolean criarNovoJuri (String presidente, String orientador, String arguente) throws Exception {
        JuriRestDTO juri = new JuriRestDTO (presidente, orientador,arguente);
        boolean create= projetoWebRepository.createJuri (juri);
        return create;
    }

    public boolean alterarEstadoSubmissao (int codSubmissao, String estado) throws Exception {

        SubmissaoRestDTO submissaoParcial = new SubmissaoRestDTO(codSubmissao, estado);
        boolean alterou = projetoWebRepository.updateEstadoSubmissao (submissaoParcial);
        return  alterou;
    }
}
