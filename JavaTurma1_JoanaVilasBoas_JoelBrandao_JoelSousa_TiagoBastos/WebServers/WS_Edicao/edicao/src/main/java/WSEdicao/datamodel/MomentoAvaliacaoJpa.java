package WSEdicao.datamodel;

import WSEdicao.utils.Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Table(name = "momentosavaliacao")
@Entity
public class MomentoAvaliacaoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "codMomentoAvaliacao_seq")
    private int codMomentoAvaliacao;
    private String denominacao;

    protected MomentoAvaliacaoJpa(){}

    public MomentoAvaliacaoJpa(String denominacao) {
        if (Util.validaStringMinCarateresNaoBrancos(3, denominacao)) {
            this.denominacao = denominacao;
        } else
            throw new IllegalArgumentException("A denominção deve contem no mínimo 3 caracteres não brancos");
    }
}
