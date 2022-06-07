package com.project.sprint.datamodel;

import com.project.sprint.domain.entities.Utilizador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorJPATest {
    @Test
    void shouldCreateANewUtilizadorJpa() {
        new UtilizadorJPA(1,"Joel","Brandão","JoelDocente@Upskill.pt", Utilizador.TipoUtilizador.DOCENTE);
    }

    @Test
    void shouldCreateAUtilizadorJpaWithCorrectAttributes(){
        UtilizadorJPA utilizadorJPA = new UtilizadorJPA(1,"Joel","Brandão","JoelDocente@Upskill.pt", Utilizador.TipoUtilizador.DOCENTE);

        assertEquals(utilizadorJPA.getCodUtilizador(),1);
        assertEquals(utilizadorJPA.getNome(),"Joel");
        assertEquals(utilizadorJPA.getSobrenome(),"Brandão");
        assertEquals(utilizadorJPA.getEmail(),"JoelDocente@Upskill.pt");
        assertEquals(utilizadorJPA.getTipoUtilizador(), Utilizador.TipoUtilizador.DOCENTE);


    }

    @Test
    void shouldReturnEqualsSameUtilizadorJpa(){
        UtilizadorJPA utilizadorJPA = new UtilizadorJPA(1,"Joel","Brandão","JoelDocente@Upskill.pt", Utilizador.TipoUtilizador.DOCENTE);

        assertEquals(utilizadorJPA,utilizadorJPA);
    }

    @Test
    void shouldReturnNotEqualsDifferentLocaisJpa(){
        UtilizadorJPA utilizadorJPA = new UtilizadorJPA(1,"Joel","Brandão","JoelDocente@Upskill.pt", Utilizador.TipoUtilizador.DOCENTE);;
        UtilizadorJPA utilizadorJPA1 = new UtilizadorJPA(2,"Noel","dão","NoelAluno@Upskill.pt", Utilizador.TipoUtilizador.ESTUDANTE);

        assertNotEquals(utilizadorJPA,utilizadorJPA1);
    }}