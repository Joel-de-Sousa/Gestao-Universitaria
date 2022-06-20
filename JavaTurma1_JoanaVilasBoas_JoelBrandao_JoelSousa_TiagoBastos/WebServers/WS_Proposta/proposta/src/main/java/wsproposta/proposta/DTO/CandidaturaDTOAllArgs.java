package wsproposta.proposta.DTO;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class CandidaturaDTOAllArgs {

    @Getter
    int codCandidatura;
    @Getter
    int codProposta;
    @Getter
    String titulo;
    @Getter
    String problema;
    @Getter
    String objectivo;
    /*@Getter
    String edicao;*/
    @Getter
    int codEstudante;
    @Getter
    String nomeEstudante;
    @Getter
    String sobrenomeEstudante;
    @Getter
    String estado;
}
