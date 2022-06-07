package org.sprint.model.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.repository.AnoLetivoWebRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnoLetivoServiceTest {

    @Mock
    AnoLetivoWebRepository anoLetivoWebRepository;
    @Mock
    AnoLetivoRestDTO anoLetivoRestDTO;

    @InjectMocks
    AnoLetivoService anoLetivoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get List Anos Correta service")
    void shouldGetListAnosWithCorrectAttributes() {
        // Arrange

        List<AnoLetivoRestDTO> listDouble = new ArrayList<>();
        listDouble.add(anoLetivoRestDTO);
        when(anoLetivoWebRepository.getListaAnosLetivos()).thenReturn(listDouble);
        // Act
        List<AnoLetivoRestDTO> listEdicoes = anoLetivoWebRepository.getListaAnosLetivos();
        // Assert
        assertEquals(listDouble,listEdicoes);
    }

    @SneakyThrows
    @Test
    @DisplayName("Teste criar Ano service")
    void shouldCreateAnoWithCorrectAttributes() {
        // Arrange

        when(anoLetivoWebRepository.criarNovoAnoLetivo(anoLetivoRestDTO)).thenReturn(true);
        // Act
        boolean anoCriado = anoLetivoWebRepository.criarNovoAnoLetivo(anoLetivoRestDTO);
        // Assert
        assertTrue(anoCriado);

    }

}