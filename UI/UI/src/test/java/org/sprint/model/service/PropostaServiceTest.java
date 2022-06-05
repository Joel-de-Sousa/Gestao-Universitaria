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


    @InjectMocks
    PropostaService propostaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateNewPropostaWitCorrectAttributes() throws Exception {
        when(propostaWebRepository.criarNovaProposta())

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

    public boolean criarNovaProposta(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) throws Exception {
        PropostaRestDTO novo= new PropostaRestDTO(codUtilizador,nifOrganizacao,codEdicao,titulo,problema,objetivo);
        boolean valid=propostaWebRepository.criarNovaProposta(novo);
        return valid;
    }


}