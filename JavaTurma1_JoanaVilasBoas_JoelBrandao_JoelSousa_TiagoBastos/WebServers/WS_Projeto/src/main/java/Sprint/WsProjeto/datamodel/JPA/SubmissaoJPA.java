package Sprint.WsProjeto.datamodel.JPA;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "submissoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubmissaoJPA {

    @Id
    @GeneratedValue(generator = "codSubmissao")
   private int codSubmissao;

   private String titulo;

   private String urlFicheiro;

   private String linguagemFicheiro;

    public SubmissaoJPA(String titulo, String urlFicheiro, String linguagemFicheiro) {
        this.titulo = titulo;
        this.urlFicheiro = urlFicheiro;
        this.linguagemFicheiro = linguagemFicheiro;
    }
}
