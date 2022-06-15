package wsproposta.proposta.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.services.CandidaturaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CandidaturaControllerTest {
/*
    @MockBean
    CandidaturaService candidaturaService;

    @MockBean
    CandidaturaController candidaturaController;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Teste Get All Candidaturas")
    void shouldGetAllCandidaturas(){

        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CandidaturaDTO candidaturaDTO = mock(CandidaturaDTO.class);

        CandidaturaDTO candidaturaDTO2 = mock(CandidaturaDTO.class);

        List<CandidaturaDTO> dtoList = new ArrayList<>();
        dtoList.add(candidaturaDTO);
        dtoList.add(candidaturaDTO2);

        when(candidaturaService.findAllCandidaturas()).thenReturn(dtoList);


        //Act
        ResponseEntity<Object> response = candidaturaController.getAllCandidaturas();

        //Assert
        assertEquals(response.getStatusCodeValue(),HttpStatus.OK.value());

        List<CandidaturaDTO> listFinal = (List<CandidaturaDTO>) response.getBody();

        assertEquals(listFinal,dtoList);

    }

    @Test
    @DisplayName("Teste Get Candidaturas By Code")
    void ShouldGetCandidaturasByCode(){
        //Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        CandidaturaDTO candidaturaDTO = mock(CandidaturaDTO.class);

        Optional<CandidaturaDTO> optionalCandidaturaDTO = Optional.of(candidaturaDTO);

        when(candidaturaDTO.getCodCandidatura()).thenReturn(1);

        when(candidaturaService.getCandidaturaByCodCandidatura(1)).thenReturn(optionalCandidaturaDTO);

        //Act
        ResponseEntity<Object> responseEntity = candidaturaController.getCandidaturaByCode(1);

        //Assert

        assertEquals(responseEntity.getStatusCodeValue(),200);

        Optional<CandidaturaDTO> candidaturaFinal  = (Optional<CandidaturaDTO>) responseEntity.getBody();

        assertEquals(candidaturaFinal,optionalCandidaturaDTO);

        assertEquals(candidaturaFinal.get().getCodCandidatura(),1);
    }*/






/*    @GetMapping("/{codCandidatura}")
    @ResponseBody
    public ResponseEntity<Object> getCandidaturaByCode(@PathVariable int codCandidatura) {

        Optional<CandidaturaDTO> opCandidaturaDTO = candidaturaService.getCandidaturaByCodCandidatura(codCandidatura);

        if (opCandidaturaDTO.isPresent()) {
            return new ResponseEntity<>(opCandidaturaDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O codigo da candidatura nao consta na Base de Dados", HttpStatus.NOT_FOUND);
    }*/

  /*  @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllCandidaturas() {
        List<CandidaturaDTO> listCandidaturasDTO = candidaturaService.findAllCandidaturas();
        return new ResponseEntity<>(listCandidaturasDTO, HttpStatus.OK);
    }*/
}