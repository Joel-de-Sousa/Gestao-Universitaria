package org.sprint.model.repository;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UcWebRepositoryTest {


    @Mock
    UcRestRepository ucRestRepository;

    @InjectMocks
    UcWebRepository ucWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldGetListDeUcWithCorrectAttributes() {
        // Arrange
        UcRestDTO uc1 = mock(UcRestDTO.class) ;
        UcRestDTO uc2 = mock(UcRestDTO.class) ;
        List<UcRestDTO> listDouble = new ArrayList<>();
        listDouble.add(uc1);
        listDouble.add(uc2);
        Optional<List<UcRestDTO>> opList = Optional.of(listDouble);
        when(ucRestRepository.getAllUc()).thenReturn(opList);

        // Act
        List<UcRestDTO> listUcs = ucWebRepository.getListaUc();
        // Assert
        assertEquals(2, listUcs.size());

    }


    @SneakyThrows
    @Test
    @DisplayName("Teste criar Uc")
    void shouldCreateAnoWithCorrectAttributes() {
        // Arrange
        UcRestDTO uc1 = mock(UcRestDTO.class) ;

        when(ucRestRepository.createUc(uc1)).thenReturn(true);

        // Act
        boolean ucCriado = ucWebRepository.criarNovaUC(uc1);

        // Assert
        assertTrue(ucCriado);

    }

}