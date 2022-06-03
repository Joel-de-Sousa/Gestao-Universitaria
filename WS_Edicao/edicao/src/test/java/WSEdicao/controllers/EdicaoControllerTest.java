package WSEdicao.controllers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.UcDTO;
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

        UcDTO ucDouble = mock(UcDTO.class);
        when(ucDouble.getCodUc()).thenReturn(1);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        AnoLetivoDTO anoLetivoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDouble.getAno()).thenReturn("2015-2016");

        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        when( edicaoDouble.getCodEdicao()).thenReturn(1);
        when( edicaoDouble.getCodUc()).thenReturn(1);
        when( edicaoDouble.getCodAnoLetivo()).thenReturn(1);

        Optional<EdicaoDTO> opEdicao = Optional.of(edicaoDouble);
        when( edicaoService.getEdicaoByCode(1)).thenReturn(opEdicao);

        // Act
        ResponseEntity<Object> responseEntity = edicaoController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        EdicaoDTO edicaoRes = (EdicaoDTO) responseEntity.getBody();
        assertEquals(edicaoRes.getCodEdicao(), 1);
        assertEquals(edicaoRes.getUc().getSigla(),"POOJ");
        assertEquals(edicaoRes.getAnoLetivo().getAno(), "2015-2016");
    }

    @Test
    void shouldFindEveryEdicaoDTOCreatedCorrectly() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        EdicaoDTO edicaoDouble = mock(EdicaoDTO.class);
        when( edicaoDouble.getCodEdicao() ).thenReturn(1);
        when( edicaoDouble.getthenReturn("2015-2016");

        EdicaoDTO edicaoDouble2 = mock(EdicaoDTO.class);
        when (edicaoDouble2.getCodEdicao()).thenReturn(2);
        when(edicaoDouble2.getAno()).thenReturn("2016-2017");

        List<EdicaoDTO> listaEdicao = new ArrayList<>();
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