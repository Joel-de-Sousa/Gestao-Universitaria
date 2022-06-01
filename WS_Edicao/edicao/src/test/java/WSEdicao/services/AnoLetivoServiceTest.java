package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.factories.AnoLetivoFactory;
import WSEdicao.repositories.AnoLetivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnoLetivoServiceTest {


    @MockBean
    AnoLetivoRepository anoLetivoRepository;

    @MockBean
    AnoLetivoFactory anoLetivoFactory;

    @MockBean
    AnoLetivo anoLetivo;

    @InjectMocks
    AnoLetivoService anoLetivoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAAnoLetivoWithCorrectAttributes() {
        // Arrange
        when(anoLetivo.getAno()).thenReturn("2015-2016");

        when( anoLetivoFactory.createAnoLetivo("2015-2016")).thenReturn(anoLetivo);

        when(anoLetivoRepository.save( anoLetivo )).thenReturn(anoLetivo);

        // Act
        AnoLetivo anoLetivo1 = anoLetivoService.createAndSaveAnoLetivo("2015-2016");

        String sAno = anoLetivo1.getAno();

        // Assert
        assertEquals(anoLetivo,anoLetivo1);

        assertEquals(sAno, "2015-2016");
    }

    @Test
    void shouldFindSpecificAnoLetivoSearchingById() {
        // Arrange
        when(anoLetivo.getAno()).thenReturn("2015-2016");

        Optional<AnoLetivo> opAnoLetivo = Optional.of(anoLetivo);

        when(anoLetivoRepository.findByCode(1)).thenReturn(opAnoLetivo);

        // Act
        Optional<AnoLetivo> anoLetivo1 = anoLetivoService.getAnoLetivoByCode(1);

        String sANo = anoLetivo1.get().getAno();

        // Assert
        assertEquals(anoLetivo1,opAnoLetivo);

        assertEquals(sANo, "2015-2016");
    }

    @Test
    void shouldFindEveryAnoLetivoCreatedCorrectly() {

        AnoLetivo anoLetivoDouble = mock(AnoLetivo.class);
        when(anoLetivoDouble.getAno()).thenReturn("2015-2016");

        AnoLetivo anoLetivoDouble2 = mock(AnoLetivo.class);
        when(anoLetivoDouble2.getAno()).thenReturn("2017-2018");


        List<AnoLetivo> listAnoLetivoAux = new ArrayList<>();
        listAnoLetivoAux.add(anoLetivoDouble);
        listAnoLetivoAux.add(anoLetivoDouble2);

        when(anoLetivoRepository.findAll()).thenReturn(listAnoLetivoAux);

        // Act
        List<AnoLetivo> listAnoLetivoAct = anoLetivoService.getAllAnoLetivo();

        // Assert
        assertEquals(listAnoLetivoAux, listAnoLetivoAct);
        assertTrue(listAnoLetivoAct.size()==2);
    }

}