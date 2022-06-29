package Sprint.WsProjeto.domain.entities;


import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Convite {

    public enum Estado {PENDENTE, ACEITE, REJEITADO};
    public static final Estado ESTADO_POR_OMISSAO = Estado.PENDENTE;
    int codConvite;
    int codProjeto;

    int codEstudante;

    int codDocente;

    Estado estado;





    public Convite(int codProjeto, int codEstudante, int codDocente) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.estado = ESTADO_POR_OMISSAO;
    }
}
