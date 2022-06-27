package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.repositories.REST.EdicaoRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoWebRepositoryTest {

    @MockBean
    EdicaoRestRepository edicaoRestRepository;

    @InjectMocks
    EdicaoWebRepository edicaoWebRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindEdicaoByCode(){
        //arrange
        EdicaoRestDTO edicaoRestDTO = mock(EdicaoRestDTO.class);
        Optional<EdicaoRestDTO> optionalEdicaoRestDTO = Optional.of(edicaoRestDTO);
        when(edicaoRestRepository.findEdicaoByCode(1)).thenReturn(optionalEdicaoRestDTO);


        //act
        Optional<EdicaoRestDTO> result = edicaoWebRepository.findEdicaoByCode(1);

        //assert
        assertEquals(result,optionalEdicaoRestDTO);
    }

}