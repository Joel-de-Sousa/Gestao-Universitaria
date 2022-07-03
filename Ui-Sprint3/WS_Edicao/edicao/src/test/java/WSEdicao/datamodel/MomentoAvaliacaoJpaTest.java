package WSEdicao.datamodel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentoAvaliacaoJpaTest {

    @Test
    void shouldCreateANewMomentoAvaliacao() {
        new MomentoAvaliacaoJpa(1,"Sprint1");
    }

    @Test
    void shouldNotCreateANewMomentoAvaliacaoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new MomentoAvaliacaoJpa(1,"9*");
        });
        String expectedMessage = "A denominção deve contem no mínimo 3 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAMomentoAvaliacaoJpaWithCorrectAttributes() {
        MomentoAvaliacaoJpa momentoAvaliacao1 = new MomentoAvaliacaoJpa(1,"Sprint1");

        assertEquals(momentoAvaliacao1.getCodEdicao(), 1);
        assertEquals(momentoAvaliacao1.getDenominacao(),"Sprint1");
    }


    @Test
    void shouldReturnTheSameMomentoAvaliacaoJpa() {
        MomentoAvaliacaoJpa momentoAvaliacao1 = new MomentoAvaliacaoJpa(1,"Sprint1");
        assertEquals(momentoAvaliacao1,momentoAvaliacao1);
    }
    @Test
    void shouldReturnNotEqualsDifferentMomentoAvaliacaoJpa(){
        MomentoAvaliacaoJpa momentoAvaliacao1 = new MomentoAvaliacaoJpa (1,"Sprint1");
        MomentoAvaliacaoJpa momentoAvaliacao2 = new MomentoAvaliacaoJpa (1,"Sprint2");

        assertNotEquals(momentoAvaliacao1,momentoAvaliacao2);
    }
}