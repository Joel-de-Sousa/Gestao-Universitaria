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
        UcJpa uc= new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivoJpa anoLetivo = new AnoLetivoJpa("2015-2016");
        new EdicaoJpa(uc,anoLetivo);
    }

    @Test
    void shouldCreateAEdicaoJpaWithCorrectAttributes() {
        UcJpa uc= new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivoJpa anoLetivo = new AnoLetivoJpa("2015");
        EdicaoJpa edicao = new EdicaoJpa(uc,anoLetivo);

        assertEquals(edicao.getCodUc(), uc);
        assertEquals(edicao.getCodAnoLetivo(),anoLetivo);
    }


    @Test
    void shouldReturnEqualSameEdicao() {
        UcJpa uc= new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivoJpa anoLetivo = new AnoLetivoJpa("2015-2016");
        EdicaoJpa edicao = new EdicaoJpa(uc,anoLetivo);

        assertEquals(edicao,edicao);
    }

    @Test
    void shouldReturnNotEqualsDifferentLocais(){
        UcJpa uc1= new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivoJpa anoLetivo1 = new AnoLetivoJpa("2015-2016");
        EdicaoJpa edicao1 = new EdicaoJpa(uc1,anoLetivo1);
        UcJpa uc2= new UcJpa("POOJ","ProgramacaoOrientadaAObjetos");
        AnoLetivoJpa anoLetivo2 = new AnoLetivoJpa("2016-2017");
        EdicaoJpa edicao2 = new EdicaoJpa(uc2,anoLetivo2);

        assertNotEquals(edicao1,edicao2);
    }
}