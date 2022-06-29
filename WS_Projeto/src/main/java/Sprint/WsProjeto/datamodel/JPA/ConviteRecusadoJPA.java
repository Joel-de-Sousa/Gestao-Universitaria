package Sprint.WsProjeto.datamodel.JPA;


import Sprint.WsProjeto.domain.entities.Convite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "conviteRecusado")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConviteRecusadoJPA {

    @Id
    @GeneratedValue(generator = "codConvite")
    int codConvite;

    int codProjeto;

    int codEstudante;

    int codDocente;

    Convite.Estado estado;

    public ConviteRecusadoJPA(int codProjeto, int codEstudante, int codDocente, Convite.Estado estado) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.estado = estado;
    }
}
