package wsproposta.proposta.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.datamodel.JPA.CandidaturaJPA;
import wsproposta.proposta.datamodel.JPA.assembler.CandidaturaDomainDataAssembler;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.repositories.JPA.ICandidaturaJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class CandidaturaRepositoryTest {

    @MockBean
    ICandidaturaJPARepository candidaturaJPARepository;

    @MockBean
    CandidaturaDomainDataAssembler candidaturaDomainDataAssembler;

    @InjectMocks
    CandidaturaRepository candidaturaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void ShouldSaveCandidatura(){

        Candidatura candidaturaDouble = mock(Candidatura.class);
        CandidaturaJPA candidaturaJPADouble = mock(CandidaturaJPA.class);
        Candidatura candidaturaDoubleSaved = mock(Candidatura.class);
        CandidaturaJPA candidaturaJPADoubleSaved = mock(CandidaturaJPA.class);

        when(candidaturaDomainDataAssembler.toData(candidaturaDouble)).thenReturn(candidaturaJPADouble);
        when(candidaturaJPARepository.save(candidaturaJPADouble)).thenReturn(candidaturaJPADoubleSaved);
        when(candidaturaDomainDataAssembler.toDomain(candidaturaJPADoubleSaved)).thenReturn(candidaturaDoubleSaved);


        //act
        Candidatura candidatura = candidaturaRepository.save(candidaturaDouble);


        //assert

        assertEquals(candidatura,candidaturaDoubleSaved);

    }

    @Test
    void shouldFindCandidaturaById(){

        //arrange
        CandidaturaJPA candidatura = mock(CandidaturaJPA.class);
        Optional<CandidaturaJPA> candidatura1  = Optional.of(candidatura);

        when(candidaturaJPARepository.findById(1)).thenReturn(candidatura1);

        CandidaturaJPA candidaturaJPA2 = candidatura1.get();
        Candidatura candidatura2 = mock(Candidatura.class);
        when(candidaturaDomainDataAssembler.toDomain(candidaturaJPA2)).thenReturn(candidatura2);

        //act
        Optional<Candidatura> optionalCandidatura = candidaturaRepository.findById(1);

        //assert
        Optional<Candidatura> opCandidatura = Optional.of(candidatura2);

        assertEquals(optionalCandidatura,opCandidatura);
    }

    @Test
    void shouldFindAllCandidaturas(){

        //Arrange
        CandidaturaJPA candidaturaJPA = mock(CandidaturaJPA.class);
        CandidaturaJPA candidaturaJPA1 = mock(CandidaturaJPA.class);

        List<CandidaturaJPA> candidaturaListJPA = new ArrayList<>();

        candidaturaListJPA.add(candidaturaJPA);
        candidaturaListJPA.add(candidaturaJPA1);

        when(candidaturaJPARepository.findAll()).thenReturn(candidaturaListJPA);

        Candidatura candidaturaDouble = mock(Candidatura.class);
        Candidatura candidaturaDouble2 = mock(Candidatura.class);

        when(candidaturaDomainDataAssembler.toDomain(candidaturaJPA)).thenReturn(candidaturaDouble);
        when(candidaturaDomainDataAssembler.toDomain(candidaturaJPA1)).thenReturn(candidaturaDouble2);

        List<Candidatura> listDouble = new ArrayList<>();
        listDouble.add(candidaturaDouble);
        listDouble.add(candidaturaDouble2);



        //act
        List<Candidatura> candidaturaList = candidaturaRepository.findAll();

        //Assert
        assertEquals(candidaturaList,listDouble);
    }

    @Test
    void shouldFindCandidaturaById2(){

        Optional<CandidaturaJPA> candidatura1  = Optional.empty();

        when(candidaturaJPARepository.findById(1)).thenReturn(candidatura1);


        Candidatura candidatura2 = mock(Candidatura.class);


        //act
        Optional<Candidatura> optionalCandidatura = candidaturaRepository.findById(1);

        //assert


        assertEquals(optionalCandidatura,candidatura1);
    }






}