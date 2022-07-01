package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.AvaliacaoPartialDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AvaliacaoControllerTest {

    @InjectMocks
    AvaliacaoController avaliacaoController;

    @MockBean
    AvaliacaoService avaliacaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAvaliacaoByCode() throws Exception {
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoService.findAvaliacaoByCode(1)).thenReturn(avaliacaoDTO);

        //act
        ResponseEntity<Object> act = avaliacaoController.findAvalicaoByCode(1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        AvaliacaoDTO result = (AvaliacaoDTO) act.getBody();
        assertEquals(result,avaliacaoDTO);
    }

    @Test
    void shouldFindAvaliacaoesByCodProjeto() throws Exception {
        //arrange
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();

        when(avaliacaoService.findAvaliacoesByCodProjeto(1)).thenReturn(avaliacaoDTOList);
        avaliacaoDTOList.add(avaliacaoDTO);

        //act
        ResponseEntity<Object> act = avaliacaoController.findAvaliacoesByCodProjeto(1);
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        List<AvaliacaoDTO> result = (List<AvaliacaoDTO>) act.getBody();
        assertEquals(result,avaliacaoDTOList);
    }

    @Test
    void shouldUpdateAvaliacao() throws Exception {
        AvaliacaoPartialDTO info = mock(AvaliacaoPartialDTO.class);
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoService.updateAvaliacao(info)).thenReturn(avaliacaoDTO);

        //act
        ResponseEntity<Object> act = avaliacaoController.updateAvaliacao(info,1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        AvaliacaoDTO result = (AvaliacaoDTO) act.getBody();
        assertEquals(result,avaliacaoDTO);
    }

    @Test
    void shouldUpdateEstadoAvaliacao() throws Exception {
        AvaliacaoPartialDTO update = mock(AvaliacaoPartialDTO.class);
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoService.updateEstadoAvaliacao(update)).thenReturn(avaliacaoDTO);

        //act
        ResponseEntity<Object> act = avaliacaoController.updateEstadoAvaliacao(update,1);

        //assert
        assertEquals(act.getStatusCodeValue(), HttpStatus.OK.value());
        AvaliacaoDTO result = (AvaliacaoDTO) act.getBody();
        assertEquals(result,avaliacaoDTO);
    }

}