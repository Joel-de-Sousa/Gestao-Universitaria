package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.ConviteJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ConviteJDBCDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.repositories.JDBC.ConviteJDBCRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteRepositoryTest {
    @MockBean
    ConviteJDBCRepository conviteJDBCRepository;
    @MockBean
    ConviteJDBCDomainDataAssembler conviteJDBCDomainDataAssembler;
    @InjectMocks
    ConviteRepository conviteRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveConvite() throws SQLException {
        //arrange
        Convite convite = mock(Convite.class);


        ConviteJDBC conviteJDBC = mock(ConviteJDBC.class);
        when(conviteJDBCDomainDataAssembler.toJDBC(convite)).thenReturn(conviteJDBC);
        ConviteJDBC saved = mock(ConviteJDBC.class);
        when(conviteJDBCRepository.save(conviteJDBC)).thenReturn(saved);
        when(conviteJDBCDomainDataAssembler.toDomain(saved)).thenReturn(convite);


        //act
        Convite act = conviteRepository.save(convite);

        //assert
        assertEquals(act,convite);
    }

    @Test
    void shouldFindConviteById() throws SQLException {
        //arrange
        ConviteJDBC conviteJDBC = mock(ConviteJDBC.class);
        Optional<ConviteJDBC> optional = Optional.of(conviteJDBC);
        when(conviteJDBCRepository.getById(1)).thenReturn(optional);
        Convite convite = mock(Convite.class);
        when(conviteJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(convite);

        Optional<Convite> act = conviteRepository.findById(1);

        //assert
        assertEquals(act,Optional.of(convite));
    }

    @Test
    void shouldDeleteByCodeConvite() throws SQLException {

    }


}