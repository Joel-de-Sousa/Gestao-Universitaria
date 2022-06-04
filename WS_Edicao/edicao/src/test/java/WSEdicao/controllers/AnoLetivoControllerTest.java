
package WSEdicao.controllers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.factories.AnoLetivoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.services.AnoLetivoService;
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
class AnoLetivoControllerTest {

    @MockBean
    AnoLetivoService anoLetivoService;

    @InjectMocks
    AnoLetivoController anoLetivoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSpecificAnoLetivoSearchingById() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        AnoLetivoDTO anoLetivoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDouble.getAno()).thenReturn("2015-2016");

        Optional<AnoLetivoDTO> opAnoLetivo = Optional.of(anoLetivoDouble);
        when(anoLetivoService.getAnoLetivoByCode(1)).thenReturn(opAnoLetivo);

        // Act
        ResponseEntity<Object> responseEntity = anoLetivoController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        AnoLetivoDTO anoLetivoRes = (AnoLetivoDTO) responseEntity.getBody();
        assertEquals(anoLetivoRes.getCodAnoLetivo(), 1);
        assertEquals(anoLetivoRes.getAno(), "2015-2016");
    }

    @Test
    void shouldFindEveryAnoLetivoDTOCreatedCorrectly() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        AnoLetivoDTO anoLetivoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDouble.getAno()).thenReturn("2015-2016");

        AnoLetivoDTO anoLetivoDouble2 = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble2.getCodAnoLetivo()).thenReturn(2);
        when(anoLetivoDouble2.getAno()).thenReturn("2016-2017");

        List<AnoLetivoDTO> listaAnoLetivo = new ArrayList<>();
        listaAnoLetivo.add(anoLetivoDouble);
        listaAnoLetivo.add(anoLetivoDouble2);

        when(anoLetivoService.getAllAnoLetivo()).thenReturn(listaAnoLetivo);

        // Act
        ResponseEntity<Object> responseEntity = anoLetivoController.findAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res, listaAnoLetivo);
    }

    @Test
    void shouldCreateAnoLetivoWithCorrectAttributes() {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        AnoLetivoDTO anoLetivoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDouble.getAno()).thenReturn("2015-2016");

        AnoLetivoDTO anoLetivoDtoDouble = mock(AnoLetivoDTO.class);
        when(anoLetivoDtoDouble.getCodAnoLetivo()).thenReturn(1);
        when(anoLetivoDtoDouble.getAno()).thenReturn("2015-2016");

        when(anoLetivoService.createAndSaveAnoLetivo("2015-2016")).thenReturn(anoLetivoDouble);

        // Act
        ResponseEntity<Object> responseEntity = anoLetivoController.createAnoLetivo(anoLetivoDtoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, anoLetivoDouble);
    }
}

