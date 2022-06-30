package wsproposta.proposta.datamodel.REST;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EdicaoAllArgsDTO {

    int codEdicao;
    int codUc;
    String sigla;
    String denominacao;
    int codAnoLetivo;
    String ano;
    int codRUC;
    String estado;
}
