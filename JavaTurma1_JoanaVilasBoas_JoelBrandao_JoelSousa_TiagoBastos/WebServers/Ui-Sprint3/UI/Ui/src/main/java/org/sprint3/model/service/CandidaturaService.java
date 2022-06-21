package org.sprint3.model.service;

import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.CandidaturaAllArgsDTO;
import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.CandidaturaWebRepository;
import org.sprint3.model.repository.UtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CandidaturaService {

    CandidaturaWebRepository candidaturaWebRepository;
    UtilizadorWebRepository utilizadorWebRepository;

    public CandidaturaService() {
        candidaturaWebRepository = new CandidaturaWebRepository();
        utilizadorWebRepository=new UtilizadorWebRepository();
    }


    public boolean criarNovaCandidatura(int codProposta, int codEstudante) throws Exception {
        CandidaturaRestDTO candidatura = new CandidaturaRestDTO(codProposta, codEstudante);
        boolean create = candidaturaWebRepository.createCandidatura(candidatura);
        return create;
    }


    public List<String> getAllCandidaturas() {
        Optional<List<CandidaturaAllArgsDTO>> lista = candidaturaWebRepository.findAllCandidaturas();

        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()) {
            for (CandidaturaAllArgsDTO a : lista.get()) {
                Optional<UtilizadorRestDTO> utilizador = utilizadorWebRepository.getUtilizadorById(a.getCodEstudante());

                if (a.getEstado().equals("PENDENTE")) {
                    listaString.add(String.format(a.getCodCandidatura() + "- " +utilizador.get().getNome() + " " + utilizador.get().getSobrenome()));
                }
            }
            return listaString;
        } else
            return null;
    }


    public CandidaturaRestDTO getCandidaturaById (int codCandidatura){
        Optional<CandidaturaRestDTO> candidatura = candidaturaWebRepository.getCandidaturaById(codCandidatura);

        CandidaturaRestDTO candidatura2 = candidatura.get();

        return candidatura2;
    }

    public CandidaturaRestDTO getCandidaturaByCodEstudante (int codEstudante){
        Optional<CandidaturaRestDTO> candidatura = candidaturaWebRepository.getCandidaturaByCodEstudante(codEstudante);

        if (candidatura.isPresent()){
        CandidaturaRestDTO candidatura2 = candidatura.get();

        return candidatura2;}
        else return null;
    }

    public boolean alterarEstadoCandidatura (int codCand, String estado) throws Exception {

        CandidaturaRestDTO candidaturaParcial = new CandidaturaRestDTO(codCand, estado);
        boolean alterou = candidaturaWebRepository.updateEstadoCandidatura(candidaturaParcial);
        return  alterou;
    }
}
