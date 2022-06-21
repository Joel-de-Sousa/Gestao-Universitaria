package WSEdicao.controllers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.dto.*;
import WSEdicao.services.EdicaoService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoControllerTest {

    @MockBean
    EdicaoService edicaoService;

    @MockBean
    EdicaoFactory edicaoFactory;

    @MockBean
    Edicao edicao;

    @InjectMocks
    EdicaoController edicaoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSpecificEdicaoSearchingById() {
        // Arrange
        UcDTO ucDouble = mock(UcDTO.class);
        when(ucDouble.getCodUc()).thenReturn(1);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        AnoLetivoDTO anoLetivoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDouble.getAno()).thenReturn("2015");

        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDouble.getCodUc()).thenReturn(1);
        when(edicaoDouble.getCodAnoLetivo()).thenReturn(1);

        Optional<EdicaoDTO> opEdicao = Optional.of(edicaoDouble);
        when(edicaoService.getEdicaoByCode(1)).thenReturn(opEdicao);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        EdicaoDTO edicaoRes = (EdicaoDTO) responseEntity.getBody();
        assertEquals(edicaoRes.getCodEdicao(), 1);
        assertEquals(edicaoRes.getCodUc(), 1);
        assertEquals(edicaoRes.getCodEdicao(), 1);
    }

    @Test
    void shouldFindEveryEdicaoDTOCreatedCorrectly() {
        // Arrange
        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDouble.getCodUc()).thenReturn(1);
        when(edicaoDouble.getCodAnoLetivo()).thenReturn(1);

        EdicaoDTO edicaoDouble2 = mock(EdicaoDTO.class);
        when(edicaoDouble2.getCodEdicao()).thenReturn(2);
        when(edicaoDouble2.getCodUc()).thenReturn(2);
        when(edicaoDouble2.getCodAnoLetivo()).thenReturn(2);

        List<EdicaoDTO> listaEdicao = new ArrayList<>();
        listaEdicao.add(edicaoDouble);
        listaEdicao.add(edicaoDouble2);

        when(edicaoService.getAllEdicao()).thenReturn(listaEdicao);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.findAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res, listaEdicao);
    }

    @Test
    void shouldCreateEdicaoWithCorrectAttributes() throws Exception {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        EdicaoDTO edicaoDTODouble = mock(EdicaoDTO.class);
        when(edicaoDTODouble.getCodEdicao()).thenReturn(1);
        when(edicaoDTODouble.getCodUc()).thenReturn(1);
        when(edicaoDTODouble.getCodAnoLetivo()).thenReturn(1);
        when(edicaoDTODouble.getCodUc()).thenReturn(1);

        NewEdicaoInfoDTO infoDTODouble = mock(NewEdicaoInfoDTO.class);
        when(infoDTODouble.getCodEdicao()).thenReturn(1);
        when(infoDTODouble.getCodUc()).thenReturn(1);
        when(infoDTODouble.getCodAnoLetivo()).thenReturn(1);
        when(edicaoDTODouble.getCodRUC()).thenReturn(1);


        when(edicaoService.createAndSaveEdicao(infoDTODouble.getCodUc(),
                infoDTODouble.getCodAnoLetivo(),
                infoDTODouble.getCodRUC())).thenReturn(edicaoDTODouble);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.createEdicao(infoDTODouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, edicaoDTODouble);
    }

    @Test
    void shouldUpdateEstadoOfEdicaoWithCorrectAttributes() throws Exception {

        // Arrange
        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        EdicaoDTOParcial edicaoDTOparcial = mock(EdicaoDTOParcial.class);

        when(edicaoService.updateEstadoEdicao(edicaoDTOparcial)).thenReturn(edicaoDouble);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.partialUpdateEstadoProposta(edicaoDTOparcial);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        EdicaoDTO edicaoDTO = (EdicaoDTO) responseEntity.getBody();

        assertEquals(edicaoDTO, edicaoDouble);
    }

    @Test
    void shouldAddEstudanteToEdicaoWithCorrectAttributes() throws Exception {
        //Arrange
        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        AddStudentDTO addStudentDTO = mock(AddStudentDTO.class);
        when(edicaoService.addEstudantes(addStudentDTO)).thenReturn(edicaoDouble);

        //Act
        ResponseEntity<Object> responseEntity = edicaoController.addEstudante(addStudentDTO);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        EdicaoDTO edicaoDTO = (EdicaoDTO) responseEntity.getBody();
        assertEquals(edicaoDTO, edicaoDouble);

    }


    @Test
    void shouldReturnListEdicoesByCodRUC() {
        //Arrange
        EdicaoDTO edicaoDouble1 = mock(EdicaoDTO.class);
        when(edicaoDouble1.getCodRUC()).thenReturn(1);

        EdicaoDTO edicaoDouble2 = mock(EdicaoDTO.class);
        when(edicaoDouble2.getCodRUC()).thenReturn(1);


        List<EdicaoDTO> listaEdicao = new ArrayList<>();
        listaEdicao.add(edicaoDouble1);
        listaEdicao.add(edicaoDouble2);

        when(edicaoService.getAllEdicaoByCodRUC(1)).thenReturn(listaEdicao);

        //Act
        ResponseEntity<Object> responseEntity = edicaoController.getAllEdicaoByCodRUC(1);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        List<EdicaoDTO> res = (List<EdicaoDTO>) responseEntity.getBody();
        assertEquals(res, listaEdicao);
    }

    @Test
    void shouldReturnListEstudantesByCodEdicao() throws Exception {
        //Arrange
        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        AddStudentDTO addStudentDTO = mock(AddStudentDTO.class);
        when(addStudentDTO.getCodEdicao()).thenReturn(1);
        when(addStudentDTO.getCodEstudante()).thenReturn(1);

        AddStudentDTO addStudentDTO2 = mock(AddStudentDTO.class);
        when(addStudentDTO2.getCodEdicao()).thenReturn(1);
        when(addStudentDTO2.getCodEstudante()).thenReturn(2);

        List<AddStudentDTO> listaAddStudent = new ArrayList<>();
        listaAddStudent.add(addStudentDTO);
        listaAddStudent.add(addStudentDTO2);

        when(edicaoService.getAllEstudantesByCodEdicao(1)).thenReturn(listaAddStudent);

        //Act
        ResponseEntity<Object> responseEntity = edicaoController.getAllEstudantesByCodEdicao(1);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        List<AddStudentDTO> res = (List<AddStudentDTO>) responseEntity.getBody();
        //Object res = responseEntity.getBody();
        assertEquals(res, listaAddStudent);
    }

    @Test
    void shouldReturnListOfEdicoesWithAllTheirAttributes(){
        //Arrange
        EdicaoAllArgsDTO edicaoDouble = mock(EdicaoAllArgsDTO.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDouble.getCodUc()).thenReturn(1);
        when(edicaoDouble.getCodAnoLetivo()).thenReturn(1);

        EdicaoAllArgsDTO edicaoDouble2 = mock(EdicaoAllArgsDTO.class);
        when(edicaoDouble2.getCodEdicao()).thenReturn(2);
        when(edicaoDouble2.getCodUc()).thenReturn(2);
        when(edicaoDouble2.getCodAnoLetivo()).thenReturn(2);

        List<EdicaoAllArgsDTO> listaEdicao = new ArrayList<>();
        listaEdicao.add(edicaoDouble);
        listaEdicao.add(edicaoDouble2);

        when(edicaoService.getEdicaoAllArgs()).thenReturn(listaEdicao);

        //Act
        ResponseEntity<Object> responseEntity = edicaoController.findAllArgs();

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        Object res = responseEntity.getBody();
        assertEquals(res, listaEdicao);
    }

    @Test
    void shouldReturnEdicaoByCodEdicaoWithAllAttributes(){
        //Arrange
        EdicaoAllArgsDTO edicaoDouble = mock(EdicaoAllArgsDTO.class);
        Optional<EdicaoAllArgsDTO> opEdicaoDouble = Optional.of(edicaoDouble);

        when(edicaoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDouble.getCodUc()).thenReturn(1);
        when(edicaoDouble.getCodAnoLetivo()).thenReturn(1);

        when(edicaoService.getEdicaoAllArgsByCode(1)).thenReturn(opEdicaoDouble);

        //Act
        ResponseEntity<Object> responseEntity = edicaoController.getAllArgsByCode(1);

        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());

        Object res = responseEntity.getBody();
        assertEquals(res, edicaoDouble);
    }
}