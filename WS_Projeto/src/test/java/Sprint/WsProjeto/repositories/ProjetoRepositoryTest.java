package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ProjetoJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.JDBC.ProjetoJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjetoRepositoryTest {


    @MockBean
    EdicaoWebRepository edicaoWebRepository;

    @MockBean
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @MockBean
    PropostaWebRepository propostaWebRepository;

    @MockBean
    ProjetoJDBCDomainDataAssembler projetoJDBCDomainDataAssembler;

    @MockBean
    Projeto projeto;

    @MockBean
    ProjetoJDBCRepository projetoJDBCRepository;

    @InjectMocks
    ProjetoRepository projetoRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveProjeto() throws SQLException {
        //arrange
        Projeto projeto = mock(Projeto.class);
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        when(projetoJDBCDomainDataAssembler.toJDBC(projeto)).thenReturn(projetoJDBC);
        ProjetoJDBC savedOne = mock(ProjetoJDBC.class);
        when(projetoJDBCRepository.save(projetoJDBC)).thenReturn(savedOne);
        when(projetoJDBCDomainDataAssembler.toDomain(savedOne)).thenReturn(projeto);


        //act
        Projeto act = projetoRepository.save(projeto);

        //assert
        assertEquals(act,projeto);
    }
    @Test
    void shouldFindProjetoSearchingById() throws SQLException {

        //arrange
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        Optional<ProjetoJDBC> optional = Optional.of(projetoJDBC);
        when(projetoJDBCRepository.getById(projeto.getCodProjeto())).thenReturn(optional);
        Projeto projeto1 = mock(Projeto.class);
        when(projetoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(projeto1);

        //act
        Optional<Projeto> act = projetoRepository.findById(projeto.getCodProjeto());

        //assert

        assertEquals(act,Optional.of(projeto1));

    }

    @Test
    void shouldFindProjetoById() throws SQLException {
        //arrange
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        Optional<ProjetoJDBC> optional = Optional.of(projetoJDBC);
        when(projetoJDBCRepository.getById(projeto.getCodProjeto())).thenReturn(optional);

        Projeto projeto = mock(Projeto.class);
        when(projetoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(projeto);
        //act
        Optional<Projeto> act = projetoRepository.findById(projeto.getCodProjeto());

        //assert
        assertEquals(act,Optional.of(projeto));

    }
    @Test
    void shouldFindProjetoCodEstudante() throws SQLException {
        //arrange
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        Optional<ProjetoJDBC> optional = Optional.of(projetoJDBC);
        when(projetoJDBCRepository.findProjetoByCodeEstudante(projeto.getCodEstudante())).thenReturn(optional);

        Projeto projeto = mock(Projeto.class);
        when(projetoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(projeto);
        //act
        Optional<Projeto> act = projetoRepository.findByCodEstudante(projeto.getCodEstudante());

        //assert
        assertEquals(act,Optional.of(projeto));

    }

    @Test
    void shouldFindProjetosConcluidos() throws SQLException {

        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        projetoJDBCList.add(projetoJDBC);
        when(projetoJDBCRepository.findProjetosConcluidos()).thenReturn(projetoJDBCList);

        List<Projeto> listP = new ArrayList<>();
        listP.add(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC));


        //act
        List<Projeto> act = projetoRepository.findProjetosConcluidos();

        //assert
        assertEquals(act,listP);
    }

    @Test
    void shouldFindProjetosDatasAvaliacao() throws SQLException {

        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        projetoJDBCList.add(projetoJDBC);
        when(projetoJDBCRepository.findProjetosDatasAvaliacao(1,new Date(2022-1-10),new Date(2022-1-12))).thenReturn(projetoJDBCList);

        List<Projeto> listP = new ArrayList<>();
        listP.add(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC));


        //act
        List<Projeto> act = projetoRepository.findProjetosDatasAvaliacao(1,new Date(2022-1-10),new Date(2022-1-12) );

        //assert
        assertEquals(act,listP);
    }

    @Test
    void shouldFindProjetosByCodDocente() throws SQLException {

        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        projetoJDBCList.add(projetoJDBC);
        when(projetoJDBCRepository.findProjetosByCodDocente(1)).thenReturn(projetoJDBCList);

        List<Projeto> listP = new ArrayList<>();
        listP.add(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC));


        //act
        List<Projeto> act = projetoRepository.findProjetosByCodDocente(1);


        //assert
        assertEquals(act,listP);


    }

    @Test
    void shouldFindProjetosByCodProposta() throws SQLException {

        //arrange
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        Optional<ProjetoJDBC > optional = Optional.of(projetoJDBC);
        when(projetoJDBCRepository.findProjetoByCodProposta(1)).thenReturn(optional);
        Projeto projeto1 = mock(Projeto.class);
        when(projetoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(projeto1);


        //act
        Projeto act = projetoRepository.findProjetoByCodProposta(1);


        //assert
        assertEquals(act,projeto1);


    }

    @Test
    void shouldFindProjetosMACompleto() throws SQLException {

        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        projetoJDBCList.add(projetoJDBC);
        when(projetoJDBCRepository.findProjetosComDeterminadoMACompleto(1)).thenReturn(projetoJDBCList);

        List<Projeto> listP = new ArrayList<>();
        listP.add(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC));


        //act
        List<Projeto> act = projetoRepository.findProjetosComDeterminadoMACompleto(1);


        //assert
        assertEquals(act,listP);


    }


    @Test
    void shouldFindProjetosPorCodigoRuc() throws Exception {
        //arrange
        List<ProjetoDTO> listProjeto = new ArrayList<>();
        EdicaoRestDTO edicaoRestDTO = mock(EdicaoRestDTO.class);
        List<EdicaoRestDTO> listEdicoes = new ArrayList<>();
        listEdicoes.add(edicaoRestDTO);
        when(edicaoWebRepository.getListaEdicoesByCodRUC(1)).thenReturn(listEdicoes);

        PropostaRestDTO propostaRestDTO = mock(PropostaRestDTO.class);
        List<PropostaRestDTO> listPropostas = new ArrayList<>();
        listPropostas.add(propostaRestDTO);
        when(propostaWebRepository.findAllPropostasAceitesByCodEdicao(edicaoRestDTO.getCodEdicao())).thenReturn(listPropostas);

        Projeto projeto1 = mock(Projeto.class);
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        Optional<ProjetoJDBC> optional = Optional.of(projetoJDBC);

        when( projetoJDBCRepository.findProjetoByCodProposta(propostaRestDTO.getCodProposta())).thenReturn(optional);
        when(projetoJDBCDomainDataAssembler.toDomain(optional.get())).thenReturn(projeto1);


        ProjetoDTO projetoDTO = mock(ProjetoDTO.class);
        when(projetoDomainDTOAssembler.toDto(projeto1)).thenReturn(projetoDTO);
        listProjeto.add(projetoDTO);


        //act
        List<ProjetoDTO> act = projetoRepository.findProjetosPorCodigoRUC(1);

        //assert
        assertEquals(act,listProjeto);
    }
    @Test
    void listaFiltrada() throws SQLException {
        //arrange
        String query = "";
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        projetoJDBCList.add(projetoJDBC);
        when(projetoJDBCRepository.listaQuery(query)).thenReturn(projetoJDBCList);
        List<Projeto> projetoList = new ArrayList<>();

        when(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC)).thenReturn(projeto);
        projetoList.add(projeto);

        //act
        List<Projeto> act = projetoRepository.listaFiltrada(query);

        assertEquals(act,projetoList);
    }

    @Test
    void shouldFindProjetosByCodPresidente() throws SQLException {
        ProjetoJDBC projetoJDBC = mock(ProjetoJDBC.class);
        List<ProjetoJDBC> projetoJDBCList = new ArrayList<>();
        when(projetoJDBCRepository.findProjetosByCodPresidente(1)).thenReturn(projetoJDBCList);
        projetoJDBCList.add(projetoJDBC);
        List<Projeto> projetoList = new ArrayList<>();
        Projeto projeto = mock(Projeto.class);
        when(projetoJDBCDomainDataAssembler.toDomain(projetoJDBC)).thenReturn(projeto);
        projetoList.add(projeto);

        //act
        List<Projeto> act = projetoRepository.findProjetosByCodPresidente(1);

        //assert
        assertEquals(act,projetoList);
    }






}