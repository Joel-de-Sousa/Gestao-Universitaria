package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.AvaliacaoPartialDTO;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.factories.AvaliacaoFactory;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
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
    void test() throws Exception {
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

  /*  @Test
    void shouldUpdateAvaliacao() throws Exception {

        AvaliacaoPartialDTO info = mock(AvaliacaoPartialDTO.class);

        Avaliacao avaliacao = mock(Avaliacao.class);

        Optional<Avaliacao> optional = Optional.of(avaliacao);

        when(avaliacaoRepository.findById(info.getCodAvaliacao())).thenReturn(optional);

        when(info.getEstado()).thenReturn("PENDENTE");

        Date data = mock(Date.class);

        when(new Date(System.currentTimeMillis())).thenReturn(data);



        optional.get().setCodAvaliacao(info.getCodAvaliacao());
        optional.get().setNota(info.getNota());
        optional.get().setJustificacao(info.getJustificacao());
        optional.get().setDate(data);

        Avaliacao savedOne = mock(Avaliacao.class);

        when(avaliacaoRepository.update(optional.get())).thenReturn(savedOne);

        AvaliacaoDTO avaliacaoDTOSavedOne = mock(AvaliacaoDTO.class);

        when(avaliacaoDomainDTOAssembler.toDto(savedOne)).thenReturn(avaliacaoDTOSavedOne);

        //act
        AvaliacaoDTO act = avaliacaoService.updateAvaliacao(info);

        //assert
        assertEquals(act,avaliacaoDTOSavedOne);
    }
*/

/*

    @Test
    void test() throws IOException {
        //arrange
        NewAvaliacaoInfoDTO newAvaliacaoInfoDTO = mock(NewAvaliacaoInfoDTO.class);

        JuriDTO juriDTO = mock(JuriDTO.class);
        when(juriService.findJuriByCode(1)).thenReturn(juriDTO);
        Juri juri = mock(Juri.class);
        when(juriDomainDTOAssembler.toDomain(juriDTO)).thenReturn(juri);

        Submissao submissao = mock(Submissao.class);
        Optional<Submissao> optionalSubmissao = Optional.of(submissao);
        when(submissaoRepository.findById(1)).thenReturn(optionalSubmissao);
        Avaliacao avaliacao = mock(Avaliacao.class);

        when(avaliacaoFactory.createAvaliacao(1,juri,submissao)).thenReturn(avaliacao);
        Avaliacao avaliacaoSaved = mock(Avaliacao.class);
        when(avaliacaoRepository.save(avaliacao)).thenReturn(avaliacaoSaved);
        AvaliacaoDTO avaliacaoDTO = mock(AvaliacaoDTO.class);
        when(avaliacaoDomainDTOAssembler.toDto(avaliacaoSaved)).thenReturn(avaliacaoDTO);


        //act
        AvaliacaoDTO avaliacaoTest = avaliacaoService.createAndSaveAvaliacao(newAvaliacaoInfoDTO);

        //assert
        assertEquals(avaliacaoTest ,avaliacaoDTO);
    }
*/


}