package Sprint.WsProjeto.datamodel.REST;

import lombok.Getter;
import lombok.Setter;

public class UtilizadorRestDTO {

    @Getter
    @Setter
    int codUtilizador;
    @Getter
    @Setter
    String nome;
    @Getter
    @Setter
    String sobrenome;

    public UtilizadorRestDTO(int codUtilizador, String nome, String sobrenome) {
        this.codUtilizador = codUtilizador;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }
}
