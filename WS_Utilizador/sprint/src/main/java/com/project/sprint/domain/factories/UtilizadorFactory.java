package com.project.sprint.domain.factories;

import com.project.sprint.domain.entities.Utilizador;
import org.springframework.stereotype.Service;

@Service
public class UtilizadorFactory implements IUtilizadorFactory{
    /**
     * Método de criação de um Utilizador
     *
     * @param sNome o nome do utilizador.
     * @param sSobrenome o sobrenome do utilizador.
     * @param sEmail o email do utilizador.
     * @param oTipoUtilizador o tipo de utilizador ( Estudante ou Docente)
     * @return um objeto do tipo Utilizador.
     */
    public Utilizador createUtilizador(String sNome, String sSobrenome, String sEmail, Utilizador.TipoUtilizador oTipoUtilizador){
        return new Utilizador(sNome,sSobrenome,sEmail,oTipoUtilizador);
    }

}
