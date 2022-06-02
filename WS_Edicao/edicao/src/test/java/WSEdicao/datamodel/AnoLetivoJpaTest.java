package WSEdicao.datamodel;

import WSEdicao.domain.entities.AnoLetivo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AnoLetivoJpaTest {

    @Test
    void shouldCreateANewAnoLetivo() {
        new AnoLetivoJpa("2015-2016");
    }

    @Test
    void shouldNotCreateANewAnoLetivoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new AnoLetivoJpa("1999");
        });
        String expectedMessage = "Ano Inválido, preencha com o seguinte formato: AAAAinicial-AAAAseguinte";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAAnoLetivoJpaWithCorrectAttributes() {
        AnoLetivoJpa anoLetivo1 = new AnoLetivoJpa("2015-2016");

        assertEquals(anoLetivo1.getAno(), "2015-2016");
    }


    @Test
    void shouldReturnEqualSameAnoLetivoJpa() {
        AnoLetivoJpa anoLetivo1 = new AnoLetivoJpa("2015-2016");
        assertEquals(anoLetivo1,anoLetivo1);
    }
    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        AnoLetivoJpa anoLetivo1 = new AnoLetivoJpa ("2015-2016");
        AnoLetivoJpa anoLetivo2 = new AnoLetivoJpa ("2016-2017");

        assertNotEquals(anoLetivo1,anoLetivo2);
    }

    @Test
    void shouldReturnTheCorrectFormatWithAnHyphen(){
        AnoLetivoJpa anoLetivo1 = new AnoLetivoJpa("20152016");

        assertEquals(anoLetivo1.getAno(),"2015-2016");
    }

}