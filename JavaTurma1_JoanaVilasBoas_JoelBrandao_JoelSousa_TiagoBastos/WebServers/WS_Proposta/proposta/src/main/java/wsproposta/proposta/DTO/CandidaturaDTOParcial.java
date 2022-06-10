package wsproposta.proposta.DTO;

import lombok.Getter;

public class CandidaturaDTOParcial {

    @Getter
    int codCandidatura;
    @Getter
    String estadoEstudante;


    public CandidaturaDTOParcial() {
    }

    public CandidaturaDTOParcial(int codCandidatura, String estadoEstudante) {
        this.codCandidatura = codCandidatura;
        this.estadoEstudante = estadoEstudante;
    }


    public void setCodCandidatura(int codCandidatura) {
        this.codCandidatura = codCandidatura;
    }

    public void setEstadoEstudante(String estadoEstudante) {
        this.estadoEstudante = estadoEstudante;
    }

}
