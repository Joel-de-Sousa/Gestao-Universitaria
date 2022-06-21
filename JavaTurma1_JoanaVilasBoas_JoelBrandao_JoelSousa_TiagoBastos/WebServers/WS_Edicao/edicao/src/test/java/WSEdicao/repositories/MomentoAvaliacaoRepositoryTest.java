package WSEdicao.repositories;

import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.MomentoAvaliacaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.MomentoAvaliacaoDomainDTOAssembler;
import WSEdicao.repositories.jpa.MomentoAvaliacaoJpaRepository;
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
class MomentoAvaliacaoRepositoryTest {

    @MockBean
    MomentoAvaliacaoDomainDataAssembler momentoAvaliacaoDomainDataAssembler;

    @MockBean
    MomentoAvaliacaoDomainDTOAssembler momentoAvaliacaoDTOAssembler;

    @MockBean
    MomentoAvaliacaoJpaRepository momentoAvaliacaoJpaRepository;

    @MockBean
    MomentoAvaliacaoDTO momentoAvaliacaoDTO;

    @MockBean
    MomentoAvaliacao momentoAvaliacao;
    @MockBean
    MomentoAvaliacaoJpa momentoAvaliacaoJpa;

    @InjectMocks
    MomentoAvaliacaoRepository momentoAvaliacaoRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveMomentoAvaliacao() throws Exception {
        //Arrange
        when(momentoAvaliacaoDomainDataAssembler.toData(momentoAvaliacao)).thenReturn(momentoAvaliacaoJpa);

        MomentoAvaliacaoJpa savedMomentoAvaliacaoJpa = mock(MomentoAvaliacaoJpa.class);
        when(momentoAvaliacaoJpaRepository.save(momentoAvaliacaoJpa)).thenReturn(savedMomentoAvaliacaoJpa);

        when(momentoAvaliacaoDomainDataAssembler.toDomain(savedMomentoAvaliacaoJpa)).thenReturn(momentoAvaliacao);

        //Act
        MomentoAvaliacao savedMomentoAvaliacao = momentoAvaliacaoRepository.save(momentoAvaliacao);

        //Assert
        assertEquals(savedMomentoAvaliacao, momentoAvaliacao);
    }

    @Test
    void shouldFindSpecificMomentoAvaliacaoSearchingById() {
        //Arrange
        MomentoAvaliacaoJpa momentoAvaliacaoDouble = mock(MomentoAvaliacaoJpa.class);
        Optional<MomentoAvaliacaoJpa> opMomentoAvaliacaoDouble = Optional.of(momentoAvaliacaoDouble);

        when(momentoAvaliacaoJpaRepository.findBycodMomentoAvaliacao(1)).thenReturn(opMomentoAvaliacaoDouble);

        MomentoAvaliacaoJpa momentoAvaliacaoJpa = opMomentoAvaliacaoDouble.get();
        MomentoAvaliacao momentoAvaliacao = mock(MomentoAvaliacao.class);
        when(momentoAvaliacaoDomainDataAssembler.toDomain(momentoAvaliacaoJpa)).thenReturn(momentoAvaliacao);
        MomentoAvaliacaoDTO momentoAvaliacaoDTO = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDTOAssembler.toDTO(momentoAvaliacao)).thenReturn(momentoAvaliacaoDTO);

        //Act
        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacaoAct = momentoAvaliacaoRepository.findBycodMomentoAvaliacao(1);

        //Assert
        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = Optional.of(momentoAvaliacaoDTO);

        assertEquals(opMomentoAvaliacaoAct, opMomentoAvaliacao);
    }

    @Test
    void findAll() {
        //Arrange
        MomentoAvaliacaoJpa momentoAvaliacaoJpa1 = mock(MomentoAvaliacaoJpa.class);
        when(momentoAvaliacaoJpa1.getCodMomentoAvaliacao()).thenReturn(1);
        when(momentoAvaliacaoJpa1.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacaoJpa1.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacaoJpa momentoAvaliacaoJpa2 = mock(MomentoAvaliacaoJpa.class);
        when(momentoAvaliacaoJpa2.getCodMomentoAvaliacao()).thenReturn(1);
        when(momentoAvaliacaoJpa1.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacaoJpa2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacaoJpa> listMomentoAvaliacaoJpa = new ArrayList<>();
        listMomentoAvaliacaoJpa.add(momentoAvaliacaoJpa1);
        listMomentoAvaliacaoJpa.add(momentoAvaliacaoJpa2);

        when(momentoAvaliacaoJpaRepository.findAll()).thenReturn(listMomentoAvaliacaoJpa);

        List<MomentoAvaliacao> listMomentoAvaliacao = new ArrayList<>();
        for (MomentoAvaliacaoJpa momentoAvaliacaoJpa : listMomentoAvaliacaoJpa) {
            when(momentoAvaliacaoDomainDataAssembler.toDomain(momentoAvaliacaoJpa)).thenReturn(momentoAvaliacao);
            listMomentoAvaliacao.add(momentoAvaliacao);
        }

        //Act
        List<MomentoAvaliacao> listMomentoAvaliacaoAct = momentoAvaliacaoRepository.findAll();

        //Assert
        assertEquals(listMomentoAvaliacaoAct, listMomentoAvaliacao);
        assertTrue(listMomentoAvaliacaoAct.size() == 2);
    }

    @Test
    void shouldFindAllMomentoAvaliacaoByCodEdicao() throws Exception {
        //Arrange
        MomentoAvaliacao momentoAvaliacao = mock(MomentoAvaliacao.class);
        when(momentoAvaliacao.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacao.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacao momentoAvaliacao2 = mock(MomentoAvaliacao.class);
        when(momentoAvaliacao2.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacao2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacaoJpa> listMAJPA = new ArrayList<>();
        when(momentoAvaliacaoJpaRepository.findByCodEdicao(1)).thenReturn(listMAJPA);

        List<MomentoAvaliacao> listMA =new ArrayList<>();
        for (MomentoAvaliacaoJpa ma : listMAJPA) {
            listMA.add(momentoAvaliacaoDomainDataAssembler.toDomain(ma));
        }

        //Act
        List<MomentoAvaliacao> listMomentoAvaliacaoAct = momentoAvaliacaoRepository.findAllByCodEdicao(1);

        //Assert
        assertEquals(listMA, listMomentoAvaliacaoAct);
    }
}