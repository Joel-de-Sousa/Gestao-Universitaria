package WSEdicao.controllers;

import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.services.MomentoAvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class MomentoAvaliacaoControllerTest {

    @MockBean
    MomentoAvaliacaoService momentoAvaliacaoService;

    @InjectMocks
    MomentoAvaliacaoController momentoAvaliacaoController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSpecificMomentoAvaliacaoSearchingById() {
        // Arrange
        MomentoAvaliacaoDTO momentoAvaliacaoDouble = mock(MomentoAvaliacaoDTO.class);

        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = Optional.of(momentoAvaliacaoDouble);
        when(momentoAvaliacaoService.getMomentoAvaliacaoByCode(1)).thenReturn(opMomentoAvaliacao);
        // Act
        ResponseEntity<Object> responseEntity = momentoAvaliacaoController.getByCode(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        MomentoAvaliacaoDTO momentoAvaliacaoRes = (MomentoAvaliacaoDTO) responseEntity.getBody();
        assertEquals(momentoAvaliacaoRes.getCodMomentoAvaliacao(), 1);
    }

    @Test
    void shouldFindEveryMomentoAvaliacaoCreatedCorrectly() {
        // Arrange
        MomentoAvaliacaoDTO momentoAvaliacaoDouble = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDouble.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacaoDTO momentoAvaliacaoDouble2 = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDouble2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacaoDTO> listaMomentoAvaliacao = new ArrayList<>();
        listaMomentoAvaliacao.add(momentoAvaliacaoDouble);
        listaMomentoAvaliacao.add(momentoAvaliacaoDouble2);

        when(momentoAvaliacaoService.getAllMomentoAvaliacao()).thenReturn(listaMomentoAvaliacao);

        // Act
        ResponseEntity<Object> responseEntity = momentoAvaliacaoController.findAll();

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res, listaMomentoAvaliacao);
    }

    @Test
    void shouldCreateAndSaveMomentoAvaliacaoWithCorrectAttributes() throws Exception {
        // Arrange
        MomentoAvaliacaoDTO momentoAvaliacaoDouble = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDouble.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacaoDTO momentoAvaliacaoDtoDouble = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDtoDouble.getDenominacao()).thenReturn("Sprint1");

        when(momentoAvaliacaoService.createAndSaveMomentoAvaliacao(momentoAvaliacaoDtoDouble)).thenReturn(momentoAvaliacaoDouble);

        // Act
        ResponseEntity<Object> responseEntity = momentoAvaliacaoController.createMomentoAvaliacao(momentoAvaliacaoDtoDouble);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

        Object res = responseEntity.getBody();
        assertEquals(res, momentoAvaliacaoDouble);
    }

    @Test
    void shouldFindEveryMomentoAvaliacaoByCodEdicao() {
        //Arrange
        MomentoAvaliacaoDTO momentoAvaliacaoDouble = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDouble.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacaoDTO momentoAvaliacaoDouble2 = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDouble2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacaoDTO> listaMomentoAvaliacao = new ArrayList<>();
        listaMomentoAvaliacao.add(momentoAvaliacaoDouble);
        listaMomentoAvaliacao.add(momentoAvaliacaoDouble2);

        when(momentoAvaliacaoService.getAllMomentoAvaliacaoByCodEdicao(1)).thenReturn(listaMomentoAvaliacao);

        // Act
        ResponseEntity<Object> responseEntity = momentoAvaliacaoController.getAllMomentoAvaliacaoByCodEdicao(1);

        // Assert
        assertEquals(responseEntity.getStatusCodeValue(), 200);

        Object res = responseEntity.getBody();
        assertEquals(res, listaMomentoAvaliacao);
    }
}