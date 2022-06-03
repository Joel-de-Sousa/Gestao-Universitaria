package com.project.sprint.service;

import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.DTO.assembler.UtilizadorDomainDTOAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.domain.factories.IUtilizadorFactory;
import com.project.sprint.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilizadorService {
    @Autowired
    IUtilizadorFactory utilizadorFactory;
    @Autowired
    UtilizadorRepository utilizadorRepository;
    @Autowired
    UtilizadorDomainDTOAssembler utilizadorDomainDTOAssembler;

    public UtilizadorService() {
    }

    public UtilizadorDTO createAndSaveUtilizador(NewUtilizadorInfoDTO info) {
        Utilizador utilizador = utilizadorFactory.createUtilizador(info.getNome(), info.getSobrenome(), info.getEmail(), Utilizador.TipoUtilizador.valueOf(info.getTipoUtilizador()));
        Utilizador utilizadorSaved = utilizadorRepository.save(utilizador);
        UtilizadorDTO utilizadorDTO = utilizadorDomainDTOAssembler.toDTO(utilizadorSaved);
        return utilizadorDTO;
    }

    public UtilizadorDTO getUtilizadorByID(int id) {

        Optional<Utilizador> opUtilizador = utilizadorRepository.findById(id);

        if (opUtilizador.isPresent()) {
            Utilizador utilizador = opUtilizador.get();
            UtilizadorDTO utilizadorDTO = utilizadorDomainDTOAssembler.toDTO(utilizador);
            return utilizadorDTO;
        } else return null;
    }


}
