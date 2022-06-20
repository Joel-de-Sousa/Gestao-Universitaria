package WSEdicao.domain.factories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MomentoAvaliacaoFactoryTest {

    @Test
    void factoryShouldCreateMomentoAvaliacao() {
        MomentoAvaliacaoFactory momentoAvaliacaoFactory = new MomentoAvaliacaoFactory();
        momentoAvaliacaoFactory.createMomentoAvaliacao(1,"Sprint1");
    }
    @Test
    void factoryShouldNotCreateMomentoAvaliacaoEmptyDenominacao() {
        MomentoAvaliacaoFactory momentoAvaliacaoFactory = new MomentoAvaliacaoFactory();
        assertThrows(Exception.class, () -> {
            momentoAvaliacaoFactory.createMomentoAvaliacao(1,"");
        });
    }
    @Test
    void factoryShouldNotCreateMomentoAvaliacaoWithWrongFormat() {
        MomentoAvaliacaoFactory momentoAvaliacaoFactory = new MomentoAvaliacaoFactory();
        assertThrows(Exception.class, () -> {
            momentoAvaliacaoFactory.createMomentoAvaliacao(1,"9*");
        });
    }
    @Test
    void factoryShouldNotCreateMomentoAvaliacaoNullName() {
        MomentoAvaliacaoFactory momentoAvaliacaoFactory = new MomentoAvaliacaoFactory();
        assertThrows(Exception.class, () -> {
            momentoAvaliacaoFactory.createMomentoAvaliacao(1,null);
        });
    }

}