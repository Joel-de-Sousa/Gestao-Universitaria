package WSEdicao.controllers;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.services.EdicaoService;
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
    /*@Test
    void shouldFindSpecificEdicaoSearchingById() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Edicao edicaoDouble = mock(Edicao.class);
        when( edicaoDouble.getCodUc()).thenReturn(1);
        when( edicaoDouble.getAno() ).thenReturn("2015-2016");

        Optional<Edicao> opEdicao = Optional.of(edicaoDouble);
        when( edicaoService.getEdicaoByCode(1)).thenReturn(opEdicao);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Edicao edicaoRes = (Edicao) responseEntity.getBody();
        assertEquals(edicaoRes.getCodEdicao(), 1);
        assertEquals(edicaoRes.getAno(), "2015-2016");
    }*/

    /*@Test
    void shouldFindEveryEdicaoCreatedCorrectly() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Edicao edicaoDouble = mock(Edicao.class);
        when( edicaoDouble.getCodEdicao() ).thenReturn(1);
        when( edicaoDouble.getAno() ).thenReturn("2015-2016");

        Edicao edicaoDouble2 = mock(Edicao.class);
        when (edicaoDouble2.getCodEdicao()).thenReturn(2);
        when(edicaoDouble2.getAno()).thenReturn("2016-2017");

        List<Edicao> listaEdicao = new ArrayList<>();
        listaEdicao.add(edicaoDouble);
        listaEdicao.add(edicaoDouble2);

        when(edicaoService.getAllEdicao()).thenReturn(listaEdicao);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.findAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res,listaEdicao);
    }

    @Test
    void shouldCreateEdicaoWithCorrectAttributes() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Edicao edicaoDouble = mock(Edicao.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDouble.getAno()).thenReturn("2015-2016");

        EdicaoDTO edicaoDtoDouble = mock(EdicaoDTO.class);
        when(edicaoDtoDouble.getCodEdicao()).thenReturn(1);
        when(edicaoDtoDouble.getAno()).thenReturn("2015-2016");

        when(edicaoService.createAndSaveEdicao("2015-2016")).thenReturn(edicaoDouble);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.createEdicao(edicaoDtoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res,edicaoDouble);
    }*/

}