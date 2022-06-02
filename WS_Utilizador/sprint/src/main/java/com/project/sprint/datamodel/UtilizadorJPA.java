package com.project.sprint.datamodel;

import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.utils.Util;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "utilizadores", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UtilizadorJPA {


    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    int codUtilizador;
    @Getter
    String nome;
    @Getter
    String sobrenome;

    //teste UNITARIO
    /*@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)*/
    @Getter
    String email;
    @Getter
    Utilizador.TipoUtilizador tipoUtilizador;

    protected UtilizadorJPA() {
    }

    public UtilizadorJPA(int codUtilizador, String sNome, String sSobrenome, String sEmail, Utilizador.TipoUtilizador oTipoUtilizador) {
        this.codUtilizador = codUtilizador;
        if (Util.validaStringComMinSemAlgSemSimbolo(3, 255, sNome)) {
            sNome.toLowerCase();
            this.nome = StringUtils.capitalize(sNome);
        } else {
            throw new IllegalArgumentException("O nome introduzido não pode conter algarismos ou carateres especiais e tem de de ser superior a 3 caracteres.");
        }

        if (Util.validaStringComMinSemAlgSemSimbolo(2, 255, sSobrenome)) {
            sNome.toLowerCase();
            this.sobrenome =StringUtils.capitalize(sSobrenome);
        } else {
            throw new IllegalArgumentException("O sobrenome introduzido não pode conter algarismos ou carateres especiais e tem de de ser superior a 2 caracteres.");
        }
        this.email = sEmail;

        this.tipoUtilizador = oTipoUtilizador;


    }


}
