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
@Table(name = "convite")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConviteJPA {

    @Id
    @GeneratedValue(generator = "codConvite")
    int codConvite;

    int codProjeto;

    int codEstudante;

    int codDocente;

    Convite.Estado estado;

    public ConviteJPA(int codProjeto, int codEstudante, int codDocente, Convite.Estado estado) {
        this.codProjeto = codProjeto;
        this.codEstudante = codEstudante;
        this.codDocente = codDocente;
        this.estado = estado;
    }
}
