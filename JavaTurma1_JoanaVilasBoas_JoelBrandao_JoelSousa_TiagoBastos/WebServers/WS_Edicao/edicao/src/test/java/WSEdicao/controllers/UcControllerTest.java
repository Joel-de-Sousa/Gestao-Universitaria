
package WSEdicao.controllers;

import WSEdicao.domain.factories.UcFactory;
import WSEdicao.dto.UcDTO;
import WSEdicao.services.UcService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
    UcDTO ucDTO;

    @InjectMocks
    UcController ucController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSpecificUcSearchingById() {
        // Arrange
        UcDTO ucDouble = mock(UcDTO.class);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Optional<UcDTO> opUc = Optional.of(ucDouble);
        when(ucService.getUcByCode(1)).thenReturn(opUc);

        // Act
        ResponseEntity<Object> responseEntity = ucController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        UcDTO ucRes = (UcDTO) responseEntity.getBody();
        assertEquals(ucRes.getSigla(), "POOJ");
        assertEquals(ucRes.getDenominacao(), "ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldFindEveryUcCreatedCorrectly() {
        // Arrange
        UcDTO ucDouble = mock(UcDTO.class);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        UcDTO ucDouble2 = mock(UcDTO.class);
        when(ucDouble2.getSigla()).thenReturn("LP");
        when(ucDouble2.getDenominacao()).thenReturn("LaboratorioProgramacao");

        List<UcDTO> listaUc = new ArrayList<>();
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
    void shouldCreateUcWithCorrectAttributes() throws Exception {
        // Arrange
        UcDTO ucDouble = mock(UcDTO.class);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        UcDTO ucDtoDouble = mock(UcDTO.class);
        when(ucDtoDouble.getSigla()).thenReturn("POOJ");
        when(ucDtoDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        when(ucService.createAndSaveUc("POOJ","ProgramacaoOrientadaAObjetos")).thenReturn(ucDouble);

        // Act
        ResponseEntity<Object> responseEntity = ucController.createUc(ucDtoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, ucDouble);
    }
}
