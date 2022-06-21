package org.sprint3.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class EdicaoRestDTO {

    @Getter
    int codEdicao;
    @Getter
    int codUc;
    @Getter
    String sigla;
    @Getter
    String denominacao;
    @Getter
    int codAnoLetivo;
    @Getter
    String ano;
    @Getter
    int codRUC;
    @Getter
    String estado;
    @Getter
    ArrayList<MomentoAvaliacaoDTO> momentoAvaliacaoList;
    @Getter
    ArrayList<AddStudentDTO> estudantesList;





    public EdicaoRestDTO(int codUc, int codAnoLetivo, int codRUC, String estado) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
        this.codRUC = codRUC;
        this.estado = estado;
    }

    public EdicaoRestDTO(int codUc, int codAnoLetivo) {
        this.codUc = codUc;
        this.codAnoLetivo = codAnoLetivo;
    }

    public EdicaoRestDTO(int codUc, String sigla, String denominacao, int codAnoLetivo, String ano, int codRUC, String estado) {
        this.codUc = codUc;
        this.sigla = sigla;
        this.denominacao = denominacao;
        this.codAnoLetivo = codAnoLetivo;
        this.ano = ano;
        this.codRUC = codRUC;
        this.estado = estado;
    }

    public EdicaoRestDTO(int codEdicao, String sigla, String ano) {
        this.codEdicao = codEdicao;
        this.sigla = sigla;
        this.ano = ano;
    }
}
