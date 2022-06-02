package wsproposta.proposta.domain.entities;


import lombok.*;
import wsproposta.proposta.utils.Util;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@EqualsAndHashCode

public class Proposta {

    public enum Estado {PENDENTE, ACEITE, REJEITADA};

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Getter @Setter
    int codProposta;

    @Getter @Setter
    int codUtilizador;

    @Getter @Setter
    long nifOrganizacao;

    @Getter @Setter
    int codEdicao;

    @Getter @Setter
    String titulo;

    @Getter @Setter
    String problema;

    @Getter @Setter
    String objetivo;

    @Getter @Setter
    Estado estado;

    private static final Estado ESTADO_POR_OMISSAO = Estado.PENDENTE;


    public Proposta(int codProposta, int codUtilizador, long nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo, Estado estado) {
        this.codProposta = codProposta;
        this.codUtilizador = codUtilizador;
        this.nifOrganizacao = nifOrganizacao;
        this.codEdicao = codEdicao;
        this.titulo = titulo;
        this.problema = problema;
        this.objetivo = objetivo;
        this.estado = estado;
    }


    public Proposta(int codProposta, Estado estado) {
        this.codProposta = codProposta;
        this.estado = estado;
    }

    public Proposta(int codUtilizador, long nifOrganizacao, int codEdicao, String sTitulo, String sProblema, String sObjetivo/*, Estado estado*/) {

        this.codUtilizador = codUtilizador;

        this.nifOrganizacao = nifOrganizacao;

        this.codEdicao = codEdicao;

        if(Util.validaStringMinCarateresNaoBrancos(10, sTitulo))
            this.titulo = sTitulo;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Título' deve ter no mínimo 10 caracteres não brancos");

        if(Util.validaStringMinCarateresNaoBrancos(10, sProblema))
            this.problema = sProblema;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Problema' deve ter no mínimo 10 caracteres não brancos");

        if(Util.validaStringMinCarateresNaoBrancos(10, sObjetivo))
            this.objetivo = sObjetivo;
        else
            throw new IllegalArgumentException ("O valor do parâmetro 'Objectivo' deve ter no mínimo 10 caracteres não brancos");

        this.estado = ESTADO_POR_OMISSAO;

    }

    public void setCodProposta(int codProposta) {
        this.codProposta = codProposta;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setCodUtilizador(int codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public void setNifOrganizacao(long nifOrganizacao) {
        this.nifOrganizacao = nifOrganizacao;
    }

    public void setCodEdicao(int codEdicao) {
        this.codEdicao = codEdicao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
}
