package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.AvaliacaoPartialDTO;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.AvaliacaoFactory;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import Sprint.WsProjeto.repositories.EdicaoWebRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.SubmissaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class AvaliacaoServiceTest {

    @InjectMocks
    AvaliacaoService avaliacaoService;

    @MockBean
    AvaliacaoRepository avaliacaoRepository;

    @MockBean
    AvaliacaoFactory avaliacaoFactory;

    @MockBean
    JuriService juriService;

    @MockBean
    JuriDomainDTOAssembler juriDomainDTOAssembler;

    @MockBean
    SubmissaoRepository submissaoRepository;

    @MockBean
    ProjetoRepository projetoRepository;

    @MockBean
    EdicaoWebRepository edicaoWebRepository;

    @MockBean
    AvaliacaoDomainDTOAssembler avaliacaoDomainDTOAssembler;

    @BeforeEach
    public void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindAvaliacaoBycode () throws Exception {
        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        Optional<Avaliacao> optionalAvaliacao = Optional.of(avaliacao);
        when(avaliacaoRepository.findById(1)).thenReturn(optionalAvaliacao);


        AvaliacaoDTO avaliacaoDTO1 = mock(AvaliacaoDTO.class);
        when(avaliacaoDomainDTOAssembler.toDto(optionalAvaliacao.get())).thenReturn(avaliacaoDTO1);

        //act
        AvaliacaoDTO avaliacaoDTO = avaliacaoService.findAvaliacaoByCode(1);

        //assert
        assertEquals(avaliacaoDTO,avaliacaoDTO1);
    }
    @Test
    void shouldCreateAndSaveAvaliacao() throws Exception {
        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        when(avaliacaoFactory.createAvaliacao(1,2)).thenReturn(avaliacao);
        Avaliacao savedOne = mock(Avaliacao.class);
        when(avaliacaoRepository.save(avaliacao)).thenReturn(savedOne);
       //act
       Avaliacao act = avaliacaoService.createAndSaveAvaliacao(1,2);
       //arrange
        assertEquals(act,savedOne);
    }
    @Test
    void shouldFindAvaliacoesByCodProjeto() throws Exception {
        //arrange
        Avaliacao avaliacao = mock(Avaliacao.class);
        List<Avaliacao> avaliacaoList = new ArrayList<>();
        avaliacaoList.add(avaliacao);
        when(avaliacaoRepository.findAvaliacoesByCodProjeto(1)).thenReturn(avaliacaoList);
        List<AvaliacaoDTO> avaliacaoDTOList = new ArrayList<>();

        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoDomainDTOAssembler.toDto(avaliacao)).thenReturn(avaliacaoDTO);
        avaliacaoDTOList.add(avaliacaoDTO);

        //act
        List<AvaliacaoDTO> act = avaliacaoService.findAvaliacoesByCodProjeto(1);

        //assert
        assertEquals(act,avaliacaoDTOList);
    }

    @Test
    void shouldUpdateAvaliacao() throws Exception {
        //arrange
        AvaliacaoPartialDTO update = mock(AvaliacaoPartialDTO.class);
        Projeto projeto = mock(Projeto.class);
        List<Projeto> projetoList = new ArrayList<>();
        when(projetoRepository.findProjetosByCodPresidente(update.getCodPresidente())).thenReturn(projetoList);
        projetoList.add(projeto);
        Avaliacao avaliacao = mock(Avaliacao.class);
        when(avaliacao.getEstado()).thenReturn(Avaliacao.Estado.PENDENTE);

        Optional<Avaliacao> avaliacaoOptional = Optional.of(avaliacao);
        when(avaliacaoRepository.findById(update.getCodAvaliacao())).thenReturn(avaliacaoOptional);
        Avaliacao savedOne = mock(Avaliacao.class);
        when(avaliacaoRepository.update(avaliacaoOptional.get())).thenReturn(savedOne);
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoDomainDTOAssembler.toDto(savedOne)).thenReturn(avaliacaoDTO);


        //act
        AvaliacaoDTO act = avaliacaoService.updateAvaliacao(update);


        //assert

        assertEquals(act ,avaliacaoDTO);


    }

/*    @Test
    void shouldUpdateEstadoAvaliacao() throws Exception {

        AvaliacaoPartialDTO update = mock(AvaliacaoPartialDTO.class);
        List<EdicaoRestDTO> edicaoRestDTOList = new ArrayList<>();
        EdicaoRestDTO edicaoRestDTO = mock(EdicaoRestDTO.class);
        when(edicaoWebRepository.getListaEdicoesByCodRUC(update.getCodRUC())).thenReturn(edicaoRestDTOList);
        edicaoRestDTOList.add(edicaoRestDTO);


        //act
        AvaliacaoDTO act = avaliacaoService.updateEstadoAvaliacao(update);


    }*/


}