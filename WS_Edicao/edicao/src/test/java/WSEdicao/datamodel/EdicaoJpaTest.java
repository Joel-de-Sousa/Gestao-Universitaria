package WSEdicao.datamodel;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoJpaTest {

    @Test
    void shouldCreateANewEdicaoJpa() {
        new EdicaoJpa(1, 1);
    }

    @Test
    void shouldCreateAEdicaoJpaWithCorrectAttributes() {
        EdicaoJpa edicao = new EdicaoJpa(1, 1);

        assertEquals(edicao.getCodUc(), 1);
        assertEquals(edicao.getCodAnoLetivo(),1);
    }


    @Test
    void shouldReturnEqualSameEdicao() {
        EdicaoJpa edicao = new EdicaoJpa(1, 1);

        assertEquals(edicao, edicao);
    }

    @Test
    void shouldReturnNotEqualsDifferentEdicaoJpa() {
        EdicaoJpa edicao1 = new EdicaoJpa(1, 1);
        EdicaoJpa edicao2 = new EdicaoJpa(2, 2);

        assertNotEquals(edicao1, edicao2);
    }
}