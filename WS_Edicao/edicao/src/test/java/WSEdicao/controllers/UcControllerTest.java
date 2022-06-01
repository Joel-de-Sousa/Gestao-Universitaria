package WSEdicao.controllers;

import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.UcFactory;
import WSEdicao.dto.UcDTO;
import WSEdicao.services.UcService;
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
class UcControllerTest {

    @MockBean
    UcService ucService;

    @MockBean
    UcFactory ucFactory;

    @MockBean
    Uc uc;

    @InjectMocks
    UcController ucController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSpecificUcSearchingById() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Uc ucDouble = mock(Uc.class);
        when(ucDouble.getSSigla()).thenReturn("POOJ");
        when(ucDouble.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Optional<Uc> opUc = Optional.of(ucDouble);
        when(ucService.getUcByCode(1)).thenReturn(opUc);

        // Act
        ResponseEntity<Object> responseEntity = ucController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Uc ucRes = (Uc) responseEntity.getBody();
        assertEquals(ucRes.getSSigla(), "POOJ");
        assertEquals(ucRes.getSDenominacao(), "ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldFindEveryUcCreatedCorrectly() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Uc ucDouble = mock(Uc.class);
        when(ucDouble.getSSigla()).thenReturn("POOJ");
        when(ucDouble.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Uc ucDouble2 = mock(Uc.class);
        when(ucDouble2.getSSigla()).thenReturn("LP");
        when(ucDouble2.getSDenominacao()).thenReturn("LaboratorioProgramacao");

        List<Uc> listaUc = new ArrayList<>();
        listaUc.add(ucDouble);
        listaUc.add(ucDouble2);

        when(ucService.getAllUc()).thenReturn(listaUc);

        // Act
        ResponseEntity<Object> responseEntity = ucController.findAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res, listaUc);
    }

    @Test
    void shouldCreateUcWithCorrectAttributes() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Uc ucDouble = mock(Uc.class);
        when(ucDouble.getSSigla()).thenReturn("POOJ");
        when(ucDouble.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        UcDTO ucDtoDouble = mock(UcDTO.class);
        when(ucDtoDouble.getSSigla()).thenReturn("POOJ");
        when(ucDtoDouble.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        when(ucService.createAndSaveUc("POOJ","ProgramacaoOrientadaAObjetos")).thenReturn(ucDouble);

        // Act
        ResponseEntity<Object> responseEntity = ucController.createUc(ucDtoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, ucDouble);

    }
}