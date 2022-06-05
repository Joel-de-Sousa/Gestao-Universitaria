package org.sprint.controllers;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.service.EdicaoService;
import org.sprint.model.service.PropostaService;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PropostaControllerTest {

    @Mock
    PropostaService propostaService;
    @Mock
    EdicaoService edicaoService;
    @InjectMocks
    PropostaController propostaController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get List Edicoes para criar proposta")
    void shouldGetListEdicoesWithCorrectAttributes() {
        // Arrange

        String edicao1 = "PDSI 2022/2023";
        String edicao2 = "POOI 2023/2024";
        List<String> listDouble = new ArrayList<>();
        listDouble.add(edicao1);
        listDouble.add(edicao2);

        when(edicaoService.getListaEdicoes()).thenReturn(listDouble);

        // Act
        List<String> listEdicoes = propostaController.getListaEdicoes();
        // Assert
        assertEquals(2, listEdicoes.size());

    }

    @Test
    @DisplayName("Teste criar Proposta")
    void shouldCreatePropostaWithCorrectAttributes() throws Exception {
        // Arrange
        when(propostaService.criarNovaProposta(1, 257837248, 1, "Titulo Teste", "Problema Teste", "Objectivo teste")).thenReturn(true);
        // Act
        boolean edicaoCriada = propostaController.criarNovaProposta(1, 257837248, 1, "Titulo Teste", "Problema Teste", "Objectivo teste");
        // Assert
        assertTrue(edicaoCriada);

    }
}