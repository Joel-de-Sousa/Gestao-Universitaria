package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

class ProjetoFactoryTest {

    @Test
    void FactoryShouldCreateProjeto(){
       ProjetoFactory projetoFactory = new ProjetoFactory();
       projetoFactory.createProjeto(1,2);
    }


}