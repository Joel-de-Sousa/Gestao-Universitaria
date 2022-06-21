package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.SubmissaoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.repositories.JPA.SubmissaoJPARepository;
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
class SubmissaoRepositoryTest {


    @MockBean
    SubmissaoJPARepository submissaoJPARepository;
    @MockBean
    SubmissaoDomainDataAssembler submissaoDomainDataAssembler;

    @InjectMocks
    SubmissaoRepository submissaoRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveJuri(){
        //arrange
        Submissao submissao = mock(Submissao.class);
        SubmissaoJPA submissaoJPA = mock(SubmissaoJPA.class);
        when(submissaoDomainDataAssembler.toData(submissao)).thenReturn(submissaoJPA);
        SubmissaoJPA savedJpa = mock(SubmissaoJPA.class);
        when(submissaoJPARepository.save(submissaoJPA)).thenReturn(savedJpa);

        when(submissaoDomainDataAssembler.toDomain(savedJpa)).thenReturn(submissao);


        //act
       Submissao result = submissaoRepository.save(submissao);

        assertEquals(result, submissao);



    }

    @Test
    void shouldFindById(){
        //arrange

        SubmissaoJPA submissaoJPA = mock(SubmissaoJPA.class);
        Optional<SubmissaoJPA> optionalSubmissaoJPA = Optional.of(submissaoJPA);
        when(submissaoJPARepository.findById(1)).thenReturn(optionalSubmissaoJPA);
        Submissao submissao = mock(Submissao.class);
        when(submissaoDomainDataAssembler.toDomain(optionalSubmissaoJPA.get())).thenReturn(submissao);


        //act
        Optional<Submissao> result = submissaoRepository.findById(1);

        //assert
        assertEquals(result,Optional.of(submissao));

    }

}