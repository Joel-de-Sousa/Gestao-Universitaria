package WSEdicao.datamodel;

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
    private int codEdicao;
    private String denominacao;

    protected MomentoAvaliacaoJpa(){}

    public MomentoAvaliacaoJpa(int codEdicao,String denominacao) {
        this.codEdicao = codEdicao;
        this.denominacao = denominacao;
    }
}
