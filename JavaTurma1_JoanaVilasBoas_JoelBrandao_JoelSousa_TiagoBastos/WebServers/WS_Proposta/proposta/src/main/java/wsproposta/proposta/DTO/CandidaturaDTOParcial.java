package wsproposta.proposta.DTO;

import lombok.Getter;

public class CandidaturaDTOParcial {

    @Getter
    int codCandidatura;
    @Getter
    int codOrientador;
    @Getter
    String estadoEstudante;
    @Getter
    String estadoOrientador;

    public CandidaturaDTOParcial() {
    }

    public CandidaturaDTOParcial(int codCandidatura, String estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.estadoEstudante = estadoEstudante;
    }

    public CandidaturaDTOParcial(int codCandidatura, int codOrientador, String estadoOrientador) {
        this.codCandidatura = codCandidatura;
        this.codOrientador = codOrientador;
        this.estadoOrientador = estadoOrientador;
    }

    public void setCodCandidatura(int codCandidatura) {
        this.codCandidatura = codCandidatura;
    }

    public void setEstadoEstudante(String estadoEstudante) {
        this.estadoEstudante = estadoEstudante;
    }

    public void setCodOrientador(int codOrientador) {
        this.codOrientador = codOrientador;
    }

    public void setEstadoOrientador(String estadoOrientador) {
        this.estadoOrientador = estadoOrientador;
    }
}
