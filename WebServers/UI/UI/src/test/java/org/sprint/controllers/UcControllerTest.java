package org.sprint.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.service.AnoLetivoService;
import org.sprint.model.service.UcService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UcControllerTest {

    @Mock
    UcService ucService;

    @InjectMocks
    UcController ucController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Teste criar Uc")
    void shouldCreateUCWithCorrectAttributes() throws Exception {
        // Arrange

        when(ucService.criarNovaUC("AAAA","teste de denominacao")).thenReturn(true);

        // Act
        boolean ucCriada = ucService.criarNovaUC("AAAA","teste de denominacao");

        // Assert
        assertTrue(ucCriada);

    }

    @Test
   void shouldGetListDeUnidadesCurricularesWithCorrectSize(){
        // Arrange
        String uc1="AAAA: qwertyZXCVB";
        String uc2="AAAB: zxcvbQWERTY";
        List<String> listaDouble=new ArrayList<>();
        listaDouble.add(uc1);
        listaDouble.add(uc2);

        when(ucService.getListaUnidadeCurricular()).thenReturn(listaDouble);
        // Act
        List<String> lista = ucService.getListaUnidadeCurricular();
        // Assert
        assertEquals(2,lista.size());
    }

    @Test
    void shouldGetListDeUnidadesCurricularesWithTheSameAttributes(){
        // Arrange
        String uc1="AAAA: qwertyZXCVB";
        String uc2="AAAB: zxcvbQWERTY";
        List<String> listaDouble=new ArrayList<>();
        listaDouble.add(uc1);
        listaDouble.add(uc2);

        when(ucService.getListaUnidadeCurricular()).thenReturn(listaDouble);
        // Act
        List<String> lista = ucService.getListaUnidadeCurricular();
        // Assert
        assertEquals(listaDouble,lista);
    }

    @Test
    void shouldGetListDeSiglasWithCorrectSize(){
        // Arrange
        String uc1="AAAA";
        String uc2="AAAB";
        List<String> listaDouble=new ArrayList<>();
        listaDouble.add(uc1);
        listaDouble.add(uc2);

        when(ucService.getListaSiglas()).thenReturn(listaDouble);
        // Act
        List<String> lista = ucService.getListaSiglas();
        // Assert
        assertEquals(2,lista.size());
    }


    @Test
    void shouldGetListaSiglasWithTheSameAttributes(){
        // Arrange
        String uc1="AAAA";
        String uc2="AAAB";
        List<String> listaDouble=new ArrayList<>();
        listaDouble.add(uc1);
        listaDouble.add(uc2);

        when(ucService.getListaSiglas()).thenReturn(listaDouble);
        // Act
        List<String> lista = ucService.getListaSiglas();
        // Assert
        assertEquals(listaDouble,lista);
    }

    @Test
    void shouldGetDenominacao(){
        // Arrange
        String denominacaoDouble="AAAA";

        when(ucService.getDenominacao(denominacaoDouble)).thenReturn(denominacaoDouble);
        // Act
        String result=ucService.getDenominacao("AAAA");
        // Assert
        assertEquals(denominacaoDouble,result);
    }

    @Test
    void shouldNotGetDenominacao(){
        // Arrange
        String denominacaoDouble="AAAA";

        when(ucService.getDenominacao(denominacaoDouble)).thenReturn(denominacaoDouble);
        // Act
        String result=ucService.getDenominacao("ABAA");
        // Assert
        assertNotEquals(denominacaoDouble,result);
    }



}