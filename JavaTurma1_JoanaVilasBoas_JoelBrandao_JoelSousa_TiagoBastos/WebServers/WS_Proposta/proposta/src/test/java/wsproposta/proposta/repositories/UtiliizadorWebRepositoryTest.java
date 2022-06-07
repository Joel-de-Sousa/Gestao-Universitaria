package wsproposta.proposta.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UtiliizadorWebRepositoryTest {


    @MockBean
    UtilizadorRestRepository utilizadorRestRepository;

    @InjectMocks
    UtiliizadorWebRepository utiliizadorWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Find Utilizador by CodUtilizador")
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
    @DisplayName("Teste Find Utilizador by CodUtilizador Return EmptyOptional")
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

