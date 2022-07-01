package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoTest {

    @Test
    void shouldCreateANewAnoLetivo() {
        new AnoLetivo("2015");
    }

    @Test
    void shouldNotCreateANewAnoLetivoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new AnoLetivo("POG");

        });
        String expectedMessage = "Ano Inválido, preencha o Ano com o seguinte formato: AAAA";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAAnoLetivoWithCorrectAttributes() {
        AnoLetivo anoLetivo = new AnoLetivo("2015");

        assertEquals(anoLetivo.getAno(), "2015-2016");
    }


    @Test
    void shouldReturnEqualSameAnoLetivo() {
        AnoLetivo anoLetivo1 = new AnoLetivo("2015");
        assertEquals(anoLetivo1,anoLetivo1);
    }
    @Test
    void shouldReturnNotEqualsDifferentAnoLetivo(){
        AnoLetivo anoLetivo1 = new AnoLetivo ("2015");
        AnoLetivo anoLetivo2 = new AnoLetivo ("2016");

        assertNotEquals(anoLetivo1,anoLetivo2);
    }

}