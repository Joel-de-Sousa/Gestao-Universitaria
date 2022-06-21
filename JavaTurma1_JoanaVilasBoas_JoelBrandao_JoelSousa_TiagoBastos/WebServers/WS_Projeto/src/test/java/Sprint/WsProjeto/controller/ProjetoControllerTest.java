package Sprint.WsProjeto.controller;

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
    void shouldGetProjetoByCodeWithCorrectAttributes() {

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
    void shouldGiveNotFounCodeIfCodeNotFound (){

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
    void shouldCreateAndSaveProjeto(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));


        NewProjetoInfoDto newProjetoInfoDto = mock(NewProjetoInfoDto.class);

        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);
        when(projetoService.createAndSaveProjeto(newProjetoInfoDto)).thenReturn(projetoDTO);


        //act
        ResponseEntity<Object> responseEntity = projetoController.createAndSaveProjeto( newProjetoInfoDto);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.CREATED.value());

        ProjetoDTO result = (ProjetoDTO) responseEntity.getBody();

        assertEquals(result,projetoDTO);


    }

}