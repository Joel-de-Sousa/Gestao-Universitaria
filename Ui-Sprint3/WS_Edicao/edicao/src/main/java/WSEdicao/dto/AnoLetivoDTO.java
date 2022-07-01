package WSEdicao.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
public class AnoLetivoDTO {

    int codAnoLetivo;
    String ano;

    public AnoLetivoDTO() {
    }
}
