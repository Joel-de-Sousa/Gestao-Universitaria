package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.MomentoAvaliacaoDTO;
import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.ProjetoFactory;
import Sprint.WsProjeto.repositories.EdicaoWebRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoServiceTest {


    @MockBean
    NewProjetoInfoDto newProjetoInfoDto;

    @MockBean
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @MockBean
    PropostaWebRepository propostaWebRepository;

    @MockBean
    UtilizadorWebRepository utilizadorWebRepository;

    @MockBean
    ProjetoDTO projetoDTO;

    @MockBean
    ProjetoRepository projetoRepository;

    @MockBean
    ProjetoFactory projetoFactory;

    @MockBean
    Projeto projeto;

    @MockBean
    EdicaoWebRepository edicaoWebRepository;

    @MockBean
    AvaliacaoService avaliacaoService;

    @InjectMocks
    ProjetoService projetoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindProjetoSearchingByCode() throws SQLException {
        //ARRANGE

        Optional<Projeto> opProjecto = Optional.of(projeto);

        when(projetoRepository.findById(1)).thenReturn(opProjecto);

        ProjetoDTO projetoDTO1 = mock(ProjetoDTO.class);
        when(projetoDomainDTOAssembler.toDto(opProjecto.get())).thenReturn(projetoDTO1);

        //ACT
        ProjetoDTO projeto1 = projetoService.findProjetoByCode(1);


        //ASSERT
        assertEquals(projetoDTO1, projeto1);

    }


    @Test
    void shouldFindUtilizadorByCode() {

        //ARRANGE
        UtilizadorRestDTO utilizador = mock(UtilizadorRestDTO.class);
        Optional<UtilizadorRestDTO> utilizadorRestDTO = Optional.of(utilizador);

        when(utilizadorWebRepository.findUtilizadorByCode(1)).thenReturn(utilizadorRestDTO);


        //ACT

        Optional<UtilizadorRestDTO> optionalUtilizadorRestDTO = projetoService.findUtilizadorByCode(1);

        //ASSERT

        assertEquals(utilizadorRestDTO, optionalUtilizadorRestDTO);

    }

    @Test
    void shouldFindPropostaByCode() {

        //Arrange
        PropostaRestDTO proposta = mock(PropostaRestDTO.class);
        Optional<PropostaRestDTO> optionalRest = Optional.of(proposta);
        when(propostaWebRepository.findPropostaByCode(1)).thenReturn(optionalRest);

        //ACT
        Optional<PropostaRestDTO> optionalPropostaRestDTO = projetoService.findPropostaByCode(1);

        //Assert

        assertEquals(optionalPropostaRestDTO, optionalRest);

    }

    @Test
    void shouldNotFindPropostaByCode() {

        //Arrange
        PropostaRestDTO proposta = mock(PropostaRestDTO.class);
        Optional<PropostaRestDTO> optionalRest = Optional.of(proposta);
        when(propostaWebRepository.findPropostaByCode(1)).thenReturn(optionalRest);

        //ACT
        Optional<PropostaRestDTO> optionalPropostaRestDTO = projetoService.findPropostaByCode(2);

        //Assert

        assertNotEquals(optionalPropostaRestDTO, optionalRest);

    }

    @Test
    void shouldCreateAndSaveProjeto() throws Exception {

        NewProjetoInfoDto info = mock(NewProjetoInfoDto.class);
        PropostaRestDTO propostaRestDTO = mock(PropostaRestDTO.class);
        Optional<PropostaRestDTO> optional = Optional.of(propostaRestDTO);
        when(propostaWebRepository.findPropostaByCode(info.getCodProposta())).thenReturn(optional);
        Projeto projeto = mock(Projeto.class);
        when(projetoFactory.createProjeto(info.getCodProposta(),info.getCodEstudante())).thenReturn(projeto);
        Projeto savedOne = mock(Projeto.class);
        when(projetoRepository.save(projeto)).thenReturn(savedOne);
        EdicaoRestDTO edicaoRestDTO = mock(EdicaoRestDTO.class);
        Optional<EdicaoRestDTO> optionalEdicaoRestDTO = Optional.of(edicaoRestDTO);
        when(edicaoWebRepository.findEdicaoByCode(optional.get().getCodEdicao())).thenReturn(optionalEdicaoRestDTO);
        MomentoAvaliacaoDTO momentoAvaliacaoDTO = mock(MomentoAvaliacaoDTO.class);

        List<MomentoAvaliacaoDTO> momentoAvaliacaoDTOList = new ArrayList<>();
        momentoAvaliacaoDTOList.add(momentoAvaliacaoDTO);
        momentoAvaliacaoDTOList = optionalEdicaoRestDTO.get().getMomentoAvaliacaoList();
        Avaliacao avaliacao = mock(Avaliacao.class);
        when(avaliacaoService.createAndSaveAvaliacao(momentoAvaliacaoDTO.getCodMomentoAvaliacao(),savedOne.getCodProjeto())).thenReturn(avaliacao);
        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);
        when(projetoDomainDTOAssembler.toDto(savedOne)).thenReturn(projetoDTO);


        //act
        ProjetoDTO act = projetoService.createAndSaveProjeto(info);

        //assert
        assertEquals(act,projetoDTO);

    }


}
