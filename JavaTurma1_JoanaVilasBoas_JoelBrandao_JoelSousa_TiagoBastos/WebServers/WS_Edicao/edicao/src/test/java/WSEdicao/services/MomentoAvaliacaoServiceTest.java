package WSEdicao.services;

import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.domain.factories.MomentoAvaliacaoFactory;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.MomentoAvaliacaoDomainDTOAssembler;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.MomentoAvaliacaoRepository;
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
class MomentoAvaliacaoServiceTest {

    @MockBean
    MomentoAvaliacaoDomainDTOAssembler momentoAvaliacaoDTOAssembler;

    @MockBean
    MomentoAvaliacaoRepository momentoAvaliacaoRepository;

    @MockBean
    MomentoAvaliacaoFactory momentoAvaliacaoFactory;

    @MockBean
    MomentoAvaliacaoDTO momentoAvaliacaoDTO;

    @MockBean
    MomentoAvaliacao momentoAvaliacao;

    @MockBean
    EdicaoRepository edicaoRepository;

    @InjectMocks
    MomentoAvaliacaoService momentoAvaliacaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void shouldCreateAMomentoAvaliacaoWithCorrectAttributes() throws Exception {
        // Arrange
        MomentoAvaliacaoDTO info = mock(MomentoAvaliacaoDTO.class);
        when(info.getCodEdicao()).thenReturn(1);
        when(info.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacao momentoAvaliacaoDouble = mock(MomentoAvaliacao.class);
        when(momentoAvaliacaoRepository.save(momentoAvaliacaoDouble)).thenReturn(momentoAvaliacaoDouble);

        when(momentoAvaliacaoFactory.createMomentoAvaliacao
                (info.getCodEdicao(),info.getDenominacao())).
                thenReturn(momentoAvaliacaoDouble);

        Edicao edicao = mock(Edicao.class);
        Optional<Edicao> opEdicao = Optional.of(edicao);
        when(opEdicao.get().getCodEdicao()).thenReturn(1);
        when(edicaoRepository.findBycodEdicao(info.getCodEdicao())).thenReturn(opEdicao);

        edicao.getMomentoAvaliacaoList().add(momentoAvaliacaoDouble);
        when(edicaoRepository.save(edicao)).thenReturn(edicao);

        MomentoAvaliacaoDTO momentoAvaliacaoDTO = mock(MomentoAvaliacaoDTO.class);
        when(momentoAvaliacaoDTOAssembler.toDTO(momentoAvaliacao)).thenReturn(momentoAvaliacaoDTO);


        // Act
        MomentoAvaliacaoDTO maDTO = momentoAvaliacaoService.createAndSaveMomentoAvaliacao(info);

        // Assert
        assertEquals(info, maDTO);
    }*/


    @Test
    void shouldFindSpecificMomentoAvaliacaoSearchingById() {
        // Arrange
        when(momentoAvaliacaoDTO.getCodMomentoAvaliacao()).thenReturn(1);
        when(momentoAvaliacaoDTO.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacaoDTO.getDenominacao()).thenReturn("Sprint1");

        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = Optional.of(momentoAvaliacaoDTO);

        when(momentoAvaliacaoRepository.findBycodMomentoAvaliacao(1)).thenReturn(opMomentoAvaliacao);

        // Act
        Optional<MomentoAvaliacaoDTO> momentoAvaliacao1 = momentoAvaliacaoService.getMomentoAvaliacaoByCode(1);
        int codEdicao = momentoAvaliacao1.get().getCodEdicao();
        String sDenominacao = momentoAvaliacao1.get().getDenominacao();

        // Assert
        assertEquals(momentoAvaliacao1, opMomentoAvaliacao);

        assertEquals(codEdicao, 1);
        assertEquals(sDenominacao, "Sprint1");
    }

    @Test
    void shouldFindEveryMomentoAvaliacaoCreatedCorrectly() {

        MomentoAvaliacao momentoAvaliacaoDouble = mock(MomentoAvaliacao.class);
        when(momentoAvaliacaoDouble.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacaoDouble.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacao momentoAvaliacaoDouble2 = mock(MomentoAvaliacao.class);
        when(momentoAvaliacaoDouble2.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacaoDouble2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacao> listMomentoAvaliacaoAux = new ArrayList<>();
        listMomentoAvaliacaoAux.add(momentoAvaliacaoDouble);
        listMomentoAvaliacaoAux.add(momentoAvaliacaoDouble2);

        when(momentoAvaliacaoRepository.findAll()).thenReturn(listMomentoAvaliacaoAux);

        List<MomentoAvaliacaoDTO> listaDto=new ArrayList<>();
        for (MomentoAvaliacao momentoAvaliacao:listMomentoAvaliacaoAux) {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = momentoAvaliacaoDTOAssembler.toDTO(momentoAvaliacao);
            listaDto.add(momentoAvaliacaoDTO);
        }

        // Act
        List<MomentoAvaliacaoDTO> listMomentoAvaliacaoAct = momentoAvaliacaoService.getAllMomentoAvaliacao();

        // Assert
        assertEquals(listaDto, listMomentoAvaliacaoAct);
        assertEquals(2, listMomentoAvaliacaoAct.size());
    }

    @Test
    void shouldFindAllMomentoAvaliacaoByCodEdicao(){
        //Arrange
        MomentoAvaliacao momentoAvaliacao = mock(MomentoAvaliacao.class);
        when(momentoAvaliacao.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacao.getDenominacao()).thenReturn("Sprint1");

        MomentoAvaliacao momentoAvaliacao2 = mock(MomentoAvaliacao.class);
        when(momentoAvaliacao2.getCodEdicao()).thenReturn(1);
        when(momentoAvaliacao2.getDenominacao()).thenReturn("Sprint2");

        List<MomentoAvaliacao> listMA = new ArrayList<>();
        when(momentoAvaliacaoRepository.findAllByCodEdicao(1)).thenReturn(listMA);

        List<MomentoAvaliacaoDTO> listaMADTO = new ArrayList<>();
        for (MomentoAvaliacao ma : listMA) {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = momentoAvaliacaoDTOAssembler.toDTO(ma);
            listaMADTO.add(momentoAvaliacaoDTO);
        }

        //Act
        List<MomentoAvaliacaoDTO> listMomentoAvaliacaoAct = momentoAvaliacaoService.getAllMomentoAvaliacaoByCodEdicao(1);
    }
}