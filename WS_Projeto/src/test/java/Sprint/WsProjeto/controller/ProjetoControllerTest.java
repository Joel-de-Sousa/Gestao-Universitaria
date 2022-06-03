package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.service.ProjetoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

}