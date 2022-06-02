package com.project.sprint.datamodel.assembler;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.domain.entities.Utilizador;
import org.springframework.stereotype.Service;

@Service
public class UtilizadorDomainDataAssembler {

    public UtilizadorJPA toData(Utilizador utilizador){
        UtilizadorJPA utilizadorJPA= new UtilizadorJPA(utilizador.getCodUtilizador(), utilizador.getNome(), utilizador.getSobrenome(), utilizador.getEmail(), utilizador.getTipoUtilizador());
        return utilizadorJPA;
    }

    public Utilizador toDomain(UtilizadorJPA utilizadorJPA){
        Utilizador utilizador= new Utilizador(utilizadorJPA.getCodUtilizador(),utilizadorJPA.getNome(), utilizadorJPA.getSobrenome(), utilizadorJPA.getEmail(), utilizadorJPA.getTipoUtilizador());
        return utilizador;
    }

}
