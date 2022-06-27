package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class ProjetoRestDto {
    @Getter
   int codProjeto;
    @Getter
   int codEstudante;
    @Getter
   int codOrientador;
    @Getter
   int codProposta;

    public ProjetoRestDto(int codEstudante, int codProposta) {
        this.codEstudante = codEstudante;

        this.codProposta = codProposta;
    }
}
