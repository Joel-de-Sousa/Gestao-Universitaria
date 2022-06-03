package wsproposta.proposta.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.services.PropostaService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaControllerTest {

    @MockBean
    PropostaService propostaService;
    @InjectMocks
    PropostaController propostaController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get Proposta by Code")
    void shouldGetPropostaByCodeWithCorrectAttributes() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        PropostaDTO propostaDouble = mock(PropostaDTO.class);

        when( propostaDouble.getCodUtilizador()).thenReturn(1);
        when( propostaDouble.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble.getCodEdicao() ).thenReturn(1);
        when( propostaDouble.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        when( propostaService.getPropostaById(1)).thenReturn(propostaDouble);

        // Act
        ResponseEntity<Object> responseEntity = propostaController.getPropostaByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        PropostaDTO propostaResult = (PropostaDTO) responseEntity.getBody();

        assertEquals(propostaResult.getCodUtilizador(), 1);
        assertEquals(propostaResult.getNifOrganizacao(), 257837248L);
        assertEquals(propostaResult.getCodEdicao(), 1);
        assertEquals(propostaResult.getTitulo(), "Titulo da Proposta");
        assertEquals(propostaResult.getProblema(), "Problema da Proposta");
        assertEquals(propostaResult.getObjetivo(), "Objectivo da proposta");
        assertEquals(propostaResult.getEstado(), "PENDENTE");

    }

    @Test
    @DisplayName("Teste Get Proposta By Code Return Null")
    void shouldREturnNotFoundIfGetPropostaByCodeReturnNull() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when( propostaService.getPropostaById(1)).thenReturn(null);

        // Act
        ResponseEntity<Object> responseEntity = propostaController.getPropostaByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 404);
        assertEquals(responseEntity.getBody(), "O codigo da proposta nao consta na Base de Dados");
    }


    @Test
    @DisplayName("Teste Get All Propostas")
    void shouldGetAllPropostasWithCorrectAttributes() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        PropostaDTO propostaDouble = mock(PropostaDTO.class);

        when( propostaDouble.getCodUtilizador()).thenReturn(1);
        when( propostaDouble.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble.getCodEdicao() ).thenReturn(1);
        when( propostaDouble.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        PropostaDTO propostaDouble2 = mock(PropostaDTO.class);

        when( propostaDouble2.getCodUtilizador()).thenReturn(1);
        when( propostaDouble2.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble2.getCodEdicao() ).thenReturn(1);
        when( propostaDouble2.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble2.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble2.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble2.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        List<PropostaDTO> listDTO = new ArrayList<>();
        listDTO.add(propostaDouble);
        listDTO.add(propostaDouble2);

        when( propostaService.findAll()).thenReturn(listDTO);

        // Act
        ResponseEntity<Object> responseEntity = propostaController.getAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

       List<PropostaDTO> listResult = (List<PropostaDTO>) responseEntity.getBody();

       assertEquals(listResult, listDTO);
    }

    @Test
    @DisplayName("Teste Post Proposta")
    void shouldCreatePropostaWithCorrectAttributes() throws Exception {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        NewPropostaInfoDTO propostaInfoDouble = mock(NewPropostaInfoDTO.class);

        when( propostaInfoDouble.getCodUtilizador()).thenReturn(1);
        when( propostaInfoDouble.getNifOrganizacao()).thenReturn(257837248);
        when( propostaInfoDouble.getCodEdicao() ).thenReturn(1);
        when( propostaInfoDouble.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaInfoDouble.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaInfoDouble.getObjetivo() ).thenReturn("Objectivo da proposta");

        PropostaDTO propostaDouble = mock(PropostaDTO.class);

        when( propostaDouble.getCodUtilizador()).thenReturn(1);
        when( propostaDouble.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble.getCodEdicao() ).thenReturn(1);
        when( propostaDouble.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        when( propostaService.createAndSaveProposta(propostaInfoDouble)).thenReturn(propostaDouble);

        // Act
        ResponseEntity<Object> responseEntity = propostaController.createProposta(propostaInfoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        PropostaDTO propostaResult = (PropostaDTO) responseEntity.getBody();

        assertEquals(propostaResult.getCodUtilizador(), 1);
        assertEquals(propostaResult.getNifOrganizacao(), 257837248L);
        assertEquals(propostaResult.getCodEdicao(), 1);
        assertEquals(propostaResult.getTitulo(), "Titulo da Proposta");
        assertEquals(propostaResult.getProblema(), "Problema da Proposta");
        assertEquals(propostaResult.getObjetivo(), "Objectivo da proposta");
        assertEquals(propostaResult.getEstado(), String.valueOf(Proposta.Estado.PENDENTE));

    }

    @Test
    @DisplayName("Teste Get Proposta por CodUtilizador")
    void shouldGetAllPropostasByCodeUtilizadorWithCorrectAttributes() {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        PropostaDTO propostaDouble = mock(PropostaDTO.class);

        when( propostaDouble.getCodUtilizador()).thenReturn(1);
        when( propostaDouble.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble.getCodEdicao() ).thenReturn(1);
        when( propostaDouble.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        PropostaDTO propostaDouble2 = mock(PropostaDTO.class);

        when( propostaDouble2.getCodUtilizador()).thenReturn(1);
        when( propostaDouble2.getNifOrganizacao()).thenReturn(257837248L);
        when( propostaDouble2.getCodEdicao() ).thenReturn(1);
        when( propostaDouble2.getTitulo() ).thenReturn("Titulo da Proposta");
        when( propostaDouble2.getProblema() ).thenReturn("Problema da Proposta");
        when( propostaDouble2.getObjetivo() ).thenReturn("Objectivo da proposta");
        when( propostaDouble2.getEstado() ).thenReturn(String.valueOf(Proposta.Estado.PENDENTE));

        List<PropostaDTO> listDTO = new ArrayList<>();
        listDTO.add(propostaDouble);
        listDTO.add(propostaDouble2);

        when( propostaService.findAllByCodUtilizador(1)).thenReturn(listDTO);

        // Act
        ResponseEntity<Object> responseEntity = propostaController.getAllPropostasByCodUtilizador(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        //List<PropostaDTO> listResult = (List<PropostaDTO>) responseEntity.getBody();

        /*assertEquals(listResult.size(), listDTO.contains);
        assertTrue(listResult.contains(propostaDouble));*/

    }
}