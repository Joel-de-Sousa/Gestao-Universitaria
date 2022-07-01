package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.ConviteDTO;
import Sprint.WsProjeto.DTO.ConvitePartialDTO;
import Sprint.WsProjeto.DTO.NewConviteInfoDTO;
import Sprint.WsProjeto.Exceptions.ProjetoNotExists;
import Sprint.WsProjeto.service.ConviteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteControllerTest {

    @InjectMocks
    ConviteController conviteController;

    @MockBean
    ConviteService conviteService;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindConviteByCode() throws Exception {
       //arrange
        ConviteDTO conviteDTO = mock(ConviteDTO.class);
        when(conviteService.findConviteByCode(1)).thenReturn(conviteDTO);

        //act
        ResponseEntity<Object> responseEntity = conviteController.findConviteByCode(1);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.OK.value());
        ConviteDTO conviteDTO1 = (ConviteDTO) responseEntity.getBody();

        assertEquals(conviteDTO1,conviteDTO);
    }

    @Test
    void shouldCreateAndSaveConvite() throws ProjetoNotExists, SQLException {
        //arrange
        NewConviteInfoDTO newDto = mock(NewConviteInfoDTO.class);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);

        when(conviteService.createAndSaveConvite(newDto)).thenReturn(conviteDTO);


        //act
        ResponseEntity<Object> responseEntity = conviteController.createAndSaveConvite(newDto);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
        ConviteDTO conviteDTO1 = (ConviteDTO) responseEntity.getBody();

        assertEquals(conviteDTO1,conviteDTO);

    }
    @Test
    void shouldUpdateEstadoConviteOrientador() throws Exception {

        //arrange
        ConvitePartialDTO update = mock(ConvitePartialDTO.class);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);
        when(conviteService.updateEstadoConvite(update)).thenReturn(conviteDTO);

        //act
        ResponseEntity<Object> act = conviteController.partialUpdateEstadoConviteOrientador(update,1);

        //assert
        assertEquals(act.getStatusCodeValue(),HttpStatus.OK.value());
        ConviteDTO conviteDTO1 = (ConviteDTO) act.getBody();

        assertEquals(conviteDTO1,conviteDTO);



    }





}