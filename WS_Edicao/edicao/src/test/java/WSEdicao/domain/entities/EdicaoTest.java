package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoTest {

    @Test
    void shouldCreateANewEdicao() {
        Uc uc= new Uc("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivo anoLetivo = new AnoLetivo("2015");
        new Edicao(uc,anoLetivo);
    }

    /*@Test
    void shouldNotCreateANewEdicaoWithTheWrongFormat() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Edicao("PG","Pog");
        });
        String expectedMessage = "A Sigla deve conter no mínimo 3 carateres, sem espaços, sem símbolos e sem algarismos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }*/

    @Test
    void shouldCreateAEdicaoWithCorrectAttributes() {
        Uc uc= new Uc("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivo anoLetivo = new AnoLetivo("2015");
        Edicao edicao = new Edicao(uc,anoLetivo);

        assertEquals(edicao.getUc(), uc);
        assertEquals(edicao.getAnoLetivo(), anoLetivo);
    }


    @Test
    void shouldReturnEqualSameEdicao() {
        Uc uc= new Uc("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivo anoLetivo = new AnoLetivo("2015");
        Edicao edicao = new Edicao(uc,anoLetivo);
        assertEquals(edicao,edicao);
    }
    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        Uc uc1= new Uc("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivo anoLetivo1 = new AnoLetivo("2015");
        Edicao edicao1 = new Edicao(uc1,anoLetivo1);
        Uc uc2= new Uc("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivo anoLetivo2 = new AnoLetivo("2016");
        Edicao edicao2 = new Edicao(uc2,anoLetivo2);

        assertNotEquals(edicao1,edicao2);
    }
}