package com.project.sprint.DTO.assembler;

import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.domain.entities.Utilizador;
import org.springframework.stereotype.Service;

@Service
public class UtilizadorDomainDTOAssembler {
    public UtilizadorDTO toDTO(Utilizador utilizador){
        UtilizadorDTO utilizadorDTO= new UtilizadorDTO(utilizador.getCodUtilizador(), utilizador.getNome(), utilizador.getSobrenome(), utilizador.getEmail(), utilizador.getTipoUtilizador().name());
    return utilizadorDTO;
    }
}
