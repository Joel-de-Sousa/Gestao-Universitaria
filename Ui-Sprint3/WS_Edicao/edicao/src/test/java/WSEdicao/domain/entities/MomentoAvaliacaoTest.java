package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentoAvaliacaoTest {

    @Test
    void shouldCreateANewMomentoAvaliacao() {
        new MomentoAvaliacao(1, "Sprint1");
    }

    @Test
    void shouldNotCreateANewMomentoAvaliacaoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new MomentoAvaliacao(1,"te");

        });
        String expectedMessage = "A denominção deve contem no mínimo 3 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAMomentoAvaliacaoWithCorrectAttributes() {
        MomentoAvaliacao momentoAvaliacao = new MomentoAvaliacao(1, "Sprint1");

        assertEquals(momentoAvaliacao.getDenominacao(), "Sprint1");
    }


    @Test
    void shouldReturnEqualSameMomentoAvaliacao() {
        MomentoAvaliacao momentoAvaliacao1 = new MomentoAvaliacao(1, "Sprint1");
        assertEquals(momentoAvaliacao1,momentoAvaliacao1);
    }
    @Test
    void shouldReturnNotEqualsDifferentMomentoAvaliacao(){
        MomentoAvaliacao momentoAvaliacao1 = new MomentoAvaliacao (1,"Sprint1");
        MomentoAvaliacao momentoAvaliacao2 = new MomentoAvaliacao (1,"Sprint2");

        assertNotEquals(momentoAvaliacao1,momentoAvaliacao2);
    }

}