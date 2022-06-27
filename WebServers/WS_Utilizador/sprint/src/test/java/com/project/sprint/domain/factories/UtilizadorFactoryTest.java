package com.project.sprint.domain.factories;

import com.project.sprint.domain.entities.Utilizador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorFactoryTest {


    @Test
    void factoryShouldCreateUtilizador(){

        UtilizadorFactory UtilizadorFactory = new UtilizadorFactory();
        UtilizadorFactory.createUtilizador("Joel","Brandão","JoelDocente@upskill.pt", Utilizador.TipoUtilizador.DOCENTE);
    }


}