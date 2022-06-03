
package WSEdicao.services;

import WSEdicao.datamodel.assemblers.AnoLetivoDomainDataAssembler;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.factories.AnoLetivoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
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
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @MockBean
    AnoLetivoRepository anoLetivoRepository;

    @MockBean
    AnoLetivoFactory anoLetivoFactory;

    @MockBean
    AnoLetivoDTO anoLetivoDTO;

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
        when(anoLetivo.getAno()).thenReturn("2015");

        when(anoLetivoFactory.createAnoLetivo("2015")).thenReturn(anoLetivo);

        when(anoLetivoRepository.save(anoLetivo)).thenReturn(anoLetivo);

        AnoLetivoDTO anoLetivoDTO = anoLetivoDTOAssembler.toDTO(anoLetivo);

        // Act
        AnoLetivoDTO anoLetivo1 = anoLetivoService.createAndSaveAnoLetivo("2015");

        // Assert
        assertEquals(anoLetivoDTO, anoLetivo1);
    }

    @Test
    void shouldFindSpecificAnoLetivoSearchingById() {
        //Arrange
        when(anoLetivoDTO.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDTO.getAno()).thenReturn("2015-2016");

        Optional<AnoLetivoDTO> opAnoLetivo = Optional.of(anoLetivoDTO);

        when(anoLetivoRepository.findBycodAnoLetivo(1)).thenReturn(opAnoLetivo);

        // Act
        Optional<AnoLetivoDTO> anoLetivo1 = anoLetivoService.getAnoLetivoByCode(1);
        String sANo = anoLetivo1.get().getAno();

        // Assert
        assertEquals(anoLetivo1, opAnoLetivo);
        assertEquals(sANo, "2015-2016");
    }

    @Test
    void shouldFindEveryAnoLetivoCreatedCorrectly() {

        AnoLetivo anoLetivoDouble = mock(AnoLetivo.class);
        when(anoLetivoDouble.getAno()).thenReturn("2015");

        AnoLetivo anoLetivoDouble2 = mock(AnoLetivo.class);
        when(anoLetivoDouble2.getAno()).thenReturn("2017");


        List<AnoLetivo> listAnoLetivoAux = new ArrayList<>();
        listAnoLetivoAux.add(anoLetivoDouble);
        listAnoLetivoAux.add(anoLetivoDouble2);

        when(anoLetivoRepository.findAll()).thenReturn(listAnoLetivoAux);

        List<AnoLetivoDTO> listaDto=new ArrayList<>();
        for (AnoLetivo anoLetivo:listAnoLetivoAux) {
            AnoLetivoDTO anoLetivoDTO = anoLetivoDTOAssembler.toDTO(anoLetivo);
            listaDto.add(anoLetivoDTO);
        }


        // Act
        List<AnoLetivoDTO> listAnoLetivoAct = anoLetivoService.getAllAnoLetivo();

        // Assert
        assertEquals(listaDto , listAnoLetivoAct);
        assertEquals(2, listAnoLetivoAct.size());
    }
}


