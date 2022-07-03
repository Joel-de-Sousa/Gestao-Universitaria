package org.sprint3.model.repository;

import org.sprint3.model.DTO.ConviteRestDTO;
import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.REST.ConviteRestRepository;
import org.sprint3.model.repository.REST.EdicaoRestRepository;

import java.util.List;
import java.util.Optional;

public class ConviteWebRepository {

    ConviteRestRepository conviteRestRepository;

    public ConviteWebRepository() {
        conviteRestRepository=new ConviteRestRepository();
    }

    public boolean createConvite(ConviteRestDTO convite) throws Exception {
        boolean create= conviteRestRepository.createConvite(convite);
        return create;
    }
    public Optional<ConviteRestDTO> getConvitesByCodDocente (int codDocente){

        Optional<ConviteRestDTO> convite =conviteRestRepository.getListaConvitesByCodDocente (codDocente);
        if (convite.isPresent()){
            return convite;
        }else
            return Optional.empty();
    }

    public Optional<ConviteRestDTO> getConviteByCodConvite(int codConvite){
        Optional<ConviteRestDTO> opConvite = conviteRestRepository.findConviteByCodConvite(codConvite);

        return opConvite;
    }

    public boolean updateEstadoConvite(ConviteRestDTO conviteParcial) throws Exception {

        boolean alterou = conviteRestRepository.updateEstadoConvite(conviteParcial);
        return  alterou;
    }
}
