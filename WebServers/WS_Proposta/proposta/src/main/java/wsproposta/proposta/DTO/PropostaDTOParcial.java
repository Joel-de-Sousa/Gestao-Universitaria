package wsproposta.proposta.DTO;

import lombok.Getter;

/**
 * Classe PropostaDTOParcial foi criada para permitir a realizacao do update do estado da
 * proposta, tem apenas os parametros codigo da proposta e estado da proposta
 */

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
