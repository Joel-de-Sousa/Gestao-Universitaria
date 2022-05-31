package wsproposta.proposta.domain.entities;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@EqualsAndHashCode
public class Proposta {

    @Id
    @GeneratedValue
    @Getter @Setter
    int codProposta;

    @Getter @Setter
    int codUtilizador;

    @Getter @Setter
    int nifOrganizacao;

    @Getter @Setter
    int codEdicao;

    @Getter @Setter
    String titulo;

    @Getter @Setter
    String problema;

    @Getter @Setter
    String objetivo;


    public Proposta(int codUtilizador, int nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo) {

        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = sTitulo;
        this.problema = sProblema;
        this.objetivo = sObjetivo;

    }
}
