package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoFactoryTest {

    @Test
    void FactoryShouldCreateProjeto(){
       ProjetoFactory projetoFactory = new ProjetoFactory();
       projetoFactory.createProjeto(1,2,3);
    }

    @Test

    void factoryShouldNotCreateProjetoEmptyCode(){

    }

}