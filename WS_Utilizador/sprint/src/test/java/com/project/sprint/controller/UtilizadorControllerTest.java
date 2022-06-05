package com.project.sprint.controller;

import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.service.UtilizadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class UtilizadorControllerTest {


    @MockBean
    UtilizadorService utilizadorService;

    @InjectMocks
    UtilizadorController utilizadorController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetUtilizadorSearchingByID() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UtilizadorDTO utilizadorDTO = mock(UtilizadorDTO.class);

        when(utilizadorDTO.getCodUtilizador()).thenReturn(1);
        when(utilizadorDTO.getNome()).thenReturn("Joel");
        when(utilizadorDTO.getSobrenome()).thenReturn("Brandão");
        when(utilizadorDTO.getEmail()).thenReturn("JoelDocente@upskill.pt");

        when(utilizadorService.getUtilizadorByID(1)).thenReturn(utilizadorDTO);

        // Act
        ResponseEntity<Object> responseEntity = utilizadorController.getUtilizadorByID(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        UtilizadorDTO utilizadorResult = (UtilizadorDTO) responseEntity.getBody();

        assertEquals(utilizadorResult.getCodUtilizador(), 1);
        assertEquals(utilizadorResult.getNome(), "Joel");
        assertEquals(utilizadorResult.getSobrenome(), "Brandão");
        assertEquals(utilizadorResult.getEmail(), "JoelDocente@upskill.pt");
    }

    @Test
    void shouldCreateUtilizadorWithCorrectAttributes() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        UtilizadorDTO utilizadorDouble = mock(UtilizadorDTO.class);
        when(utilizadorDouble.getNome()).thenReturn("Joel");
        when(utilizadorDouble.getSobrenome()).thenReturn("Brandão");
        when(utilizadorDouble.getEmail()).thenReturn("@test");
        when(utilizadorDouble.getCodUtilizador()).thenReturn(1);
        when(utilizadorDouble.getTipoUtilizador()).thenReturn(String.valueOf(Utilizador.TipoUtilizador.DOCENTE));

        NewUtilizadorInfoDTO utilizadorDouble2 = mock(NewUtilizadorInfoDTO.class);
        when(utilizadorDouble2.getNome()).thenReturn("Joel");
        when(utilizadorDouble2.getSobrenome()).thenReturn("Brandão");
        when(utilizadorDouble2.getEmail()).thenReturn("@test");
        when(utilizadorDouble2.getCodUtilizador()).thenReturn(1);
        when(utilizadorDouble2.getTipoUtilizador()).thenReturn(String.valueOf(Utilizador.TipoUtilizador.DOCENTE));

        when(utilizadorService.createAndSaveUtilizador(utilizadorDouble2)).thenReturn(utilizadorDouble);

        // Act
        ResponseEntity<Object> responseEntity = utilizadorController.createUtilizador(utilizadorDouble2);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, utilizadorDouble);
    }

}