package WSEdicao.services;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.AnoLetivoFactory;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.domain.factories.UcFactory;
import WSEdicao.dto.*;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.UcRepository;
import WSEdicao.repositories.jpa.EstudanteJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EdicaoServiceTest {

    @MockBean
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @MockBean
    UcDomainDTOAssembler ucDTOAssembler;

    @MockBean
    AnoLetivo anoLetivo;

    @MockBean
    Uc uc;

    @MockBean
    AnoLetivoFactory anoLetivoFactory;

    @MockBean
    UcFactory ucFactory;

    @MockBean
    UcRepository ucRepository;

    @MockBean
    AnoLetivoRepository anoLetivoRepository;

    @MockBean
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    @MockBean
    EdicaoDomainDataAssembler edicaoAssembler;

    @MockBean
    EdicaoRepository edicaoRepository;

    @MockBean
    EdicaoFactory edicaoFactory;

    @MockBean
    EdicaoDTO edicaoDTO;

    @MockBean
    EdicaoAllArgsDTO edicaoAllArgsDTO;

    @MockBean
    Edicao edicao;

    @MockBean
    EstudanteJpaRepository estudanteJpaRepository;

    @InjectMocks
    EdicaoService edicaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAEdicaoWithCorrectAttributes() throws Exception {
        // Arrange
        UcDTO ucDTO = mock(UcDTO.class);
        when(ucRepository.findBycodUc(1)).thenReturn(Optional.of(ucDTO));
        //Optional<UcDTO> optionalUcDTO = ucRepository.findBycodUc(1);


        AnoLetivoDTO anoLetivoDTO = mock(AnoLetivoDTO.class);
        when(anoLetivoRepository.findBycodAnoLetivo(1)).thenReturn(Optional.of(anoLetivoDTO));
        //Optional<AnoLetivoDTO> optionalAnoLetivoDTO = anoLetivoRepository.findBycodAnoLetivo(1);

        Edicao edicao = edicaoFactory.createEdicao(ucDTO.getCodUc(),anoLetivoDTO.getCodAnoLetivo(),1);
        Edicao savedEdicao = edicaoRepository.save(edicao);
        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(savedEdicao);

        // Act
        EdicaoDTO edicaoAct = edicaoService.createAndSaveEdicao(1, 1,1);

        // Assert
        assertEquals(edicaoDTO, edicaoAct);
    }

    @Test
    void shouldFindSpecificEdicaoSearchingById() {
        //Arrange
        when(edicaoDTO.getCodEdicao()).thenReturn(1);
        when(edicaoDTO.getCodUc()).thenReturn(1);
        when(edicaoDTO.getCodAnoLetivo()).thenReturn(1);

        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);


        Optional<EdicaoDTO> opEdicaoDTO = Optional.of(edicaoDTO);
        when(edicaoDTOAssembler.toDTO(opEdicao.get())).thenReturn(edicaoDTO);


        // Act
        Optional<EdicaoDTO> edicao1 = edicaoService.getEdicaoByCode(1);

        // Assert
        assertEquals(edicao1, opEdicaoDTO);
    }

    @Test
    void shouldFindEveryEdicaoCreatedCorrectly() {

        Edicao edicaoDouble = mock(Edicao.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);

        Edicao edicaoDouble2 = mock(Edicao.class);
        when(edicaoDouble2.getCodEdicao()).thenReturn(1);


        List<Edicao> listEdicaoAux = new ArrayList<>();
        listEdicaoAux.add(edicaoDouble);
        listEdicaoAux.add(edicaoDouble2);

        when(edicaoRepository.findAll()).thenReturn(listEdicaoAux);

        List<EdicaoDTO> listaDto = new ArrayList<>();
        for (Edicao edicao : listEdicaoAux) {
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);
            listaDto.add(edicaoDTO);
        }


        // Act
        List<EdicaoDTO> listEdicaoAct = edicaoService.getAllEdicao();

        // Assert
        assertEquals(listaDto, listEdicaoAct);
        assertEquals(2, listEdicaoAct.size());
    }

    @Test
    void shouldFindEveryEdicaoAllArgsCreatedCorrectly(){
        Edicao edicaoDouble = mock(Edicao.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);

        Edicao edicaoDouble2 = mock(Edicao.class);
        when(edicaoDouble2.getCodEdicao()).thenReturn(1);


        List<Edicao> listEdicaoAux = new ArrayList<>();
        listEdicaoAux.add(edicaoDouble);
        listEdicaoAux.add(edicaoDouble2);

        when(edicaoRepository.findAll()).thenReturn(listEdicaoAux);

        List<EdicaoAllArgsDTO> listaDto = new ArrayList<>();
        for (Edicao edicao : listEdicaoAux) {
            EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(edicao);
            listaDto.add(edicaoAllArgsDTO);
        }


        // Act
        List<EdicaoAllArgsDTO> listEdicaoAct = edicaoService.getEdicaoAllArgs();

        // Assert
        assertEquals(listaDto, listEdicaoAct);
        assertEquals(2, listEdicaoAct.size());
    }

    @Test
    void shouldFindSpecificEdicaoAllArgsSearchingById() {
        //Arrange
        Edicao edicao = mock(Edicao.class);
        when(edicao.getCodEdicao()).thenReturn(1);

        when(edicaoAllArgsDTO.getCodEdicao()).thenReturn(1);
        when(edicaoAllArgsDTO.getCodUc()).thenReturn(1);
        when(edicaoAllArgsDTO.getCodAnoLetivo()).thenReturn(1);

        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);


        Optional<EdicaoAllArgsDTO> opEdicaoAllArgsDTO = Optional.of(edicaoAllArgsDTO);
        when(edicaoDTOAssembler.toDTOAllArgs(opEdicao.get())).thenReturn(edicaoAllArgsDTO);


        // Act
        Optional<EdicaoAllArgsDTO> edicao1 = edicaoService.getEdicaoAllArgsByCode(1);

        // Assert
        assertEquals(edicao1, opEdicaoAllArgsDTO);
    }

    /*@Test
    void shouldUpdateEstadoEdicaoCorrectly() throws Exception {
        //Arrange
        EdicaoDTOParcial edicaoDTOParcial = mock(EdicaoDTOParcial.class);
        when(edicaoDTOParcial.getCodEdicao()).thenReturn(1);
        when(edicaoDTOParcial.getEstado()).thenReturn(String.valueOf(Edicao.Estado.PENDENTE));

        Edicao edicao = mock(Edicao.class);
        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);

        opEdicao.get().setEstado(Edicao.Estado.PENDENTE);

        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(opEdicao.get());

        //Act
        EdicaoDTO edicaoDTOAct = edicaoService.updateEstadoEdicao(edicaoDTOParcial);

        //Assert
        assertEquals(edicaoDTOAct,edicaoDTO );
    }*/

    /*@Test
    void shouldAddStudentToEdicaoAndSave() throws Exception {
        AddStudentDTO addStudentDTO = mock(AddStudentDTO.class);
        when(addStudentDTO.getCodEdicao()).thenReturn(1);
        when(addStudentDTO.getCodEstudante()).thenReturn(1);

        EdicaoJpa edicaoJpa = mock(EdicaoJpa.class);
        when(edicaoJpa.getCodEdicao()).thenReturn(addStudentDTO.getCodEdicao());
        when(edicaoRepository.findBycodEdicaoJpa(1)).thenReturn(edicaoJpa);

        EstudanteJpa estudanteJpa = mock(EstudanteJpa.class);
        when(estudanteJpa.getCodEdicao()).thenReturn(addStudentDTO.getCodEdicao());
        when(estudanteJpa.getCodEstudante()).thenReturn(addStudentDTO.getCodEstudante());

        edicaoJpa.getListEstudantes().add(estudanteJpa);

        Edicao edicao = edicaoAssembler.toDomain(edicaoJpa);
        Edicao edicaoSaved = edicaoRepository.saveWithoutValidation(edicao);
        EdicaoDTO edicaoSavedDTO = edicaoDTOAssembler.toDTO(edicaoSaved);

        //Act
        EdicaoDTO edicaoDTO = edicaoService.addEstudantes(addStudentDTO);

        //Assert
        assertEquals(edicaoSavedDTO,edicaoDTO);
    }*/

    @Test
    void shouldReturnListEdicaoAllArgsDTOByCodRUC(){
        EdicaoAllArgsDTO edicaoAllArgsDTO1 = mock(EdicaoAllArgsDTO.class);
        when(edicaoAllArgsDTO1.getCodEdicao()).thenReturn(1);
        when(edicaoAllArgsDTO1.getCodRUC()).thenReturn(1);

        EdicaoAllArgsDTO edicaoAllArgsDTO2 = mock(EdicaoAllArgsDTO.class);
        when(edicaoAllArgsDTO2.getCodEdicao()).thenReturn(1);
        when(edicaoAllArgsDTO2.getCodRUC()).thenReturn(1);

        List<Edicao> listMA = new ArrayList<>();
        when(edicaoRepository.findListEdicaoBycodRUC(1)).thenReturn(listMA);

        List<EdicaoAllArgsDTO> edicaoAllArg =new ArrayList<>();
        for (Edicao edicao : listMA) {
            edicaoAllArg.add(edicaoDTOAssembler.toDTOAllArgs(edicao));
        }

        //Act
        List<EdicaoAllArgsDTO> listEdicaoAllArgsDTO = edicaoService.getAllEdicaoByCodRUC(1);

        //Assert
        assertEquals(listEdicaoAllArgsDTO, edicaoAllArg);
    }

    @Test
    void shouldReturnEdicaoByCodEstudante() throws Exception {
        //Arrange
        EstudanteJpa estudanteJpa = mock(EstudanteJpa.class);
        when(estudanteJpa.getCodEdicao()).thenReturn(1);

        Optional<EstudanteJpa> opEstudanteJpa = Optional.of(estudanteJpa);
        when(estudanteJpaRepository.findByCodEstudante(1)).thenReturn(opEstudanteJpa);

        Edicao edicao = mock(Edicao.class);
        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);

        EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(edicao);

        //Act
        EdicaoAllArgsDTO edicaoAllArgs = edicaoService.getEdicaoByCodEstudante(1);

        //Assert
        assertEquals(edicaoAllArgs,edicaoAllArgsDTO);
    }

    @Test
    void shouldReturnEdicaoByCodRUC() throws Exception {
        //Arrange
        Edicao edicao = mock(Edicao.class);
        when(edicao.getCodEdicao()).thenReturn(1);
        when(edicao.getCodRUC()).thenReturn(1);

        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(edicaoRepository.getEdicaoByCodRUC(1)).thenReturn(opEdicao);

        EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(opEdicao.get());

        //Act
        EdicaoAllArgsDTO edicaoAllArgs = edicaoService.getEdicaoByCodRUC(1);

        //Assert
        assertEquals(edicaoAllArgs,edicaoAllArgsDTO);
    }
}