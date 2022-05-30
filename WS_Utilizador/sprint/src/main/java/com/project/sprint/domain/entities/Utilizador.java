package com.project.sprint.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@ToString
@EqualsAndHashCode
public class Utilizador {
    public enum TipoUtilizador {ESTUDANTE, DOCENTE}

    ;
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
    @Email
    @Getter
    @Setter
    String email;
    @Getter
    @Setter
    TipoUtilizador tipoUtilizador;

    public Utilizador(int codUtilizador, String sNome, String sSobrenome, String sEmail, TipoUtilizador oTipoUtilizador) {
        this.codUtilizador = codUtilizador;
        if (sNome != null && !sNome.isBlank() && !(sNome.length() < 3) && sNome.matches("[a-zA-Z]")) {

            for (int i =0 ; i<4 ; i++) {



            }
            this.nome = sNome;
        } else
            throw new IllegalArgumentException("O nome introduzido tem de ser valido! sem algarismos ou carateres especiais.");

        this.sobrenome = sSobrenome;
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            // is valid, do something
            this.email = sEmail;
        } else {
            // is invalid, do something
        }

        this.tipoUtilizador = oTipoUtilizador;


        if (sDenominacao != null && !sDenominacao.isBlank() && !sDenominacao.isEmpty())
            this.denominacao = sDenominacao;


        this.tipoAlojamentoId = oTipoAlojamento;

        if (nQtdMinPax < 1 && nQtdMinPax > nQtdMaxPax)
            throw new IllegalArgumentException("Valor de parâmetro 'quantidade mínima pax' deve ser maior que 0 e menor ou igual a 'quantidade máxima pax'.");

        this.quantMinPax = nQtdMinPax;
        this.quantMaxPax = nQtdMaxPax;

        this.diaSemana = diaSemana;

        if (fPreco > 0)
            this.preco = fPreco;
        else
            throw new IllegalArgumentException("Valor de parâmetro 'preço' deve ser maior que 0.");

        this.localId = oLocal;
    }
}
}
