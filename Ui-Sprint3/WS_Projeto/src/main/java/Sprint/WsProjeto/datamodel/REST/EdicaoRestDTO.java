package Sprint.WsProjeto.datamodel.REST;


import Sprint.WsProjeto.DTO.AddStudentDTO;
import Sprint.WsProjeto.DTO.MomentoAvaliacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@AllArgsConstructor
@Getter
@Setter
public class EdicaoRestDTO {

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
