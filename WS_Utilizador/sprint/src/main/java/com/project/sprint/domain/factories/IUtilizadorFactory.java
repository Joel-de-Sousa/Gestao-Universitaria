package com.project.sprint.domain.factories;

import com.project.sprint.domain.entities.Utilizador;

public interface IUtilizadorFactory {

    public Utilizador createUtilizador(String sNome, String sSobrenome, String sEmail, Utilizador.TipoUtilizador oTipoUtilizador);
}
