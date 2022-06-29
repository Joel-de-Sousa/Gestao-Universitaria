package Sprint.WsProjeto.datamodel.REST;


import Sprint.WsProjeto.DTO.AddStudentDTO;
import Sprint.WsProjeto.DTO.MomentoAvaliacaoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;



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

    public EdicaoRestDTO(int codEdicao, int codUc, String sigla, String denominacao, int codAnoLetivo, String ano, int codRUC, String estado, ArrayList<MomentoAvaliacaoDTO> momentoAvaliacaoList, ArrayList<AddStudentDTO> estudantesList) {
        this.codEdicao = codEdicao;
        this.codUc = codUc;
        this.sigla = sigla;
        this.denominacao = denominacao;
        this.codAnoLetivo = codAnoLetivo;
        this.ano = ano;
        this.codRUC = codRUC;
        this.estado = estado;
        this.momentoAvaliacaoList = momentoAvaliacaoList;
        this.estudantesList = estudantesList;
    }

    public EdicaoRestDTO() {
    }
}
