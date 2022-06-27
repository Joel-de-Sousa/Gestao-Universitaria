package WSEdicao.datamodel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EstudanteJpaTest {

    @Test
    void shouldCreateANewEstudante() {
        new EstudanteJpa(1,1);
    }

    @Test
    void shouldCreateAEstudanteJpaWithCorrectAttributes() {
        EstudanteJpa estudante1 = new EstudanteJpa(1,1);

        assertEquals(estudante1.getCodEdicao(), 1);
        assertEquals(estudante1.getCodEstudante(),1);
    }


    @Test
    void shouldReturnTheSameEstudanteJpa() {
        EstudanteJpa estudante1 = new EstudanteJpa(1,1);
        assertEquals(estudante1,estudante1);
    }
    @Test
    void shouldReturnNotEqualsDifferentEstudanteJpa(){
        EstudanteJpa estudante1 = new EstudanteJpa (1,1);
        EstudanteJpa estudante2 = new EstudanteJpa (1,2);

        assertNotEquals(estudante1,estudante2);
    }
}