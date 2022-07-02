package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.ConviteRecusadoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ConviteJDBCDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.repositories.JDBC.ConviteJDBCRepository;
import Sprint.WsProjeto.repositories.JDBC.ConviteRecusadoJDBCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteRecusadoRepositoryTest {
    @MockBean
    ConviteJDBCDomainDataAssembler conviteJDBCDomainDataAssembler;

    @MockBean
    ConviteRecusadoJDBCRepository conviteRecusadoJDBCRepository;

    @InjectMocks
    ConviteRecusadoRepository conviteRecusadoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveConviteRecusado() throws SQLException {
        //arrange
        Convite convite = mock(Convite.class);
        ConviteRecusadoJDBC conviteRecusadoJDBC = mock(ConviteRecusadoJDBC.class);
        when(conviteJDBCDomainDataAssembler.toJDBCRecusado(convite)).thenReturn(conviteRecusadoJDBC);
        ConviteRecusadoJDBC savedOne = mock(ConviteRecusadoJDBC.class);
        when(conviteRecusadoJDBCRepository.save(conviteRecusadoJDBC)).thenReturn(savedOne);
        when(conviteJDBCDomainDataAssembler.toDomainRecusado(savedOne)).thenReturn(convite);

        //act
        Convite act = conviteRecusadoRepository.save(convite);

        //assert
        assertEquals(act,convite);
    }

    @Test
    void shouldFindByID() throws SQLException {
        //arrange
        ConviteRecusadoJDBC conviteRecusadoJDBC = mock(ConviteRecusadoJDBC.class);
        Optional<ConviteRecusadoJDBC> optional = Optional.of(conviteRecusadoJDBC);
        when(conviteRecusadoJDBCRepository.getById(1)).thenReturn(optional);
        Convite convite = mock(Convite.class);
        when(conviteJDBCDomainDataAssembler.toDomainRecusado(optional.get())).thenReturn(convite);
        //act
        Optional<Convite> act = conviteRecusadoRepository.findById(1);
        //arrange
        assertEquals(act,Optional.of(convite));
    }

}