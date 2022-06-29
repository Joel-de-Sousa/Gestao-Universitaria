package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.repositories.REST.UtilizadorRestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UtilizadorWebRepositoryTest {



    @Mock
    UtilizadorRestRepository utilizadorRestRepository;

    @InjectMocks
    UtilizadorWebRepository utilizadorWebRepository;

    @Test
    void shouldFindUtilizadorByCode (){

        //arrange
        UtilizadorRestDTO utilizadorRestDTO = mock(UtilizadorRestDTO.class);
        Optional<UtilizadorRestDTO> utilizadorRestDTO1 = Optional.of(utilizadorRestDTO);
        when(utilizadorRestRepository.findUtilizadorByCode(1)).thenReturn(utilizadorRestDTO1);

        //act
        Optional<UtilizadorRestDTO> utilizadorRes = utilizadorWebRepository.findUtilizadorByCode(1);

        assertEquals(utilizadorRestDTO1,utilizadorRes );

    }


}



