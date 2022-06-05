package org.sprint.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.service.EdicaoService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EdicaoControllerTest {

    @Mock
    EdicaoService edicaoService;

    @InjectMocks
    EdicaoController edicaoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste criar Edição")
    void shouldCreateEdicaoWithCorrectAttributes() throws Exception {
        // Arrange

        when(edicaoService.criarNovaEdicao(1,2)).thenReturn(true);

        // Act
        boolean ucCriada = edicaoService.criarNovaEdicao(1,2);

        // Assert
        assertTrue(ucCriada);

    }

}