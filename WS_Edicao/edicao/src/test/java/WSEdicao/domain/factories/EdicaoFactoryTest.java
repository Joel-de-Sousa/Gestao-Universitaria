
package WSEdicao.domain.factories;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EdicaoFactoryTest {

    @Test
    void factoryShouldCreateEdicao() {
        EdicaoFactory edicaoFactory = new EdicaoFactory();
        edicaoFactory.createEdicao(1, 1);
    }
}
