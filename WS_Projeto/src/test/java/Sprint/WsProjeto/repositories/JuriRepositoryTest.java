package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.JuriJDBCDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.JDBC.JuriJDBCRepository;
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
class JuriRepositoryTest {
    @MockBean
    JuriJDBCDomainDataAssembler juriJDBCDomainDataAssembler;

    @MockBean
    JuriJDBCRepository juriJDBCRepository;

    @InjectMocks
    JuriRepository juriRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveJuri() throws SQLException {
        //arrange
        Juri juri = mock(Juri.class);
        JuriJDBC juriJDBC = mock(JuriJDBC.class);
        when(juriJDBCDomainDataAssembler.toJDBC(juri)).thenReturn(juriJDBC);
        JuriJDBC saved = mock(JuriJDBC.class);
        when(juriJDBCRepository.save(juriJDBC)).thenReturn(saved);

        when(juriJDBCDomainDataAssembler.toDomain(saved)).thenReturn(juri);

        //act
        Juri act = juriRepository.save(juri);

        //assert
        assertEquals(act,juri);


    }

    @Test
    void ShouldFindJuriById() throws SQLException {

        //arrange
        JuriJDBC juriJDBC = mock(JuriJDBC.class);

        Optional<JuriJDBC> optional = Optional.of(juriJDBC);

        when(juriJDBCRepository.getById(1)).thenReturn(optional);

        Juri juri = mock(Juri.class);

        when(juriJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(juri);

        //act
        Optional<Juri> act = juriRepository.findById(1);

        //assert
        assertEquals(act,Optional.of(juri));


    }

}