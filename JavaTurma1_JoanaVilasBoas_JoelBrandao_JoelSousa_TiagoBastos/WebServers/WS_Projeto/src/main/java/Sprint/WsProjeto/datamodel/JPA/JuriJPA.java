package Sprint.WsProjeto.datamodel.JPA;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "juris")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JuriJPA {

    @Id
    @GeneratedValue
   private int codJuri;

   private int codPresidente;

   private int codOrientador;

   private int codArguente;
}
