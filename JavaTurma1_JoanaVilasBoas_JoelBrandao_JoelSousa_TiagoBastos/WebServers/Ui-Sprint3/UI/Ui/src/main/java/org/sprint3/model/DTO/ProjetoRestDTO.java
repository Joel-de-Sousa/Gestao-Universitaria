package org.sprint3.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
public class ProjetoRestDTO {

    @Getter

    private int codProjeto;

    @Getter

    private int codProposta;

    @Getter

    private int codEstudante;

    @Getter

    private int codOrientador;

    @Getter
    private ArrayList<AvaliacaoDTO> avaliacaoList;

   /* @Getter
    int codProjeto;
    @Getter
    int codProposta;
    @Getter
    String tituloProjeto;
    @Getter
    String problema;
    @Getter
    String objetivo;
    @Getter
    String edicao;
    @Getter
    int codEstudante;
    @Getter
    String nomeEstudante;
    @Getter
    String sobrenomeEstudante;
    @Getter
    int codOrientador;
    @Getter
    String nomeOrientador;
    @Getter
    String sobrenomeOrientador;
    @Getter
    String momentoAvaliacao;*/

  /*  public ProjetoRestDTO(int codProposta, int codEstudante, int codOrientador) {
        this.codProposta = codProposta;
        this.codEstudante = codEstudante;
    }

    public ProjetoRestDTO(int codProjeto, String tituloProjeto, String problema, String objetivo, String nomeEstudante, String sobrenomeEstudante, String momentoAvaliacao) {
        this.codProjeto = codProjeto;
        this.tituloProjeto = tituloProjeto;
        this.problema = problema;
        this.objetivo = objetivo;
        this.nomeEstudante = nomeEstudante;
        this.sobrenomeEstudante = sobrenomeEstudante;
        this.momentoAvaliacao = momentoAvaliacao;

    }

    public ProjetoRestDTO(int codProjeto, String tituloProjeto, String problema, String objetivo, String edicao,String nomeEstudante, String sobrenomeEstudante, String momentoAvaliacao) {
        this.codProjeto = codProjeto;
        this.tituloProjeto = tituloProjeto;
        this.problema = problema;
        this.objetivo = objetivo;
        this.edicao = edicao;
        this.nomeEstudante = nomeEstudante;
        this.sobrenomeEstudante = sobrenomeEstudante;
        this.momentoAvaliacao = momentoAvaliacao;

    }

    public ProjetoRestDTO(int codProjeto, String tituloProjeto, String momentoAvaliacao) {
        this.codProjeto = codProjeto;
        this.tituloProjeto = tituloProjeto;
        this.momentoAvaliacao = momentoAvaliacao;
    }*/
}
