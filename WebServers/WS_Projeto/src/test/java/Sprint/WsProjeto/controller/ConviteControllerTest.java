package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.ConviteDTO;
import Sprint.WsProjeto.DTO.NewAvaliacaoInfoDTO;
import Sprint.WsProjeto.DTO.NewConviteInfoDTO;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.service.ConviteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
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
    void shouldFindConviteByCode(){
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
    void shouldCreateAndSaveConvite(){
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




}