package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.AvaliacaoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.repositories.JPA.AvaliacaoJPARepository;
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
class AvaliacaoRepositoryTest {

    @MockBean
    AvaliacaoDomainDataAssembler avaliacaoDomainDataAssembler;

    @MockBean
    AvaliacaoJPARepository avaliacaoJPARepository;

    @InjectMocks
    AvaliacaoRepository avaliacaoRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAvaliacao(){

        //arrange

        Avaliacao avaliacao1 = mock (Avaliacao.class);

        AvaliacaoJPA avaliacaoJPA = mock(AvaliacaoJPA.class);

        when(avaliacaoDomainDataAssembler.toData(avaliacao1)).thenReturn(avaliacaoJPA);

        AvaliacaoJPA avaliacaoJPASaved = mock(AvaliacaoJPA.class);

        when(avaliacaoJPARepository.save(avaliacaoJPA)).thenReturn(avaliacaoJPASaved);
        when(avaliacaoDomainDataAssembler.toDomain(avaliacaoJPASaved)).thenReturn(avaliacao1);



        //act
        Avaliacao avaliacao = avaliacaoRepository.save(avaliacao1);

        //assert

        assertEquals(avaliacao,avaliacao1);




    }
    @Test
    void shouldFindAvaliacaoByID (){
        //arrange
        AvaliacaoJPA avaliacaoJPA = mock(AvaliacaoJPA.class);
        Optional<AvaliacaoJPA> optionalAvaliacaoJPA = Optional.of(avaliacaoJPA);
        when(avaliacaoJPARepository.findById(1)).thenReturn(optionalAvaliacaoJPA);

        Avaliacao avaliacao = mock(Avaliacao.class);
        when(avaliacaoDomainDataAssembler.toDomain(optionalAvaliacaoJPA.get())).thenReturn(avaliacao);

        //act
        Optional<Avaliacao> result = avaliacaoRepository.findById(1);

        //assert
        assertEquals(Optional.of(avaliacao),result);
    }

}