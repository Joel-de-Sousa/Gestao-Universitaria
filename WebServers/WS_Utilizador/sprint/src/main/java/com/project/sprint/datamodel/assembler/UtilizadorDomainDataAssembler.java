package com.project.sprint.datamodel.assembler;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.domain.entities.Utilizador;
import org.springframework.stereotype.Service;

@Service

public class UtilizadorDomainDataAssembler {
    /**
     * Método que recebe como parametro um objeto Utilizador que cria e retorna um objeto UtilizadorJPA.
     * @param utilizador objeto do tipo Utilizador
     * @return um objeto do tipo UtilizadorJPA
     */
    public UtilizadorJPA toData(Utilizador utilizador){
        UtilizadorJPA utilizadorJPA= new UtilizadorJPA(utilizador.getCodUtilizador(), utilizador.getNome(), utilizador.getSobrenome(), utilizador.getEmail(), utilizador.getTipoUtilizador());
        return utilizadorJPA;
    }

    /**
     * Método que recebe como parametro um objeto UtilizadorJPA que cria e retorna um objeto Utilizador.
     * @param utilizadorJPA objeto do Tipo utilizadorJPA
     * @return um objeto do tipo Utilizador
     */
    public Utilizador toDomain(UtilizadorJPA utilizadorJPA){
        Utilizador utilizador= new Utilizador(utilizadorJPA.getCodUtilizador(),utilizadorJPA.getNome(), utilizadorJPA.getSobrenome(), utilizadorJPA.getEmail(), utilizadorJPA.getTipoUtilizador());
        return utilizador;
    }

}
