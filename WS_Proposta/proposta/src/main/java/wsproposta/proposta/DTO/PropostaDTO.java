package wsproposta.proposta.DTO;


import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@ToString
public class PropostaDTO {

    /*@Getter
    int codProposta;*/
    @Getter
    int codUtilizador;
    @Getter
    int nifOrganizacao;
    @Getter
    int codEdicao;
    @Getter
    String titulo;
    @Getter
    String problema;
    @Getter
    String objetivo;
    @Getter
    String estado;
}
