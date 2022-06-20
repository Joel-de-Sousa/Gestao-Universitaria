package org.sprint3.model.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SubmissaoRestDTO {


    @Getter
    int codSubmissao;
    @Getter
    String titulo;
    @Getter
    byte [] ficheiro;
    @Getter
    String linguagemFicheiro;

    public SubmissaoRestDTO(int codSubmissao, String titulo, String linguagemFicheiro) {
        this.codSubmissao = codSubmissao;
        this.titulo = titulo;
        this.linguagemFicheiro = linguagemFicheiro;
    }
}
