package Sprint.WsProjeto.domain.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConviteFactoryTest {

    @Test
    void FactoryShouldCreateConvite(){
        ConviteFactory conviteFactory = new ConviteFactory();
        conviteFactory.createConvite(1,2,3);

    }

}