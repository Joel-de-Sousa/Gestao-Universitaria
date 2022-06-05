package wsproposta.proposta.datamodel.REST;

import lombok.Getter;

public class UtilizadorRestDTO {

    @Getter
    int codUtilizador;
    @Getter
    String nome;
    @Getter
    String sobrenome;
    @Getter
    String email;
    @Getter
    String tipoUtilizador;

    public UtilizadorRestDTO() {
    }

    public UtilizadorRestDTO(int codUtilizador, String nome, String sobrenome, String email, String tipoUtilizador) {
        this.codUtilizador = codUtilizador;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.tipoUtilizador = tipoUtilizador;
    }
}
