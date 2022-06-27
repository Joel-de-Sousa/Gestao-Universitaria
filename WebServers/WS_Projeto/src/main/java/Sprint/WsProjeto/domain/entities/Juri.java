package Sprint.WsProjeto.domain.entities;


import lombok.*;

import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Getter
@Setter
public class Juri {

    int codJuri;

    int codPresidente;

    int codOrientador;

    int codArguente;

    public Juri(int codPresidente, int codOrientador, int codArguente) {
        this.codPresidente = codPresidente;
        this.codOrientador = codOrientador;
        this.codArguente = codArguente;
    }
}
