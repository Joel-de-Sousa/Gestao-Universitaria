package org.sprint3.model.service;

import org.sprint3.model.DTO.*;
import org.sprint3.model.repository.ConviteWebRepository;
import org.sprint3.model.repository.ProjetoWebRepository;
import org.sprint3.model.repository.PropostaWebRepository;
import org.sprint3.model.repository.UtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConviteService {
    ConviteWebRepository conviteWebRepository;
    PropostaWebRepository propostaWebRepository;
    ProjetoWebRepository projetoWebRepository;
    UtilizadorWebRepository utilizadorWebRepository;
    ConviteRestDTO conviteRestDTO;

    public ConviteService() {
        conviteWebRepository = new ConviteWebRepository();
        propostaWebRepository = new PropostaWebRepository();
        projetoWebRepository = new ProjetoWebRepository();
        utilizadorWebRepository = new UtilizadorWebRepository();
        conviteRestDTO = new ConviteRestDTO();
    }

    public boolean criarNovoConvite(int codProjeto, int codEstudante, int codDocente) throws Exception {
        ConviteRestDTO convite = new ConviteRestDTO(codProjeto, codEstudante,codDocente);
        boolean create= conviteWebRepository.createConvite(convite);
        return create;
    }

    public String getListaConvitesByCodDocente (int codDocente){
        String convite;

        Optional<ConviteRestDTO> conviteRestDTO = conviteWebRepository.getConvitesByCodDocente (codDocente);
        int codConvite =conviteRestDTO.get().getCodConvite();
            if(conviteRestDTO.get().getEstado()!="PENDENTE") {


                Optional<ProjetoRestDTO> proj = projetoWebRepository.getProjetoByCodProjeto(conviteRestDTO.get().getCodProjeto());
                Optional<PropostaRestDTO> prop = propostaWebRepository.getPropostaById(proj.get().getCodProposta());

                String tituloProjeto = prop.get().getTitulo();

                Optional<UtilizadorRestDTO> util = utilizadorWebRepository.getUtilizadorById(conviteRestDTO.get().getCodEstudante());

                String nomeEstudante = String.format(util.get().getNome() + " " + util.get().getSobrenome());

                convite = String.format(codConvite + "- Convite para projecto '" + tituloProjeto + "' submetido por " + nomeEstudante);
                return convite;
           }else return null;
    }

    public ConviteRestDTO getConviteByCodConvite(int codConvite){
        Optional<ConviteRestDTO> opConvite = conviteWebRepository.getConviteByCodConvite(codConvite);
        if(opConvite.isPresent()){
        ConviteRestDTO convite = opConvite.get();
        return convite;
        }else return null;
    }

    public boolean alterarEstadoConviteAceite (int codConvite, String estado) throws Exception {

        ConviteRestDTO conviteParcial = new ConviteRestDTO(codConvite, estado);
        boolean alterou = conviteWebRepository.updateEstadoConvite(conviteParcial);
        return  alterou;
    }
}
