package wsproposta.proposta.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.DTO.CandidaturaDTOParcial;
import wsproposta.proposta.DTO.NewCandidaturaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.DTO.assemblers.CandidaturaDomainDTOAssembler;
import wsproposta.proposta.datamodel.JPA.assembler.CandidaturaDomainDataAssembler;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.domain.factories.ICandidaturaFactory;
import wsproposta.proposta.repositories.CandidaturaRepository;
import wsproposta.proposta.repositories.ProjetoWebRepository;
import wsproposta.proposta.repositories.PropostaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CandidaturaServiceTest {

    @MockBean
    ICandidaturaFactory candidaturaFactory;
    @MockBean
    CandidaturaRepository candidaturaRepository;
    @MockBean
    ProjetoWebRepository projetoWebRepository;
    @MockBean
    PropostaRepository propostaRepository;
    @MockBean
    CandidaturaDomainDTOAssembler candidaturaDomainDTOAssembler;

    @InjectMocks
    CandidaturaService candidaturaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldCreateAndSaveCandidatura() throws Exception {
        //Arrange
        NewCandidaturaInfoDTO newCandidaturaInfoDTO = mock(NewCandidaturaInfoDTO.class);
        when(newCandidaturaInfoDTO.getCodProposta()).thenReturn(1);
        when(newCandidaturaInfoDTO.getCodProposta()).thenReturn(1);

        Proposta prop = mock(Proposta.class);
        Optional<Proposta> opProp = Optional.of(prop);
        when(propostaRepository.findById(1)).thenReturn(opProp);
        when(prop.getEstado()).thenReturn(Proposta.Estado.ACEITE);

        Candidatura cand = mock(Candidatura.class);
        Optional<Candidatura> opCand = Optional.of(cand);
        when(candidaturaRepository.findById(1)).thenReturn(opCand);


        CandidaturaDTO candidaturaDTODouble = mock(CandidaturaDTO.class);
        Candidatura candidaturaCreated = mock(Candidatura.class);
        Candidatura candidaturaSaved = mock(Candidatura.class);


        when(candidaturaFactory.createCandidatura(newCandidaturaInfoDTO.getCodProposta(), newCandidaturaInfoDTO.getCodEstudante())).thenReturn(candidaturaCreated);
        when(candidaturaRepository.save(candidaturaCreated)).thenReturn(candidaturaSaved);
        when(candidaturaDomainDTOAssembler.toDTO(candidaturaSaved)).thenReturn(candidaturaDTODouble);

        //act
        CandidaturaDTO candidaturaDTOAct = candidaturaService.createAndSaveCandidatura(newCandidaturaInfoDTO);

        //assert
        assertEquals(candidaturaDTODouble, candidaturaDTOAct);

    }

    @Test
    void shouldFindAllCandidaturas() {
        //arrange
        Candidatura candidatura = mock(Candidatura.class);
        Candidatura candidatura1 = mock(Candidatura.class);


        List<Candidatura> candidaturaList = new ArrayList<>();
        candidaturaList.add(candidatura);
        candidaturaList.add(candidatura1);

        when(candidaturaRepository.findAll()).thenReturn(candidaturaList);

        CandidaturaDTO candidaturaDTO = mock(CandidaturaDTO.class);
        CandidaturaDTO candidaturaDTO1 = mock(CandidaturaDTO.class);


        when(candidaturaDomainDTOAssembler.toDTO(candidatura)).thenReturn(candidaturaDTO);
        when(candidaturaDomainDTOAssembler.toDTO(candidatura1)).thenReturn(candidaturaDTO1);


        List<CandidaturaDTO> candidaturaDTOList = new ArrayList<>();
        candidaturaDTOList.add(candidaturaDTO);
        candidaturaDTOList.add(candidaturaDTO1);


        //act
        List<CandidaturaDTO> candidaturaListFinal = candidaturaService.findAllCandidaturas();

        //assert

        assertEquals(2, candidaturaListFinal.size());
        assertEquals(candidaturaListFinal, candidaturaDTOList);

    }

    @Test
    void shouldGetCandidaturaByCodCandidatura() {

        //arrange
        Candidatura candidatura = mock(Candidatura.class);
        Optional<Candidatura> optionalCandidatura = Optional.of(candidatura);

        when(candidaturaRepository.findById(1)).thenReturn(optionalCandidatura);

        Candidatura candidatura1 = optionalCandidatura.get();
        CandidaturaDTO candidaturaDTO = mock(CandidaturaDTO.class);

        when(candidaturaDomainDTOAssembler.toDTO(candidatura1)).thenReturn(candidaturaDTO);


        //act
        Optional<CandidaturaDTO> candidaturaTeste = candidaturaService.getCandidaturaByCodCandidatura(1);

        //assert
        Optional<CandidaturaDTO> optionalCandidaturaDTO = Optional.of(candidaturaDTO);
        assertEquals(candidaturaTeste, optionalCandidaturaDTO);
    }

    @Test
    void shouldUpdateEstadoCandidatura() throws Exception {

        //arrange
        CandidaturaDTOParcial candidaturaDTOParcialDouble = mock(CandidaturaDTOParcial.class);
        when(candidaturaDTOParcialDouble.getCodCandidatura()).thenReturn(1);
        when(candidaturaDTOParcialDouble.getEstadoEstudante()).thenReturn("ACEITE");

        Candidatura candidaturaDouble = mock(Candidatura.class);
        Optional<Candidatura> optionalCandidatura = Optional.of(candidaturaDouble);
        when(candidaturaRepository.findById(1)).thenReturn(optionalCandidatura);


        Candidatura candidaturaDouble2 = mock(Candidatura.class);
        when(candidaturaRepository.save(optionalCandidatura.get())).thenReturn(candidaturaDouble2);
        CandidaturaDTO candidaturaDTODouble = mock(CandidaturaDTO.class);
        when(candidaturaDomainDTOAssembler.toDTO(candidaturaDouble2)).thenReturn(candidaturaDTODouble);
        ProjetoRestDto projetoRestDtoDouble = mock(ProjetoRestDto.class);
        when(projetoWebRepository.createAndSaveProjeto(projetoRestDtoDouble)).thenReturn(true);


        //act
        CandidaturaDTO candidaturaTeste = candidaturaService.updateEstadoCandidatura(candidaturaDTOParcialDouble, 1);

        assertEquals(candidaturaTeste, candidaturaDTODouble);
        assertEquals(candidaturaTeste.getEstadoEstudante(), candidaturaDTODouble.getEstadoEstudante());
    }


}