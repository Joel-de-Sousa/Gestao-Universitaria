package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuriFactoryTest {

    @Test
    void FactoryShouldCreateJuri(){

        JuriFactory juriFactory = new JuriFactory();
        juriFactory.createJuri(1,2,3);

    }

}