package org.sprint.model.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.AnoLetivoWebRepository;
import org.sprint.model.repository.UcWebRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UcServiceTest {
    @Mock
    UcWebRepository ucWebRepository;
    @Mock
    UcRestDTO ucRestDTO;

    @InjectMocks
    UcService ucService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    public String getDenominacao(String sigla){
        List<UcRestDTO> lista =ucWebRepository.getListaUc();
        String denominacao="";
        for (UcRestDTO a: lista) {
            if (a.getSigla().equals(sigla)){
                denominacao=a.getDenominacao();}
        }
        return denominacao;
    }

    @Test
    @DisplayName("Teste Get Denominacao correta")
    void shouldGetDenominacaoWithCorrectAttribute(){
        // Arrange
        String denominacao="teste denominacao teste";
        String sigla="AAAA";
        String teste="";
        List<UcRestDTO> listDouble = new ArrayList<>();
        listDouble.add(ucRestDTO);
        when(ucWebRepository.getListaUc()).thenReturn(listDouble);
        when(ucRestDTO.getDenominacao()).thenReturn(denominacao);
        when(ucRestDTO.getSigla()).thenReturn(sigla);
        // Act
        for (UcRestDTO a: listDouble) {
            if (a.getSigla().equals(sigla)){
               teste=a.getDenominacao();}
        }
        assertEquals(teste,denominacao);
    }

    @Test
    @DisplayName("Teste Get List Siglas Correta service")
    void shouldGetListSiglasWithCorrectAttributes() {
        // Arrange

        List<UcRestDTO> listDouble = new ArrayList<>();
        listDouble.add(ucRestDTO);
        when(ucWebRepository.getListaUc()).thenReturn(listDouble);
        // Act
        List<UcRestDTO> listEdicoes = ucWebRepository.getListaUc();
        // Assert
        assertEquals(listDouble,listEdicoes);
    }

    @SneakyThrows
    @Test
    @DisplayName("Teste criar Ano service")
    void shouldCreateAnoWithCorrectAttributes() {
        // Arrange

        when(ucWebRepository.criarNovaUC(ucRestDTO)).thenReturn(true);
        // Act
        boolean ucCriada = ucWebRepository.criarNovaUC(ucRestDTO);
        // Assert
        assertTrue(ucCriada);

    }

}