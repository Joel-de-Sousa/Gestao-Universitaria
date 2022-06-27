package org.sprint3.model.service;

import org.sprint3.model.DTO.ConviteRestDTO;
import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.ConviteWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConviteService {
    ConviteWebRepository conviteWebRepository;

    public ConviteService() {
        conviteWebRepository = new ConviteWebRepository();
    }

    public boolean criarNovoConvite(int codEstudante, int codDocente, int codProjeto) throws Exception {
        ConviteRestDTO convite = new ConviteRestDTO(codEstudante,codDocente,codProjeto);
        boolean create= conviteWebRepository.createConvite(convite);
        return create;
    }

    public List<String> getListaConvitesByCodDocente (int codDocente){
        List<String> convites = new ArrayList<>();

        List<ConviteRestDTO> lista = conviteWebRepository.getListaConvitesByCodDocente (codDocente);
        for (ConviteRestDTO a: lista) {
            if(a.getEstado()=="PENDENTE") {
                int codConvite = a.getCodConvite();

                String tituloProjeto = a.getTituloProjeto();
                String nomeEstudante = String.format(a.getNomeEstudante() + " " + a.getSobrenomeEstudante());
                String convite = String.format(codConvite + "- Convite para projecto '" + tituloProjeto + "' submetido por " + nomeEstudante);
                convites.add(convite);
            }}
        return convites;
    }

    public ConviteRestDTO getConviteByCodConvite(int codConvite){
        Optional<ConviteRestDTO> opConvite = conviteWebRepository.getConviteByCodConvite(codConvite);

        ConviteRestDTO convite = opConvite.get();
        return convite;
    }

    public boolean alterarEstadoConviteAceite (int codConvite, String estado) throws Exception {

        ConviteRestDTO conviteParcial = new ConviteRestDTO(codConvite, estado);
        boolean alterou = conviteWebRepository.updateEstadoConvite(conviteParcial);
        return  alterou;
    }
}
