package org.sprint.model.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.AnoLetivoWebRepository;
import org.sprint.model.repository.EdicaoWebRepository;
import org.sprint.model.repository.UcWebRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


class EdicaoServiceTest {
    @Mock
    EdicaoWebRepository edicaoWebRepository;
    @Mock
    EdicaoRestDTO edicaoRestDTO;

    @InjectMocks
    EdicaoService edicaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get List Ediçoees  service")
    void shouldGetListEdicoesWithCorrectAttributes() {
        // Arrange
        List<EdicaoRestDTO> listDouble = new ArrayList<>();
        listDouble.add(edicaoRestDTO);
        when(edicaoWebRepository.getListaEdicoes()).thenReturn(listDouble);
        // Act
        List<EdicaoRestDTO> listEdicoes = edicaoWebRepository.getListaEdicoes();
        // Assert
        assertEquals(listDouble,listEdicoes);
    }

    @SneakyThrows
    @Test
    @DisplayName("Teste criar Ano service")
    void shouldCreateAnoWithCorrectAttributes() {
        // Arrange

        when(edicaoWebRepository.criarNovaEdicao(edicaoRestDTO)).thenReturn(true);
        // Act
        boolean anoCriado =edicaoWebRepository.criarNovaEdicao(edicaoRestDTO);
        // Assert
        assertTrue(anoCriado);

    }

}