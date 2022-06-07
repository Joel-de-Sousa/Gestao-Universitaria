package org.sprint.model.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.repository.REST.EdicaoRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class EdicaoWebRepositoryTest {

    @Mock
    EdicaoRestRepository edicaoRestRepository;

    @InjectMocks
    EdicaoWebRepository edicaoWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldGetListDeEdicoesWithCorrectAttributes() {
        // Arrange
        EdicaoRestDTO edicao1 = mock(EdicaoRestDTO.class) ;
        EdicaoRestDTO edicao2 = mock(EdicaoRestDTO.class) ;
        List<EdicaoRestDTO> listDouble = new ArrayList<>();
        listDouble.add(edicao1);
        listDouble.add(edicao2);
        Optional<List<EdicaoRestDTO>> opList = Optional.of(listDouble);

        when(edicaoRestRepository.getAllEdicoes()).thenReturn(opList);

        // Act
        List<EdicaoRestDTO> listEdicoes = edicaoWebRepository.getListaEdicoes();
        // Assert
        assertEquals(2, listEdicoes.size());

    }


    @SneakyThrows
    @Test
    @DisplayName("Teste criar Edicao")
    void shouldCreateEdicaoWithCorrectAttributes() {
        // Arrange
        EdicaoRestDTO edicao = mock(EdicaoRestDTO.class) ;

        when(edicaoRestRepository.createEdicao(edicao)).thenReturn(true);

        // Act
        boolean edicaoCriada = edicaoWebRepository.criarNovaEdicao(edicao);

        // Assert
        assertTrue(edicaoCriada);

    }

}

