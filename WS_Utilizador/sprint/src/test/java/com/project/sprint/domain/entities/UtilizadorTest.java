package com.project.sprint.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorTest {

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