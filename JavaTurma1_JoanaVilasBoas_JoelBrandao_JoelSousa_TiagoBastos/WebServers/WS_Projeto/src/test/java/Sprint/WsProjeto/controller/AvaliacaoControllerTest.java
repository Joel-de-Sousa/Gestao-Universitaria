package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.NewAvaliacaoInfoDTO;
import Sprint.WsProjeto.service.AvaliacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AvaliacaoControllerTest {

    @MockBean
    AvaliacaoService avaliacaoService;

    @InjectMocks
    AvaliacaoController avaliacaoController;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAvalicaoByCode(){
        //arrange
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoService.findAvaliacaoByCode(1)).thenReturn(avaliacaoDTO);

        //act
        ResponseEntity<Object> responseEntity = avaliacaoController.findAvalicaoByCode(1);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(),200);
        AvaliacaoDTO avaliacaoDTO1 = (AvaliacaoDTO) responseEntity.getBody();

        assertEquals(avaliacaoDTO1,avaliacaoDTO);
    }

    @Test
    void shouldCreateAndSaveAvaliacao() throws IOException {

        //arrange
        NewAvaliacaoInfoDTO newAvaliacaoInfoDTO = mock(NewAvaliacaoInfoDTO.class);

        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoService.createAndSaveAvaliacao(newAvaliacaoInfoDTO)).thenReturn(avaliacaoDTO);

        //act
        ResponseEntity<Object> responseEntity = avaliacaoController.createAndSaveAvaliacao(newAvaliacaoInfoDTO);

        assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
        AvaliacaoDTO avaliacaoDTO1 = (AvaliacaoDTO) responseEntity.getBody();

        assertEquals(avaliacaoDTO1,avaliacaoDTO);

    }




}