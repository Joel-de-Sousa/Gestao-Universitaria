package org.sprint.model.repository;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.AnoLetivoRestRepository;
import org.sprint.model.service.AnoLetivoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnoLetivoWebRepositoryTest {

    @Mock
    AnoLetivoRestRepository anoLetivoRestRepository;
    @Mock
    AnoLetivoRestDTO anoLetivoRestDTO;

    @InjectMocks
    AnoLetivoWebRepository anoLetivoWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get List Anos Correta service")
    void shouldGetListAnosWithCorrectAttributes() {
        // Arrange
        List<AnoLetivoRestDTO> anoLetivoRestDTOS= new ArrayList<>();
        AnoLetivoRestDTO anoLetivoRestDTO1=mock(AnoLetivoRestDTO.class);
        anoLetivoRestDTOS.add(anoLetivoRestDTO);
        anoLetivoRestDTOS.add(anoLetivoRestDTO1);

        when(anoLetivoRestRepository.getAllAno()).thenReturn(Optional.of(anoLetivoRestDTOS));

        // Act
        Optional<List<AnoLetivoRestDTO>> opLista=anoLetivoRestRepository.getAllAno();
        List<AnoLetivoRestDTO> lista=opLista.get();

        // Assert
        assertEquals(lista,anoLetivoRestDTOS);
    }


    @SneakyThrows
    @Test
    @DisplayName("Teste criar Ano service")
    void shouldCreateAnoWithCorrectAttributes() {
        // Arrange

        when(anoLetivoRestRepository.createAnoLetivo(anoLetivoRestDTO)).thenReturn(true);
        // Act
        boolean anoCriado = anoLetivoRestRepository.createAnoLetivo(anoLetivoRestDTO);
        // Assert
        assertTrue(anoCriado);

    }

}