package wsproposta.proposta.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.datamodel.JPA.assembler.PropostaDomainDataAssembler;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.JPA.PropostaJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaRepositoryTest {

    @MockBean
    PropostaDomainDataAssembler propostaAssembler;

    @MockBean
    PropostaJPARepository propostaJPARepository;
    @InjectMocks
    PropostaRepository propostaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Save Proposta")
    void shouldSavePropostaWithCorrectAttributes() {

        // Arrange
        Proposta propostaDouble = mock(Proposta.class);
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        PropostaJPA propostaJPASavedDouble = mock(PropostaJPA.class);
        Proposta propostaSavedDouble = mock(Proposta.class);

        when(propostaAssembler.toData(propostaDouble)).thenReturn(propostaJPADouble);
        when(propostaJPARepository.save(propostaJPADouble)).thenReturn(propostaJPASavedDouble);
        when(propostaAssembler.toDomain(propostaJPASavedDouble)).thenReturn(propostaSavedDouble);

        // Act
        Proposta proposta = propostaRepository.save(propostaDouble);

        // Assert
        assertEquals(propostaSavedDouble, proposta);
    }


    @Test
    @DisplayName("Teste Find Proposta by codProposta")
    void shouldFindPropostaByCodPropostaWithCorrectAttributes() {

        // Arrange
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        Optional<PropostaJPA> opPropostaJPA = Optional.of(propostaJPADouble);
        when(propostaJPARepository.findById(1)).thenReturn(opPropostaJPA);

        PropostaJPA propostaJPADouble2 = opPropostaJPA.get();
        Proposta propostaDouble = mock(Proposta.class);
        when(propostaAssembler.toDomain(propostaJPADouble2)).thenReturn(propostaDouble);


        // Act
        Optional<Proposta> opProposta = propostaRepository.findById(1);

        // Assert
        Optional<Proposta> opPropostaDouble= Optional.of(propostaDouble);
        assertEquals(opPropostaDouble, opProposta);
    }

    @Test
    @DisplayName("Teste Find All Propostas")
    void shouldFindAllPropostaWithCorrectAttributes()  {

        // Arrange
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        PropostaJPA propostaJPADouble2 = mock(PropostaJPA.class);

        List<PropostaJPA> listPropostasJPA = new ArrayList<>();
        listPropostasJPA.add(propostaJPADouble);
        listPropostasJPA.add(propostaJPADouble2);

        when(propostaJPARepository.findAll()).thenReturn(listPropostasJPA);

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);
        when(propostaAssembler.toDomain(propostaJPADouble)).thenReturn(propostaDouble);
        when(propostaAssembler.toDomain(propostaJPADouble2)).thenReturn(propostaDouble2);

        List<Proposta> listPropostasDouble = new ArrayList<>();
        listPropostasDouble.add(propostaDouble);
        listPropostasDouble.add(propostaDouble2);

        // Act
        List<Proposta> listProposta = propostaRepository.findAll();

        // Assert
        assertEquals(2, listProposta.size());
        assertEquals(listPropostasDouble, listProposta);
    }

    @Test
    @DisplayName("Teste Find All Propostas By CodUtilizador")
    void shouldFindAllPropostaByCodUtilizadorWithCorrectAttributes()  {

        // Arrange
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        PropostaJPA propostaJPADouble2 = mock(PropostaJPA.class);

        List<PropostaJPA> listPropostasJPA = new ArrayList<>();
        listPropostasJPA.add(propostaJPADouble);
        listPropostasJPA.add(propostaJPADouble2);

        when(propostaJPARepository.findAllByCodUtilizador(1)).thenReturn(listPropostasJPA);

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);
        when(propostaAssembler.toDomain(propostaJPADouble)).thenReturn(propostaDouble);
        when(propostaAssembler.toDomain(propostaJPADouble2)).thenReturn(propostaDouble2);

        List<Proposta> listPropostasDouble = new ArrayList<>();
        listPropostasDouble.add(propostaDouble);
        listPropostasDouble.add(propostaDouble2);

        // Act
        List<Proposta> listPropostaFiltrada = propostaRepository.findAllByCodUtilizador(1);

        // Assert
        assertEquals(2, listPropostaFiltrada.size());
        assertEquals(listPropostasDouble, listPropostaFiltrada);
    }

    @Test
    @DisplayName("Teste Find All Propostas By NIF")
    void shouldFindAllPropostaByNifOrganizacaoWithCorrectAttributes()  {

        // Arrange
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        PropostaJPA propostaJPADouble2 = mock(PropostaJPA.class);

        List<PropostaJPA> listPropostasJPA = new ArrayList<>();
        listPropostasJPA.add(propostaJPADouble);
        listPropostasJPA.add(propostaJPADouble2);

        when(propostaJPARepository.findAllByNifOrganizacao(257837248)).thenReturn(listPropostasJPA);

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);
        when(propostaAssembler.toDomain(propostaJPADouble)).thenReturn(propostaDouble);
        when(propostaAssembler.toDomain(propostaJPADouble2)).thenReturn(propostaDouble2);

        List<Proposta> listPropostasDouble = new ArrayList<>();
        listPropostasDouble.add(propostaDouble);
        listPropostasDouble.add(propostaDouble2);

        // Act
        List<Proposta> listPropostaFiltrada = propostaRepository.findAllByNifOrganizacao(257837248);

        // Assert
        assertEquals(2, listPropostaFiltrada.size());
        assertEquals(listPropostasDouble, listPropostaFiltrada);
    }

    @Test
    @DisplayName("Teste Find All Propostas By NIF")
    void shouldFindAllPropostaByNifOrganizacaoAndReturnEmptyListWithCorrectAttributes()  {

        // Arrange
        List<PropostaJPA> listPropostasJPA = new ArrayList<>();

        when(propostaJPARepository.findAllByCodUtilizador(257837248)).thenReturn(listPropostasJPA);

        List<Proposta> listPropostasDouble = new ArrayList<>();


        // Act
        List<Proposta> listPropostaFiltrada = propostaRepository.findAllByNifOrganizacao(257837248);

        // Assert
        assertEquals(0, listPropostaFiltrada.size());
        assertEquals(listPropostasDouble, listPropostaFiltrada);
    }


    @Test
    @DisplayName("Teste Find All Propostas By Titulo")
    void shouldFindAllPropostaByTituloWithCorrectAttributes()  {

        // Arrange
        PropostaJPA propostaJPADouble = mock(PropostaJPA.class);
        PropostaJPA propostaJPADouble2 = mock(PropostaJPA.class);

        List<PropostaJPA> listPropostasJPA = new ArrayList<>();
        listPropostasJPA.add(propostaJPADouble);
        listPropostasJPA.add(propostaJPADouble2);

        when(propostaJPARepository.findByTituloContains("TituloTeste")).thenReturn(listPropostasJPA);

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);
        when(propostaAssembler.toDomain(propostaJPADouble)).thenReturn(propostaDouble);
        when(propostaAssembler.toDomain(propostaJPADouble2)).thenReturn(propostaDouble2);

        List<Proposta> listPropostasDouble = new ArrayList<>();
        listPropostasDouble.add(propostaDouble);
        listPropostasDouble.add(propostaDouble2);

        // Act
        List<Proposta> listPropostaFiltrada = propostaRepository.findAllByTitulo("TituloTeste");

        // Assert
        assertEquals(2, listPropostaFiltrada.size());
        assertEquals(listPropostasDouble, listPropostaFiltrada);
    }

    @Test
    @DisplayName("Teste Find All Propostas By Titulo EmptyList")
    void shouldFindAllPropostaByTituloAndReturnEmptyListWithCorrectAttributes()  {

        // Arrange
        List<PropostaJPA> listPropostasJPA = new ArrayList<>();

        when(propostaJPARepository.findByTituloContains("TituloTeste")).thenReturn(listPropostasJPA);

        List<Proposta> listPropostasDouble = new ArrayList<>();

        // Act
        List<Proposta> listPropostaFiltrada = propostaRepository.findAllByTitulo("TituloTeste");

        // Assert
        assertEquals(0, listPropostaFiltrada.size());
        assertEquals(listPropostasDouble, listPropostaFiltrada);
    }
}