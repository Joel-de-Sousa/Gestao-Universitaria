package com.project.sprint.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {


    @Test
    void shouldCreateNewUtilizador(){

        new Utilizador("Joel","Randão","JoelDocente@Upskill.pt", Utilizador.TipoUtilizador.DOCENTE);
    }
    @Test
    void shouldCreateNewUtilizadorWithCorrectAttributes(){
        Utilizador utilizador = new Utilizador("Joel","Brandão","JoelDocente@Upskill.pt", com.project.sprint.domain.entities.Utilizador.TipoUtilizador.DOCENTE);

        assertEquals(utilizador.getNome(),"Joel");
        assertEquals(utilizador.getSobrenome(),"Brandão");
        assertEquals(utilizador.getEmail(),"JoelDocente@Upskill.pt");
        assertEquals(utilizador.getTipoUtilizador(), Utilizador.TipoUtilizador.DOCENTE);
    }

    @Test
    void shouldNotCreateANewUtilizadorWithNumbersOnName() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Utilizador("7","Brandão","JoelDocente@upskill.pt", Utilizador.TipoUtilizador.DOCENTE);
        });
        String expectedMessage = "O nome introduzido não pode conter espaços ,algarismos ou carateres especiais e tem de de ser superior a 3 caracteres.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void shouldNotCreateANewUtilizadorWithNUmbersOnLastName(){

        Exception exception = assertThrows(Exception.class, () -> {
            new Utilizador("Joel","7","JoelDocente@upskill.pt", Utilizador.TipoUtilizador.DOCENTE);
        });
        String expectedMessage = "O sobrenome introduzido não pode conter espaços, algarismos ou carateres especiais e tem de de ser superior a 2 caracteres.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    void shouldNotCreateANewUtilizadorWithInvalidEmail(){
        Exception exception = assertThrows(Exception.class, () -> {
            new Utilizador("Joel","Brandão","sd", Utilizador.TipoUtilizador.DOCENTE);
        });
        String expectedMessage = "O email é invalido" ;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));


    }

    @Test
    void shouldReturnEqualSameUtilizador(){
        Utilizador utilizador = new Utilizador("Joel","Brandão","JoelDocente@Upskill.pt", com.project.sprint.domain.entities.Utilizador.TipoUtilizador.DOCENTE);

        assertEquals(utilizador,utilizador);
    }

    @Test
    void shouldReturnNotEqualsDifferentUtilizadores() {

        Utilizador utilizador = new Utilizador("Joel","Brandão","JoelDocente@Upskill.pt", com.project.sprint.domain.entities.Utilizador.TipoUtilizador.DOCENTE);
        Utilizador utilizador2 = new Utilizador("Joel","Randão","JoelDocente@Upskill.pt", com.project.sprint.domain.entities.Utilizador.TipoUtilizador.DOCENTE);

        assertNotEquals(utilizador,utilizador2);

    }


    @Test
    void validaNomeComTil() {
        String nome = "João";
        boolean test = false;
        if (nome != null  && (nome.length() > 3 && nome.length()<100)) { //testar ç
            char c;
            for (int i = 0; i < nome.length(); i++) {
                c = nome.charAt(i);
                if (Character.isAlphabetic(c)) {
                    test = true;
                    System.out.println(c);//apaga<---  e coloca o this.nome = sNome;
                } else {
                    test = false;         // apagar <---- e colocar --->throw new IllegalArgumentException("O nome introduzido não pode conter algarismos ou carateres especiais.");
                    System.out.println("não é letra");
                    break;
                }
            }
        } else {

            System.out.println("entrou else");     //apagar
            test = false;    // apagar <---- e colocar --->throw new IllegalArgumentException("O nome introduzido tem de ter pelo menos 3 caracteres");

        }

        assertTrue(test);
    }
    @Test
    void validaNomecc(){
        String nome = "Antonio migu         ac";
        boolean test = false;
        System.out.println(nome.replaceAll(" ","").length());
        System.out.println(nome);
        if (nome != null  && (nome.replaceAll(" ","").length()>9)){
             test=true;
            System.out.println(nome);
        }else{
            test=false;
        }
        assertTrue(test);
    }

}