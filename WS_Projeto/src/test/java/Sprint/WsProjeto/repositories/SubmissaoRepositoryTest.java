package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.SubmissaoJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.SubmissaoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.repositories.JDBC.SubmissaoJDBCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SubmissaoRepositoryTest {
    @MockBean
    SubmissaoJDBCRepository submissaoJDBCRepository;

    @MockBean
    SubmissaoJDBCDomainDataAssembler submissaoJDBCDomainDataAssembler;

    @InjectMocks
    SubmissaoRepository submissaoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveSubmissao() throws SQLException {
        //arrange
        Submissao submissao = mock(Submissao.class);
        SubmissaoJDBC submissaoJDBC = mock(SubmissaoJDBC.class);
        when(submissaoJDBCDomainDataAssembler.toJDBC(submissao)).thenReturn(submissaoJDBC);
        SubmissaoJDBC saved = mock(SubmissaoJDBC.class);
        when(submissaoJDBCRepository.save(submissaoJDBC)).thenReturn(saved);
        when(submissaoJDBCDomainDataAssembler.toDomain(saved)).thenReturn(submissao);

        //act
        Submissao act = submissaoRepository.save(submissao);

        //assert
        assertEquals(act,submissao);
    }
    @Test
    void shouldFindById() throws SQLException {
        //arrange
        SubmissaoJDBC submissaoJDBC = mock(SubmissaoJDBC.class);
        Optional<SubmissaoJDBC> optional = Optional.of(submissaoJDBC);
        when(submissaoJDBCRepository.getById(1)).thenReturn(optional);
        Submissao submissao = mock(Submissao.class);
        when(submissaoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(submissao);

        //act
        Optional<Submissao> act = submissaoRepository.findById(1);

        //assert
        assertEquals(act,Optional.of(submissao));
    }

}