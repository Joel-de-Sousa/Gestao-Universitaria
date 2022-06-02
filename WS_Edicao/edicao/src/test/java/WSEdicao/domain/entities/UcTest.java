package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UcTest {

    @Test
    void shouldCreateANewUc() {
        new Uc("POOJ","ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldNotCreateANewUcWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Uc("PG","Pog");
        });
        String expectedMessage = "A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void shouldCreateAUcWithCorrectAttributes() {
        Uc uc = new Uc("POOJ","ProgramacaoOrientadaAObjetos");

        assertEquals(uc.getSigla(), "POOJ");
        assertEquals(uc.getDenominacao(), "ProgramacaoOrientadaAObjetos");
    }


    @Test
    void shouldReturnEqualSameUc() {
        Uc uc1 = new Uc("POG","PogChampion");
        assertEquals(uc1,uc1);
    }
    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        Uc uc1 = new Uc ("POG","PogChampion");
        Uc uc2 = new Uc ("POOJ","ProgramacaoOrientadaAObjetos");

        assertNotEquals(uc1,uc2);
    }

}