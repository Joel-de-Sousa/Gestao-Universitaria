package com.project.sprint.domain.entities;

import com.project.sprint.utils.Util;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Utilizador {

    public enum TipoUtilizador {ESTUDANTE, DOCENTE}

    /**
     * o código do utilizador
     */
    @Getter
    @Setter
    int codUtilizador;
    /**
     * o nome do utilizador
     */
    @Getter
    @Setter
    String nome;
    /**
     * o sobrenome do utilizador
     */
    @Getter
    @Setter
    String sobrenome;

    //teste UNITARIO
   /* @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)*/

    /**
     * o email do utilizador
     */
    @Getter
    @Setter
    String email;

    /**
     * o tipo de utilizador (Estudante , Docente)
     */
    @Getter
    @Setter
    TipoUtilizador tipoUtilizador;

    /**
     * Construtor do Objeto Utilizador e suas respetivas exceções.
     * @param sNome o nome
     * @param sSobrenome o sobrenome
     * @param sEmail o email
     * @param oTipoUtilizador o tipo de utilizador ( Estudante , Docente)
     */

    public Utilizador(String sNome, String sSobrenome, String sEmail, TipoUtilizador oTipoUtilizador) {
        if (Util.validaStringComMinSemAlgSemSimbolo(3, 100, sNome)) {
            sNome.toLowerCase();
            this.nome = StringUtils.capitalize(sNome);
        } else {
            throw new IllegalArgumentException("O nome introduzido não pode conter espaços ,algarismos ou carateres especiais e tem de de ser superior a 3 caracteres.");
        }

        if (Util.validaStringComMinSemAlgSemSimbolo(2, 150, sSobrenome)) {
            sNome.toLowerCase();
            this.sobrenome =StringUtils.capitalize(sSobrenome);
        } else {
            throw new IllegalArgumentException("O sobrenome introduzido não pode conter espaços, algarismos ou carateres especiais e tem de de ser superior a 2 caracteres.");
        }
        if (EmailValidator.getInstance().isValid(sEmail)){
        this.email = sEmail;}
        else {throw new IllegalArgumentException("O email é invalido");}


        this.tipoUtilizador = oTipoUtilizador;


    }


}
