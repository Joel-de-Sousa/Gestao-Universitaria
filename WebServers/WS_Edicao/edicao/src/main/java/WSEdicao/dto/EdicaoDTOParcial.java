package WSEdicao.dto;

import WSEdicao.domain.entities.MomentoAvaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class EdicaoDTOParcial {

    int codEdicao;
    String estado;
     List<MomentoAvaliacao> momentoAvaliacaoList;


    public EdicaoDTOParcial(int codEdicao, String estado) {
        this.codEdicao = codEdicao;
        this.estado = estado;
    }

    public EdicaoDTOParcial(int codEdicao, List<MomentoAvaliacao> momentoAvaliacaoList) {
        this.codEdicao = codEdicao;
        this.momentoAvaliacaoList = momentoAvaliacaoList;
    }

    public EdicaoDTOParcial() {
    }
}
