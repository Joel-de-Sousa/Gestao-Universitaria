package org.sprint.model.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.repository.REST.PropostaRestRepository;
import org.sprint.model.service.PropostaService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PropostaWebRepositoryTest {


    @Mock
    PropostaRestRepository propostaRestRepository;

    @Mock
    PropostaRestDTO propostaRestDTO;

    @InjectMocks
    PropostaWebRepository propostaWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste criar Proposta WebRepository true")
    void shouldCreateNewPropostaWitCorrectAttributes() throws Exception {
        //Arrange
        when(propostaRestRepository.createProposta(propostaRestDTO)).thenReturn(true);
        //Act
        boolean proposta =propostaRestRepository.createProposta(propostaRestDTO);
        //Assert
        assertTrue(proposta);

    }

    @Test
    @DisplayName("Teste criar Proposta WebRepository False")
    void shouldNotCreateNewPropostaBecauseReturnIsFalse() throws Exception {
        //Arrange
        when(propostaRestRepository.createProposta(propostaRestDTO)).thenReturn(false);
        //Act
        boolean proposta =propostaRestRepository.createProposta(propostaRestDTO);
        //Assert
        assertFalse(proposta);

    }

}