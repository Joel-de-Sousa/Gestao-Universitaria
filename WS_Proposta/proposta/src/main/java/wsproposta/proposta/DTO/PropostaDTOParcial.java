package wsproposta.proposta.DTO;

import lombok.Getter;

public class PropostaDTOParcial {

    @Getter
    int codProposta;
    @Getter
    String estado;

    public PropostaDTOParcial() {
    }

    public PropostaDTOParcial(int codProposta, String estado) {
        this.codProposta = codProposta;
        this.estado = estado;
    }

    public void setCodProposta(int codProposta) {
        this.codProposta = codProposta;
    }



    public void setEstado(String estado) {
        this.estado = estado;
    }
}
