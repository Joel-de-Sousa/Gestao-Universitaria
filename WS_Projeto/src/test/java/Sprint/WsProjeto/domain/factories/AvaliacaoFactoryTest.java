package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.junit.jupiter.api.Test;

import java.io.File;

class AvaliacaoFactoryTest {

    Juri juri = new Juri(1,2,3);
    Submissao submissao = new Submissao("teste",new File("teste1"),"Espanhol");
    @Test
    void FactoryShouldCreateAvaliacao(){
        AvaliacaoFactory avaliacaoFactory = new AvaliacaoFactory();
        avaliacaoFactory.createAvaliacao(1,2);
    }

}