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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Test
    void shouldUpdateRuc() throws Exception {
        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        AvaliacaoJDBC avaliacaoJDBC = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCDomainDataAssembler.toJDBC(avaliacao)).thenReturn(avaliacaoJDBC);
        AvaliacaoJDBC savedOne = mock(AvaliacaoJDBC.class);
        when(avaliacaoJDBCRepository.updateRUC(avaliacaoJDBC)).thenReturn(savedOne);
        when(avaliacaoJDBCDomainDataAssembler.toDomain(savedOne)).thenReturn(avaliacao);


        //act
        Avaliacao act = avaliacaoRepository.updateRuc(avaliacao);

        //assert
        assertEquals(act,avaliacao);
    }

    @Test
    void shouldFindAvaliacaoById() throws Exception {
        //arrange
        AvaliacaoJDBC avaliacaoJDBC = mock(AvaliacaoJDBC.class);
        Optional<AvaliacaoJDBC> optional = Optional.of(avaliacaoJDBC);
        when(avaliacaoJDBCRepository.getById(1)).thenReturn(optional);
        Avaliacao avaliacao = mock(Avaliacao.class);
        when(avaliacaoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(avaliacao);

        //act
        Optional<Avaliacao> act = avaliacaoRepository.findById(1);

        //assert
        assertEquals(act,Optional.of(avaliacao));
    }
    @Test
    void shouldFindAvaliacaoesByCodProjeto() throws Exception {
        //arrange
        AvaliacaoJDBC avaliacaoJDBC = mock(AvaliacaoJDBC.class);
        List<AvaliacaoJDBC> list1 = new ArrayList<>();
        list1.add(avaliacaoJDBC);
        when(avaliacaoJDBCRepository.findAvaliacoesByCodProjeto(1)).thenReturn(list1);
        List<Avaliacao> list2 = new ArrayList<>();

        list2.add(avaliacaoJDBCDomainDataAssembler.toDomain(avaliacaoJDBC));


        //act
        List<Avaliacao> act = avaliacaoRepository.findAvaliacoesByCodProjeto(1);

        assertEquals(act,list2);
    }

}