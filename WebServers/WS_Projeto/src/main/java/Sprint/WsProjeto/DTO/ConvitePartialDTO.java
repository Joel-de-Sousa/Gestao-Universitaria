package Sprint.WsProjeto.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConvitePartialDTO {


    int codConvite;

    int codProjeto;

    int codDocente;

    String estado;

    public ConvitePartialDTO(int codConvite, String estado) {
        this.codConvite = codConvite;
        this.estado = estado;
    }



}
