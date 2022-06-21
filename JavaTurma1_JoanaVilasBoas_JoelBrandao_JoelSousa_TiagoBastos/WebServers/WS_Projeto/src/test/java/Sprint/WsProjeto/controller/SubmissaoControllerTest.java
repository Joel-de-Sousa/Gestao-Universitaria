package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.NewSubmissaoInfoDTO;
import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.service.SubmissaoService;
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

class SubmissaoControllerTest {

    @InjectMocks
    SubmissaoController submissaoController;

    @MockBean
    SubmissaoService submissaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSubmissaoByCode() throws IOException {

        //arrange
        SubmissaoDTO submissaoDTO = mock(SubmissaoDTO.class);
        when(submissaoService.findSubmissaoBycode(1)).thenReturn(submissaoDTO);


        //act
        ResponseEntity<Object> responseEntity = submissaoController.findSubmissaoByCode(1);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        SubmissaoDTO submissaoDTO1 = (SubmissaoDTO) responseEntity.getBody();
        assertEquals(submissaoDTO1,submissaoDTO);

    }
    @Test
    void shouldCreateAndSaveSubmissao() throws Exception {
        //arrange
        NewSubmissaoInfoDTO newSubmissaoInfoDTO = mock(NewSubmissaoInfoDTO.class);

        SubmissaoDTO submissaoDTO = mock(SubmissaoDTO.class);
        when(submissaoService.createAndSaveSubmissao(newSubmissaoInfoDTO)).thenReturn(submissaoDTO);

       //act
       ResponseEntity<Object> responseEntity = submissaoController.createAndSaveSubmissao(newSubmissaoInfoDTO) ;

       //assert
        assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
        SubmissaoDTO submissaoDTO1 = (SubmissaoDTO) responseEntity.getBody();
        assertEquals(submissaoDTO1,submissaoDTO);

    }

}