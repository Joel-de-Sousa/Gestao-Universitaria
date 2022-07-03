package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
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
    @Test
    void shouldFindAllPropostasAceitesByCodEdicao() throws Exception {
        //arrange

        List<PropostaRestDTO> list1 = new ArrayList<>();
        Optional<List<PropostaRestDTO>> optionalList = Optional.of(list1);
        when(propostaRestRepository.findAllPropostasAceitesByCodEdicao(1)).thenReturn(optionalList);

        //act
        List<PropostaRestDTO> act = propostaWebRepository.findAllPropostasAceitesByCodEdicao(1);
        //assert
        assertEquals(act,optionalList.get());
    }

    @Test
    void shouldFindAllPropostasAceitesByNIF() throws Exception {
        //arrange

        List<PropostaRestDTO> list1 = new ArrayList<>();
        Optional<List<PropostaRestDTO>> optionalList = Optional.of(list1);
        when(propostaRestRepository.findAllPropostasAceitesByNif(123456)).thenReturn(optionalList);

        //act
        List<PropostaRestDTO> act = propostaWebRepository.findAllPropostasAceitesByNif(123456);
        //assert
        assertEquals(act,optionalList.get());
    }




}