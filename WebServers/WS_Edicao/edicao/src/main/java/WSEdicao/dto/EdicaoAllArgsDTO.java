package WSEdicao.dto;

import WSEdicao.datamodel.EstudanteJpa;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class EdicaoAllArgsDTO {

    int codEdicao;
    int codUc;
    String sigla;
    String denominacao;
    int codAnoLetivo;
    String ano;
    int codRUC;
    String estado;
    ArrayList<MomentoAvaliacaoDTO> momentoAvaliacaoList;
    ArrayList<AddStudentDTO> estudantesList;

}
