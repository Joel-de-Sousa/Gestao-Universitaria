package WSEdicao.domain.factories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UcFactoryTest {

    @Test
    void factoryShouldCreateUc() {
        UcFactory ucFactory = new UcFactory();
        ucFactory.createUc("POOJ","ProgramacaoOrientadaAObjetos");
    }
    @Test
    void factoryShouldNotCreateUcEmptyName() {
        UcFactory ucFactory = new UcFactory();
        assertThrows(Exception.class, () -> {
            ucFactory.createUc("PO", "");
        });
    }
    @Test
    void factoryShouldNotCreateUcNullName() {
        UcFactory ucFactory = new UcFactory();
        assertThrows(Exception.class, () -> {
            ucFactory.createUc(null, null);
        });
    }

}