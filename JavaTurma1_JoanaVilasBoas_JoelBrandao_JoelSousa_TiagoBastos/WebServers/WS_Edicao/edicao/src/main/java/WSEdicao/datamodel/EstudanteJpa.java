package WSEdicao.datamodel;

import lombok.*;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
public class EstudanteJpa {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int codEstudante;
    private int codUtilizador;
    private int codEdicao;

    public EstudanteJpa(int codUtilizador) {
        this.codUtilizador = codUtilizador;
    }

    public EstudanteJpa(int codUtilizador, int codEdicao) {
        this.codUtilizador = codUtilizador;
        this.codEdicao = codEdicao;
    }
}
