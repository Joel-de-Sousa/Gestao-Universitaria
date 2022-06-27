package WSEdicao.repositories;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.AddStudentDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoRepositoryTest {

    @MockBean
    EdicaoDomainDataAssembler edicaoDomainDataAssembler;

    @MockBean
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    @MockBean
    EdicaoJpaRepository edicaoJpaRepository;

    @MockBean
    EdicaoDTO edicaoDTO;

    @MockBean
    Edicao edicao;
    @MockBean
    EdicaoJpa edicaoJpa;

    @InjectMocks
    EdicaoRepository edicaoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveEdicao() throws Exception {
        //Arrange
        when(edicaoDomainDataAssembler.toData(edicao)).thenReturn(edicaoJpa);

        EdicaoJpa savedEdicaoJpa = mock(EdicaoJpa.class);
        when(edicaoJpaRepository.save(edicaoJpa)).thenReturn(savedEdicaoJpa);

        when(edicaoDomainDataAssembler.toDomain(savedEdicaoJpa)).thenReturn(edicao);

        //Act
        Edicao savedEdicao = edicaoRepository.save(edicao);

        //Assert
        assertEquals(savedEdicao, edicao);
    }

    @Test
    void shouldFindSpecificEdicaoSearchingById() {
        //Arrange
        EdicaoJpa edicaoDouble = mock(EdicaoJpa.class);
        Optional<EdicaoJpa> opEdicaoDouble = Optional.of(edicaoDouble);

        when(edicaoJpaRepository.findBycodEdicao(1)).thenReturn(opEdicaoDouble);

        EdicaoJpa edicaoJpa = opEdicaoDouble.get();
        Edicao edicao = mock(Edicao.class);
        when(edicaoDomainDataAssembler.toDomain(edicaoJpa)).thenReturn(edicao);


        //Act
        Optional<Edicao> opEdicaoAct = edicaoRepository.findBycodEdicao(1);

        //Assert
        Optional<Edicao> opEdicao = Optional.of(edicao);

        assertEquals(opEdicaoAct, opEdicao);
    }

    @Test
    void findAll() {
        //Arrange
        EdicaoJpa edicaoJpa1 = mock(EdicaoJpa.class);
        when(edicaoJpa1.getCodEdicao()).thenReturn(1);
        when(edicaoJpa1.getCodUc()).thenReturn(1);
        when(edicaoJpa1.getCodAnoLetivo()).thenReturn(1);
        when(edicaoJpa1.getCodRUC()).thenReturn(1);

        EdicaoJpa edicaoJpa2 = mock(EdicaoJpa.class);
        when(edicaoJpa2.getCodEdicao()).thenReturn(1);
        when(edicaoJpa2.getCodUc()).thenReturn(1);
        when(edicaoJpa2.getCodAnoLetivo()).thenReturn(1);
        when(edicaoJpa2.getCodRUC()).thenReturn(1);

        List<EdicaoJpa> listEdicaoJpa = new ArrayList<>();
        listEdicaoJpa.add(edicaoJpa1);
        listEdicaoJpa.add(edicaoJpa2);

        when(edicaoJpaRepository.findAll()).thenReturn(listEdicaoJpa);

        List<Edicao> listEdicao = new ArrayList<>();
        for (EdicaoJpa edicaoJpa : listEdicaoJpa) {
            when(edicaoDomainDataAssembler.toDomain(edicaoJpa)).thenReturn(edicao);
            listEdicao.add(edicao);
        }

        //Act
        List<Edicao> listEdicaoAct = edicaoRepository.findAll();

        //Assert
        assertEquals(listEdicaoAct, listEdicao);
        assertTrue(listEdicaoAct.size() == 2);
    }

    @Test
    void shouldReturnListOfEstudantesByCodEdicao() {
        //Arrange
        AddStudentDTO addStudentDTO = mock(AddStudentDTO.class);
        EdicaoJpa edicaoJpa = mock(EdicaoJpa.class);
        //Optional<EdicaoJpa> opEdicaoJpa = edicaoJpaRepository.findBycodEdicao(codEdicao);
        when(edicaoJpaRepository.findBycodEdicao(1)).thenReturn(Optional.of(edicaoJpa));


        List<AddStudentDTO> listEstudante = new ArrayList<>();
        for (EstudanteJpa estudante : edicaoJpa.getListEstudantes()) {
            AddStudentDTO estudanteDTO = new AddStudentDTO(estudante.getCodEdicao(), estudante.getCodEstudante());
            listEstudante.add(estudanteDTO);
        }

        //Act
        List<AddStudentDTO> addStudentDTOList = edicaoRepository.findEstudantesByCodEdicao(1);

        //Assert
        assertEquals(listEstudante, addStudentDTOList);
    }

    @Test
    void shouldReturnListEdicaoByCodRUC() throws Exception {
        //Arrange
        EdicaoJpa edicaoJpa = mock(EdicaoJpa.class);
        when(edicaoJpa.getCodUc()).thenReturn(1);
        when(edicaoJpa.getCodAnoLetivo()).thenReturn(1);
        when(edicaoJpa.getCodRUC()).thenReturn(1);


        List<EdicaoJpa> listEdicaoJpa = new ArrayList<>();
        when(edicaoJpaRepository.findListEdicaoBycodRUC(1)).thenReturn(listEdicaoJpa);

        List<Edicao> newEdicaoList = new ArrayList<>();
        for (EdicaoJpa edicao : listEdicaoJpa) {
            newEdicaoList.add(edicaoDomainDataAssembler.toDomain(edicao));
        }

        //Act
        List<Edicao> listEdicao = edicaoRepository.findListEdicaoBycodRUC(1);

        //Assert
        assertEquals(listEdicao, newEdicaoList);
    }
}