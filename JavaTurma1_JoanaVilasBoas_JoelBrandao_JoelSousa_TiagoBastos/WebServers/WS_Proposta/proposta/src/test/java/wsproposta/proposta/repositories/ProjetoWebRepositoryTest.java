
package wsproposta.proposta.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.repositories.REST.ProjetoRestRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoWebRepositoryTest {

    @MockBean
    ProjetoRestRepository projetoRestRepository;

    @InjectMocks
    ProjetoWebRepository projetoWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void test() throws Exception {

        ProjetoRestDto projetoRestDTO = mock(ProjetoRestDto.class);
        when(projetoRestDTO.getCodProjeto()).thenReturn(1);
        when(projetoRestDTO.getCodOrientador()).thenReturn(1);
        when(projetoRestDTO.getCodProposta()).thenReturn(1);
        when(projetoRestDTO.getCodEstudante()).thenReturn(1);


        when(projetoRestRepository.createAndSaveProjeto(projetoRestDTO)).thenReturn(true);

        boolean teste = projetoWebRepository.createAndSaveProjeto(projetoRestDTO);

        boolean expResult = true;

        assertEquals(expResult, teste);

        assertTrue(teste);

    }


}
