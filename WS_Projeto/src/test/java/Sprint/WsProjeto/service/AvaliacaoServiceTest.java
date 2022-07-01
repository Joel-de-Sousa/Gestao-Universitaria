package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
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