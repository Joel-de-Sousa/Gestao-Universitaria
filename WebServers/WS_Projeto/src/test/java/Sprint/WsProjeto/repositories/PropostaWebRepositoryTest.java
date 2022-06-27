package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaWebRepositoryTest {

    @Mock
    PropostaRestRepository propostaRestRepository;

    @InjectMocks
    PropostaWebRepository propostaWebRepository;

    @Test

    void shouldFindPropostaByCode(){
        //arrange
        PropostaRestDTO propostaRestDTO = mock(PropostaRestDTO.class);
        Optional<PropostaRestDTO> propostaRestDTO1 = Optional.of(propostaRestDTO);
        when(propostaRestRepository.findPropostaByCode(1)).thenReturn(propostaRestDTO1);

        //act
        Optional<PropostaRestDTO> propostaRest = propostaWebRepository.findPropostaByCode(1);

        assertEquals(propostaRest,propostaRestDTO1);

    }

}