package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

class JuriFactoryTest {

    @Test
    void FactoryShouldCreateJuri(){

        JuriFactory juriFactory = new JuriFactory();
        juriFactory.createJuri(1,2,3);

    }

}