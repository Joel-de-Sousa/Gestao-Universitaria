package org.sprint.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.service.AnoLetivoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnoLetivoControllerTest {

    @Mock
    AnoLetivoService anoLetivoService;

    @InjectMocks
    AnoLetivoController anoLetivoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Teste criar Ano")
    void shouldCreateAnoWithCorrectAttributes() throws Exception {
        // Arrange

        when(anoLetivoService.criarNovoAnoLetivo("2020")).thenReturn(true);

        // Act
        boolean anoCriado = anoLetivoController.criarNovoAnoLetivo("2020");

        // Assert
        assertTrue(anoCriado);

    }

    @Test
    @DisplayName("Teste criar Ano")
    void shouldGerarListAnosAnoWithCorrectAttributes() {
        // Arrange
        List<String> listTest = new ArrayList<>();
        for (int ano = 1950; ano < 2051; ano++) {
            listTest.add(String.valueOf(ano));
        }

        // Act
        List<String> lista = anoLetivoController.listaAnosGerada();

        // Assert
        assertEquals(listTest, lista);
    }

    @Test
    @DisplayName("Teste criar Ano")
    void shouldFormatarAnoWithCorrectAttributes() {
        // Arrange
        String ano = "2020-2021";

        // Act
        String anoFormatado = anoLetivoController.formatarAno("2020");

        // Assert
        assertEquals(ano, anoFormatado);
    }


    @Test
    void shouldGetListDeAnosWithCorrectSize() {
        // Arrange
        String sigla1 = "2021-2022";
        String sigla2 = "2019-2020";
        List<String> listDouble = new ArrayList<>();
        listDouble.add(sigla1);
        listDouble.add(sigla2);
        when(anoLetivoService.getListaAnos()).thenReturn(listDouble);

        // Act
        List<String> listAnos = anoLetivoController.getListaAnos();
        // Assert
        assertEquals(2, listAnos.size());

    }

    @Test
    void shouldGetListDeAnosWithCorrectAttributes() {
        // Arrange
        String sigla1 = "2021-2022";
        String sigla2 = "2019-2020";
        List<String> listDouble = new ArrayList<>();
        listDouble.add(sigla1);
        listDouble.add(sigla2);
        when(anoLetivoService.getListaAnos()).thenReturn(listDouble);

        // Act
        List<String> listAnos = anoLetivoController.getListaAnos();
        // Assert
        assertEquals(listAnos.toString(), listDouble.toString());

    }

}