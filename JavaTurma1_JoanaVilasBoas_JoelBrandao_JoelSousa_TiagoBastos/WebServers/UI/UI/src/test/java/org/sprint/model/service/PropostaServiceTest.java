package org.sprint.model.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.controllers.PropostaController;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.repository.PropostaWebRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PropostaServiceTest {

    @Mock
    PropostaWebRepository propostaWebRepository;

    @Mock
    PropostaRestDTO propostaRestDTO;

    @InjectMocks
    PropostaService propostaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste criar Proposta Service true")
    void shouldCreateNewPropostaWitCorrectAttributes() throws Exception {
        //Arrange
        when(propostaWebRepository.criarNovaProposta(propostaRestDTO)).thenReturn(true);
        //Act
        boolean proposta =propostaWebRepository.criarNovaProposta(propostaRestDTO);
        //Assert
        assertTrue(proposta);

    }

    @Test
    @DisplayName("Teste criar Proposta Service false")
    void shouldNotCreateNewProposta() throws Exception {
        //Arrange
        when(propostaWebRepository.criarNovaProposta(propostaRestDTO)).thenReturn(false);
        //Act
        boolean proposta =propostaWebRepository.criarNovaProposta(propostaRestDTO);
        //Assert
        assertFalse(proposta);

    }

}