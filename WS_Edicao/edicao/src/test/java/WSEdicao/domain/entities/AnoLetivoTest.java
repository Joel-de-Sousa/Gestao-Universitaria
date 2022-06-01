package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoTest {

    @Test
    void shouldCreateANewAnoLetivo() {
        new AnoLetivo("2015-2016");
    }

    @Test
    void shouldNotCreateANewAnoLetivoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new AnoLetivo("1999");
        });
        String expectedMessage = "Ano Inválido, preencha com o seguinte formato: AAAAinicial-AAAAseguinte";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAAnoLetivoWithCorrectAttributes() {
        AnoLetivo anoLetivo = new AnoLetivo("2015-2016");

        assertEquals(anoLetivo.getAno(), "2015-2016");
    }


    @Test
    void shouldReturnEqualSameAnoLetivo() {
        AnoLetivo anoLetivo1 = new AnoLetivo("2015-2016");
        assertEquals(anoLetivo1,anoLetivo1);
    }
    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        AnoLetivo anoLetivo1 = new AnoLetivo ("2015-2016");
        AnoLetivo anoLetivo2 = new AnoLetivo ("2016-2017");

        assertNotEquals(anoLetivo1,anoLetivo2);
    }

    @Test
    void shouldReturnTheCorrectFormatWithAnHyphen(){
        AnoLetivo anoLetivo1 = new AnoLetivo("20152016");

        assertEquals(anoLetivo1.getAno(),"2015-2016");
    }

}