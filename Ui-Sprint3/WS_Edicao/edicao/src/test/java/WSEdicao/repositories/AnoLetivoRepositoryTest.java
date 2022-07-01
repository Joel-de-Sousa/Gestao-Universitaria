package WSEdicao.repositories;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.assemblers.AnoLetivoDomainDataAssembler;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.repositories.jpa.AnoLetivoJpaRepository;
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
class AnoLetivoRepositoryTest {

    @MockBean
    AnoLetivoDomainDataAssembler anoLetivoDomainDataAssembler;

    @MockBean
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @MockBean
    AnoLetivoJpaRepository anoLetivoJpaRepository;

    @MockBean
    AnoLetivo anoLetivo;
    @MockBean
    AnoLetivoJpa anoLetivoJpa;

    @InjectMocks
    AnoLetivoRepository anoLetivoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAnoLetivo() throws Exception {
        //Arrange
        when(anoLetivoDomainDataAssembler.toData(anoLetivo)).thenReturn(anoLetivoJpa);

        AnoLetivoJpa savedAnoLetivoJpa = mock(AnoLetivoJpa.class);
        when(anoLetivoJpaRepository.save(anoLetivoJpa)).thenReturn(savedAnoLetivoJpa);

        when(anoLetivoDomainDataAssembler.toDomain(savedAnoLetivoJpa)).thenReturn(anoLetivo);

        //Act
        AnoLetivo savedAnoLetivo = anoLetivoRepository.save(anoLetivo);

        //Assert
        assertEquals(savedAnoLetivo, anoLetivo);
    }

    @Test
    void shouldFindSpecificAnoLetivoSearchingById() {
        //Arrange
        AnoLetivoJpa anoLetivoDouble = mock(AnoLetivoJpa.class);
        Optional<AnoLetivoJpa> opAnoLetivoDouble = Optional.of(anoLetivoDouble);

        when(anoLetivoJpaRepository.findBycodAnoLetivo(1)).thenReturn(opAnoLetivoDouble);

        AnoLetivoJpa anoLetivoJpa = opAnoLetivoDouble.get();
        AnoLetivo anoLetivo = mock(AnoLetivo.class);
        when(anoLetivoDomainDataAssembler.toDomain(anoLetivoJpa)).thenReturn(anoLetivo);
        AnoLetivoDTO anoLetivoDTO = mock(AnoLetivoDTO.class);
        when(anoLetivoDTOAssembler.toDTO(anoLetivo)).thenReturn(anoLetivoDTO);

        //Act
        Optional<AnoLetivoDTO> opAnoLetivoAct = anoLetivoRepository.findBycodAnoLetivo(1);

        //Assert
        Optional<AnoLetivoDTO> opAnoLetivo = Optional.of(anoLetivoDTO);

        assertEquals(opAnoLetivoAct, opAnoLetivo);
    }

    @Test
    void findAll() {
        //Arrange
        AnoLetivoJpa anoLetivoJpa1 = mock(AnoLetivoJpa.class);
        when(anoLetivoJpa1.getAno()).thenReturn("2015-2016");


        AnoLetivoJpa anoLetivoJpa2 = mock(AnoLetivoJpa.class);
        when(anoLetivoJpa2.getAno()).thenReturn("2016-2017");


        List<AnoLetivoJpa> listAnoLetivoJpa = new ArrayList<>();
        listAnoLetivoJpa.add(anoLetivoJpa1);
        listAnoLetivoJpa.add(anoLetivoJpa2);

        when(anoLetivoJpaRepository.findAll()).thenReturn(listAnoLetivoJpa);

        List<AnoLetivo> listAnoLetivo = new ArrayList<>();
        for (AnoLetivoJpa anoLetivoJpa : listAnoLetivoJpa) {
            when(anoLetivoDomainDataAssembler.toDomain(anoLetivoJpa)).thenReturn(anoLetivo);
            listAnoLetivo.add(anoLetivo);
        }

        //Act
        List<AnoLetivo> listAnoLetivoAct = anoLetivoRepository.findAll();

        //Assert
        assertEquals(listAnoLetivoAct, listAnoLetivo);
        assertTrue(listAnoLetivoAct.size() == 2);
    }
}

