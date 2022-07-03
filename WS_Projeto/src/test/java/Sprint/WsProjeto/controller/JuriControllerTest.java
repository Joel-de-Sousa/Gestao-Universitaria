package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.service.JuriService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JuriControllerTest {


    @MockBean
    JuriService juriService;

    @InjectMocks
    JuriController juriController;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAJuriByCode() throws SQLException {
        //arrange
        JuriDTO juriDTO = mock(JuriDTO.class);
        when(juriService.findJuriByCode(1)).thenReturn(juriDTO);

        //act
        ResponseEntity<Object> responseEntity = juriController.findJuriByCode(1);

        //assert
        assertEquals(responseEntity.getStatusCodeValue(),200);
        JuriDTO juriDTO1 = (JuriDTO) responseEntity.getBody();

        assertEquals(juriDTO1,juriDTO);
    }

    @Test
    void shouldCreateAndSaveJuri() throws Exception {

        //arrange
        NewJuriInfoDTO newJuriInfoDTO = mock(NewJuriInfoDTO.class);

        JuriDTO juriDTO = mock(JuriDTO.class);
        when(juriService.createAndSaveJuri(newJuriInfoDTO)).thenReturn(juriDTO);

        //act
        ResponseEntity<Object> responseEntity = juriController.createAndSaveJuri(newJuriInfoDTO);

        assertEquals(responseEntity.getStatusCodeValue(), HttpStatus.CREATED.value());
        JuriDTO juriDTO1 = (JuriDTO) responseEntity.getBody();

        assertEquals(juriDTO1,juriDTO);

    }

}