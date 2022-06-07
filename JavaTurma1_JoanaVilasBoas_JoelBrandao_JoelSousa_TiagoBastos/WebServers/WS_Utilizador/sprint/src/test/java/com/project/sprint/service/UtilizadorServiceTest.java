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
    IUtilizadorFactory utilizadorFactory;

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

    @Test
    void shouldCreateUtilizadorSearchingByID() {

        //ARRANGE

        NewUtilizadorInfoDTO utilizadorInfoDTO = mock(NewUtilizadorInfoDTO.class);
        Utilizador utilizador1 = mock(Utilizador.class);


        when(utilizadorInfoDTO.getNome()).thenReturn("Nome");
        when(utilizadorInfoDTO.getSobrenome()).thenReturn("Nome");
        when(utilizadorInfoDTO.getEmail()).thenReturn("joana.gvb@gmail.com");
        when(utilizadorInfoDTO.getTipoUtilizador()).thenReturn("ESTUDANTE");

        when(utilizadorFactory.createUtilizador(utilizadorInfoDTO.getNome(), utilizadorInfoDTO.getSobrenome(),
                utilizadorInfoDTO.getEmail(), Utilizador.TipoUtilizador.valueOf(utilizadorInfoDTO.getTipoUtilizador()))).thenReturn(utilizador);

        when(utilizadorRepository.save(utilizador)).thenReturn(utilizador1);

        when(utilizadorDomainDTOAssembler.toDTO(utilizador1)).thenReturn(utilizadorDTO);


        //ACT
        UtilizadorDTO utilizadorSaved = utilizadorService.createAndSaveUtilizador(utilizadorInfoDTO);


        //ASSERT
        assertEquals(utilizadorDTO, utilizadorSaved);


    }
}

