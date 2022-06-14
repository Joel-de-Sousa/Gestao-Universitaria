package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avaliacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoJPA {

    @Id
    @GeneratedValue
    int codAvaliacao;

    int codMA;

    int codJuri;

    int codSubmissao;

}
