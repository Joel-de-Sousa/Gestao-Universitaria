package WSEdicao.domain.factories;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoFactoryTest {

    @Test
    void factoryShouldCreateAnoLetivo() {
        AnoLetivoFactory anoLetivoFactory = new AnoLetivoFactory();
        anoLetivoFactory.createAnoLetivo("2015-2016");
    }
    @Test
    void factoryShouldNotCreateAnoLetivoEmptyYear() {
        AnoLetivoFactory anoLetivoFactory = new AnoLetivoFactory();
        assertThrows(Exception.class, () -> {
            anoLetivoFactory.createAnoLetivo("");
        });
    }
    @Test
    void factoryShouldNotCreateAnoLetivoWithWrongFormat() {
        AnoLetivoFactory anoLetivoFactory = new AnoLetivoFactory();
        assertThrows(Exception.class, () -> {
            anoLetivoFactory.createAnoLetivo("1900-9013123");
        });
    }
    @Test
    void factoryShouldNotCreateAnoLetivoNullName() {
        AnoLetivoFactory anoLetivoFactory = new AnoLetivoFactory();
        assertThrows(Exception.class, () -> {
            anoLetivoFactory.createAnoLetivo(null);
        });
    }

}