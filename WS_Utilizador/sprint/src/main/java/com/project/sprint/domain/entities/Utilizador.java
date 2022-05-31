package com.project.sprint.domain.entities;

import com.project.sprint.utils.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@ToString
@EqualsAndHashCode
public class Utilizador {
    public enum TipoUtilizador {ESTUDANTE, DOCENTE}

    @Id
    @GeneratedValue
    @Getter
    @Setter
    int codUtilizador;
    @Getter
    @Setter
    String nome;
    @Getter
    @Setter
    String sobrenome;

    //teste UNITARIO
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    TipoUtilizador tipoUtilizador;

    public Utilizador(String sNome, String sSobrenome, String sEmail, TipoUtilizador oTipoUtilizador) {
        if (Util.validaStringComMinSemAlgSemSimbolo(3, 255, sNome)) {
            this.nome = sNome;
        } else {
            throw new IllegalArgumentException("O nome introduzido não pode conter algarismos ou carateres especiais e tem de de ser superior a 3 caracteres.");
        }

        if (Util.validaStringComMinSemAlgSemSimbolo(2, 255, sSobrenome)) {
            this.sobrenome = sSobrenome;
        } else {
            throw new IllegalArgumentException("O sobrenome introduzido não pode conter algarismos ou carateres especiais e tem de de ser superior a 2 caracteres.");
        }

        this.email = sEmail;

        this.tipoUtilizador = oTipoUtilizador;


    }


}
