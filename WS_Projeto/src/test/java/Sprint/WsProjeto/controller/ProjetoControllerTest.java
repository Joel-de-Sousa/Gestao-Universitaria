package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.AvaliacaoPartialDTO;
import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
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

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoControllerTest {


    @MockBean
    ProjetoService projetoService;

    @InjectMocks
    ProjetoController projetoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetProjetoByCodeWithCorrectAttributes() throws SQLException {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ProjetoDTO projetoD = mock(ProjetoDTO.class);

        when(projetoD.getCodProjeto()).thenReturn(1);
        when(projetoD.getCodEstudante()).thenReturn(2);
        when(projetoD.getCodOrientador()).thenReturn(3);
        when(projetoD.getCodProposta()).thenReturn(4);

        when(projetoService.findProjetoByCode(1)).thenReturn(projetoD);

        // Act
        ResponseEntity<Object> responseEntity = projetoController.findProjetoByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        ProjetoDTO projetoResult = (ProjetoDTO) responseEntity.getBody();

        assertEquals(projetoResult.getCodProjeto(), 1);
        assertEquals(projetoResult.getCodEstudante(), 2);
        assertEquals(projetoResult.getCodOrientador(), 3);
        assertEquals(projetoResult.getCodProposta(), 4);


    }

    @Test
    void shouldGiveNotFoundCodeIfCodeNotFound () throws SQLException {

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);

        when(projetoService.findProjetoByCode(2)).thenReturn(projetoDTO);
        //act
        ResponseEntity<Object> objectResponseEntity = projetoController.findProjetoByCode(1);

        assertEquals(objectResponseEntity.getStatusCodeValue(),404);

        ProjetoDTO result = (ProjetoDTO) objectResponseEntity.getBody();

        assertNotEquals(result,projetoDTO);
    }
    @Test
    void shouldFindProjetoByCodeEstudante() throws SQLException {
        //arrange
        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);
        when(projetoService.findProjetoByCodeEstudante(1)).thenReturn(projetoDTO);

        //act
        ResponseEntity<Object> act = projetoController.findProjetoByCodeEstudante(1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        ProjetoDTO result = (ProjetoDTO) act.getBody();
        assertEquals(result,projetoDTO);

    }
    @Test
    void shouldFindProjetoByCodRuc() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosPorCodigoRUC(1)).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosByCodRUC(1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }
    @Test
    void shouldFindProjetoByCodDocente() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosByCodDocente(1,1)).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosByCodDocente(1,1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }

    @Test
    void shouldFindProjetoComDeterminadoMAcompleto() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosComDeterminadoMACompleto(1,1)).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosComDeterminadoMACompleto(1,1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }
    @Test
    void shouldCreateAndSave() throws Exception {
        //arrange
        NewProjetoInfoDto info = mock(NewProjetoInfoDto.class);
        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);
        when(projetoService.createAndSaveProjeto(info)).thenReturn(projetoDTO);

        //act
        ResponseEntity<Object> act = projetoController.createAndSaveProjeto(info);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.CREATED.value());
        ProjetoDTO result = (ProjetoDTO) act.getBody();
        assertEquals(result,projetoDTO);

    }
    @Test
    void shouldFindProjetoConcluidos() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosConcluidos(1)).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosConcluidos(1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }

    @Test
    void shouldFindProjetoEntreDatas() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosDatasAvaliacao(1,2, new Date(11-10-2022),new Date(15-11-2022))).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosBetweenDatasAvaliacao(1,2, new Date(11-10-2022),new Date(15-11-2022));

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }
    @Test
    void shouldFindProjetoByNifOrganizacao() throws Exception {
        //arrange

        List<ProjetoDTO> projetoDTOList = new ArrayList<>();
        when(projetoService.findProjetosByNifOrganizacao(1,245490493)).thenReturn(projetoDTOList);

        //act
        ResponseEntity<Object> act = projetoController.findProjetosByNifOrganizacao(1,245490493);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<ProjetoDTO> result = (List<ProjetoDTO>) act.getBody();
        assertEquals(result,projetoDTOList);

    }




}