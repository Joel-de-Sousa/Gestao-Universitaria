package Sprint.WsProjeto.domain.factories;

import Sprint.WsProjeto.domain.entities.Submissao;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SubmissaoFactoryTest {

    @Test
    void FactoryShouldCreateSubmissao(){

        SubmissaoFactory submissaoFactory = new SubmissaoFactory();
        submissaoFactory.createSubmissao("teste",new File("teste"),"Espanhol");


    }

}