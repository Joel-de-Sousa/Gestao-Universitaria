package WSEdicao.datamodel;

import WSEdicao.domain.entities.Uc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UcJpaTest {

    @Test
    void shouldCreateANewUcJpa() {
        new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldNotCreateANewUcJpaWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new UcJpa("POG","PogChampion");
        });
        String expectedMessage = "A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAUcJpaWithCorrectAttributes() {
        UcJpa uc = new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");

        assertEquals(uc.getSSigla(), "POOJ");
        assertEquals(uc.getSDenominacao(), "ProgramacaoOrientadaAObjetos");
    }


    @Test
    void shouldReturnEqualSameUcJpa() {
        UcJpa uc1 = new UcJpa("POG","PogChampion");
        assertEquals(uc1,uc1);
    }
    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        UcJpa uc1 = new UcJpa ("POG","PogChampion");
        UcJpa uc2 = new UcJpa ("POOJ","ProgramacaoOrientadaAObjetos");

        assertNotEquals(uc1,uc2);
    }

}