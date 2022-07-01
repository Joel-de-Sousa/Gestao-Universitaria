package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.AvaliacaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.AvaliacaoJDBCDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.repositories.JDBC.AvaliacaoJDBCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class AvaliacaoRepositoryTest {

    @InjectMocks
    AvaliacaoRepository avaliacaoRepository;

    @MockBean
    AvaliacaoJDBCDomainDataAssembler avaliacaoJDBCDomainDataAssembler;

    @MockBean
    AvaliacaoJDBCRepository avaliacaoJDBCRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveAvaliacao() throws Exception {
        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        AvaliacaoJDBC avaliacaoJDBC = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCDomainDataAssembler.toJDBC(avaliacao)).thenReturn(avaliacaoJDBC);
        AvaliacaoJDBC savedOne = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCRepository.save(avaliacaoJDBC)).thenReturn(savedOne);
        Avaliacao avaliacao1 = mock(Avaliacao.class);
        when(avaliacaoJDBCDomainDataAssembler.toDomain(savedOne)).thenReturn(avaliacao1);

        //act
        Avaliacao act = avaliacaoRepository.save(avaliacao);

        //assert
        assertEquals(act,avaliacao1);


    }

    @Test
    void shouldUpdateAvaliacao() throws Exception {

        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        AvaliacaoJDBC avaliacaoJDBC = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCDomainDataAssembler.toJDBC(avaliacao)).thenReturn(avaliacaoJDBC);
        AvaliacaoJDBC savedOne = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCRepository.updatePresidente(avaliacaoJDBC)).thenReturn(savedOne);
        Avaliacao avaliacao1 = mock(Avaliacao.class);
        when(avaliacaoJDBCDomainDataAssembler.toDomain(savedOne)).thenReturn(avaliacao1);



        //act
        Avaliacao act = avaliacaoRepository.update(avaliacao);

        //assert
        assertEquals(act,avaliacao1);
    }

}