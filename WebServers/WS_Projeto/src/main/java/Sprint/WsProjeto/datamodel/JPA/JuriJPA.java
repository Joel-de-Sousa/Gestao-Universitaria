package Sprint.WsProjeto.datamodel.JPA;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "juri")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JuriJPA {

    @Id
    @GeneratedValue(generator = "codJuri")
   private int codJuri;

   private int codPresidente;

   private int codOrientador;

   private int codArguente;

    public JuriJPA(int codPresidente, int codOrientador, int codArguente) {
        this.codPresidente = codPresidente;
        this.codOrientador = codOrientador;
        this.codArguente = codArguente;
    }
}
