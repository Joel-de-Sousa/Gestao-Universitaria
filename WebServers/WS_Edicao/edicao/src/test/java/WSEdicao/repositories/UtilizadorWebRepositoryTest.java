package WSEdicao.repositories;

import WSEdicao.datamodel.REST.UtilizadorRestDTO;
import WSEdicao.repositories.REST.UtilizadorRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UtilizadorWebRepositoryTest {

    @MockBean
    UtilizadorRestRepository utilizadorRestRepository;

    @InjectMocks
    UtilizadorWebRepository utiliizadorWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUtilizadorByCodUtilizadorWithCorrectAttributes() {

        // Arrange
        UtilizadorRestDTO utilizadorRestDTODouble = mock(UtilizadorRestDTO.class);
        Optional<UtilizadorRestDTO> opUtilizadorRestDTODouble = Optional.of(utilizadorRestDTODouble);
        when(opUtilizadorRestDTODouble.get().getCodUtilizador()).thenReturn(1);

        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizadorRestDTODouble);

        // Act
        Optional<UtilizadorRestDTO> opUtilizadorRestDTO = utiliizadorWebRepository.findUtilizadorByCodUtilizador(1);

        // Assert
        assertEquals(opUtilizadorRestDTO, opUtilizadorRestDTO);
    }

    @Test
    void shouldReturnEmptyOptionalWhenFindUtilizadorByCodUtilizador() {

        // Arrange
        Optional<UtilizadorRestDTO> opUtilizadorRestDTODouble = Optional.empty();

        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizadorRestDTODouble);

        // Act
        Optional<UtilizadorRestDTO> opUtilizadorRestDTO = utiliizadorWebRepository.findUtilizadorByCodUtilizador(1);

        // Assert
        assertEquals(opUtilizadorRestDTO, opUtilizadorRestDTO);
    }

}