package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

import java.io.File;

class SubmissaoFactoryTest {

    @Test
    void FactoryShouldCreateSubmissao(){

        SubmissaoFactory submissaoFactory = new SubmissaoFactory();
        submissaoFactory.createSubmissao("teste",new File("teste"),"Espanhol");


    }

}