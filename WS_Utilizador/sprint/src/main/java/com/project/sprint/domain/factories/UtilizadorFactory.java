package com.project.sprint.domain.factories;

import com.project.sprint.domain.entities.Utilizador;
import org.springframework.stereotype.Service;

@Service
public class UtilizadorFactory implements IUtilizadorFactory{
    public Utilizador createUtilizador(String sNome, String sSobrenome, String sEmail, Utilizador.TipoUtilizador oTipoUtilizador){
        return new Utilizador(sNome,sSobrenome,sEmail,oTipoUtilizador);
    }

}
