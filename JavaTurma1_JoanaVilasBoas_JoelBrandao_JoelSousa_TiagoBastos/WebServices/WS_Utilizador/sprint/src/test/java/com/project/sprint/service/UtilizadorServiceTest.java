package com.project.sprint.service;

import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.DTO.assembler.UtilizadorDomainDTOAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.domain.factories.IUtilizadorFactory;
import com.project.sprint.repository.UtilizadorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class UtilizadorServiceTest {


    @MockBean
    UtilizadorDomainDTOAssembler utilizadorDomainDTOAssembler;


    @MockBean
    UtilizadorDTO utilizadorDTO;

    @MockBean
    UtilizadorRepository utilizadorRepository;

    @MockBean
    IUtilizadorFactory iUtilizadorFactory;

    @MockBean
    Utilizador utilizador;

    @InjectMocks
    UtilizadorService utilizadorService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindUtilizadorSearchingByID() {


        //ARRANGE


        Optional<Utilizador> optionalUtilizador = Optional.of(utilizador);

        when(utilizadorRepository.findById(1)).thenReturn(optionalUtilizador);


        UtilizadorDTO utilizadorDTO1 = mock(UtilizadorDTO.class);

        when(utilizadorDomainDTOAssembler.toDTO(optionalUtilizador.get())).thenReturn(utilizadorDTO1);

        //ACT
        UtilizadorDTO utilizador1 = utilizadorService.getUtilizadorByID(1);


        //ASSERT
        assertEquals(utilizadorDTO1, utilizador1);


    }
}

