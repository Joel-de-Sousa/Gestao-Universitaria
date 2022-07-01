package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class ProjetoRestDto {
    @Getter
    int codProjeto;
    @Getter
    int codProposta;
    @Getter
    int codEstudante;
    @Getter
    int codOrientador;


    public ProjetoRestDto(int codProposta, int codEstudante) {

        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }
}
