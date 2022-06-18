package WSEdicao.domain.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoTest {

    /*@Test
    void shouldCreateANewEdicao() {
        new Edicao(1,1);
    }
    @Test
    void shouldCreateAEdicaoWithCorrectAttributes() {
        Edicao edicao = new Edicao(1,1);

        assertEquals(edicao.getUc(), 1);
        assertEquals(edicao.getAnoLetivo(), 1);
    }

    @Test
    void shouldReturnTheSameEdicao() {
        Edicao edicao = new Edicao(1,1);
        assertEquals(edicao,edicao);
    }
    @Test
    void shouldNotReturnTheSameEdicao(){
        Edicao edicao1 = new Edicao(1,1);
        Edicao edicao2 = new Edicao(2,2);

        assertNotEquals(edicao1,edicao2);
    }*/

    @Test
    void shouldAddMA(){
        Edicao edicao = new Edicao(1,1,1,1);
        MomentoAvaliacao ma = new MomentoAvaliacao(1,1,"sprint1");
        edicao.addMomentoAvaliacao(ma);

        assertEquals(edicao.getMomentoAvaliacaoList().size(),1);
    }


}