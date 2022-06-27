package WSEdicao.datamodel.REST;

import lombok.Getter;

@Getter
public class UtilizadorRestDTO {

    int codUtilizador;
    String nome;
    String sobrenome;
    String email;
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
