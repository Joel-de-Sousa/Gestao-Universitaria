package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.repositories.REST.EdicaoRestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoWebRepositoryTest {

    @InjectMocks
    EdicaoWebRepository edicaoWebRepository;

    @MockBean
    EdicaoRestRepository edicaoRestRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindEdicaoByCode(){
        //arrange
        EdicaoRestDTO edicaoRestDTO = mock(EdicaoRestDTO.class);
        Optional<EdicaoRestDTO> optional = Optional.of(edicaoRestDTO);
        when(edicaoRestRepository.findEdicaoByCode(1)).thenReturn(optional);
        //act
        Optional<EdicaoRestDTO> act = edicaoWebRepository.findEdicaoByCode(1);
        //assert
        assertEquals(act,optional);


    }

    @Test
    void shouldGetListaEdicoesByRuc() throws Exception {
        //arrange

        List<EdicaoRestDTO> edicaoRestDTOList = new ArrayList<>() ;
        Optional<List<EdicaoRestDTO>> listOptional = Optional.of(edicaoRestDTOList);
        when(edicaoRestRepository.getAllEdicoesByCodRUC(1)).thenReturn(listOptional);
        edicaoRestDTOList = listOptional.get();

       //act
       List<EdicaoRestDTO>  act = edicaoWebRepository.getListaEdicoesByCodRUC(1);

       assertEquals(act,edicaoRestDTOList);
    }

}