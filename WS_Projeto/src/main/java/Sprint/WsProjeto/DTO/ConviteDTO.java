package Sprint.WsProjeto.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConviteDTO {


    int codConvite;

    int codProjeto;

    int codEstudante;

    int codDocente;

    String estado;

    public ConviteDTO(int codConvite, String estado) {
        this.codConvite = codConvite;
        this.estado = estado;
    }
}
