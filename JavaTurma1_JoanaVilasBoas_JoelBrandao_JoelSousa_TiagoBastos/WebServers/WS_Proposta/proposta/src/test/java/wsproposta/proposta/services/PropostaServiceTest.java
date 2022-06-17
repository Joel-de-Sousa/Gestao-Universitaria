package wsproposta.proposta.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.DTO.PropostaDTOParcial;
import wsproposta.proposta.DTO.assemblers.PropostaDomainDTOAssembler;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.domain.factories.PropostaFactory;
import wsproposta.proposta.repositories.ProjetoWebRepository;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.iRepositories.IOrganizacaoWebRepository;
import wsproposta.proposta.repositories.iRepositories.IUtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class PropostaServiceTest {


    @MockBean
    ProjetoWebRepository projetoWebRepository;

    @MockBean
    PropostaRepository propostaRepository;
    @MockBean
    PropostaFactory propostaFactory;
    @MockBean
    PropostaDomainDTOAssembler propostaAssembler;
    @MockBean
    IUtilizadorWebRepository utilizadorWebRepository;
    @MockBean
    IOrganizacaoWebRepository organizacaoWebRepository;
    @InjectMocks
    PropostaService propostaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Teste createAndSave Proposta")
    void shouldCreateAndSavePropostaWithCorrectAttributes() throws Exception {

        // Arrange
        NewPropostaInfoDTO propostaInfoDtoDouble = mock(NewPropostaInfoDTO.class);

        UtilizadorRestDTO utilizadorRestDTO = mock(UtilizadorRestDTO.class);
        Optional<UtilizadorRestDTO> opUtilizadorRestDtoDouble = Optional.of(utilizadorRestDTO);

        OrganizacaoRestDTO organizacaoRestDTO = mock(OrganizacaoRestDTO.class);
        Optional<OrganizacaoRestDTO> opOrganizacaoRestDtoDouble = Optional.of(organizacaoRestDTO);

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaSavedDouble = mock(Proposta.class);
        PropostaDTO propostaDTODouble = mock(PropostaDTO.class);

        when(utilizadorWebRepository.findUtilizadorByCodUtilizador(propostaInfoDtoDouble.getCodUtilizador())).thenReturn(opUtilizadorRestDtoDouble);
        when(organizacaoWebRepository.findOrganizacaoByNifOrganizacao(propostaInfoDtoDouble.getNifOrganizacao())).thenReturn(opOrganizacaoRestDtoDouble);

        when(propostaFactory.createProposta(propostaInfoDtoDouble.getCodUtilizador(), propostaInfoDtoDouble.getNifOrganizacao(),
                        propostaInfoDtoDouble.getCodEdicao(), propostaInfoDtoDouble.getTitulo(), propostaInfoDtoDouble.getProblema(),
                        propostaInfoDtoDouble.getObjetivo())).thenReturn(propostaDouble);

        when(propostaRepository.save(propostaDouble)).thenReturn(propostaSavedDouble);

        when(propostaAssembler.toDTO(propostaSavedDouble)).thenReturn(propostaDTODouble);


        // Act
        PropostaDTO propostaDTO = propostaService.createAndSaveProposta(propostaInfoDtoDouble);

        // Assert
        assertEquals(propostaDTODouble, propostaDTO);
    }
    @Test
    @DisplayName("Teste Get All Proposta ")
    void shouldGetAllPropostasWithCorrectAttributes() {

        // Arrange

        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);

        List<Proposta> list = new ArrayList<>();
        list.add(propostaDouble);
        list.add(propostaDouble2);

        when( propostaRepository.findAll()).thenReturn(list);

        PropostaDTO propostaDtoDouble = mock(PropostaDTO.class);
        PropostaDTO propostaDtoDouble2 = mock(PropostaDTO.class);

        when(propostaAssembler.toDTO(propostaDouble)).thenReturn(propostaDtoDouble);
        when(propostaAssembler.toDTO(propostaDouble2)).thenReturn(propostaDtoDouble2);

        List<PropostaDTO> listDto = new ArrayList<>();
        listDto.add(propostaDtoDouble);
        listDto.add(propostaDtoDouble2);

        // Act
        List<PropostaDTO> listPropostasDto = propostaService.findAll();

        // Assert
        assertEquals(2,  listPropostasDto.size());
        assertEquals(listDto,  listPropostasDto);
    }

    @Test
    @DisplayName("Teste Get Proposta by CodProposta")
    void shouldGetPropostaByCodPropostaWithCorrectAttributes() {

        // Arrange
        Proposta propostaDouble = mock(Proposta.class);
        Optional<Proposta> opPropostaDouble = Optional.of(propostaDouble);

        when(propostaRepository.findById(1)).thenReturn(opPropostaDouble);

        Proposta proposta1 = opPropostaDouble.get();
        PropostaDTO propostaDTODouble = mock(PropostaDTO.class);
        when(propostaAssembler.toDTO(proposta1)).thenReturn(propostaDTODouble);


        // Act
        Optional<PropostaDTO> opPropostaDTO = propostaService.getPropostaById(1);

        // Assert
        Optional<PropostaDTO> opPropostaDTODouble = Optional.of(propostaDTODouble);
        assertEquals(opPropostaDTODouble, opPropostaDTO);
    }

    @Test
    @DisplayName("Teste Get All Proposta By CodUtilizador")
    void shouldGetAllPropostasByCodUtilizadorWithCorrectAttributes() {

        // Arrange
        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);

        List<Proposta> list = new ArrayList<>();
        list.add(propostaDouble);
        list.add(propostaDouble2);

        when( propostaRepository.findAllByCodUtilizador(1)).thenReturn(list);

        PropostaDTO propostaDtoDouble = mock(PropostaDTO.class);
        PropostaDTO propostaDtoDouble2 = mock(PropostaDTO.class);

        when(propostaAssembler.toDTO(propostaDouble)).thenReturn(propostaDtoDouble);
        when(propostaAssembler.toDTO(propostaDouble2)).thenReturn(propostaDtoDouble2);

        List<PropostaDTO> listDto = new ArrayList<>();
        listDto.add(propostaDtoDouble);
        listDto.add(propostaDtoDouble2);

        // Act
        List<PropostaDTO> listPropostasDtoFiltrada = propostaService.findAllByCodUtilizador(1);

        // Assert
        assertEquals(2,  listPropostasDtoFiltrada.size());
        assertEquals(listDto,  listPropostasDtoFiltrada);
    }

    @Test
    @DisplayName("Teste Get lista Proposta Filtrada By NIF")
    void shouldGetAllPropostasByNifOrganizacaoWithCorrectAttributes() {

        // Arrange
        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);

        List<Proposta> list = new ArrayList<>();
        list.add(propostaDouble);
        list.add(propostaDouble2);

        when( propostaRepository.findAllByNifOrganizacao(257837248)).thenReturn(list);

        PropostaDTO propostaDtoDouble = mock(PropostaDTO.class);
        PropostaDTO propostaDtoDouble2 = mock(PropostaDTO.class);

        when(propostaAssembler.toDTO(propostaDouble)).thenReturn(propostaDtoDouble);
        when(propostaAssembler.toDTO(propostaDouble2)).thenReturn(propostaDtoDouble2);

        List<PropostaDTO> listDto = new ArrayList<>();
        listDto.add(propostaDtoDouble);
        listDto.add(propostaDtoDouble2);

        // Act
        List<PropostaDTO> listPropostasDtoFiltrada = propostaService.findAllPropostasByNifOrganizacao(257837248);

        // Assert
        assertEquals(2,  listPropostasDtoFiltrada.size());
        assertEquals(listDto,  listPropostasDtoFiltrada);
    }
    @Test
    @DisplayName("Teste Get lista Proposta Filtrada By Titulo")
    void shouldGetAllPropostasByTituloWithCorrectAttributes() {

        // Arrange
        Proposta propostaDouble = mock(Proposta.class);
        Proposta propostaDouble2 = mock(Proposta.class);

        List<Proposta> list = new ArrayList<>();
        list.add(propostaDouble);
        list.add(propostaDouble2);

        when( propostaRepository.findAllByTitulo("WS")).thenReturn(list);

        PropostaDTO propostaDtoDouble = mock(PropostaDTO.class);
        PropostaDTO propostaDtoDouble2 = mock(PropostaDTO.class);

        when(propostaAssembler.toDTO(propostaDouble)).thenReturn(propostaDtoDouble);
        when(propostaAssembler.toDTO(propostaDouble2)).thenReturn(propostaDtoDouble2);

        List<PropostaDTO> listDto = new ArrayList<>();
        listDto.add(propostaDtoDouble);
        listDto.add(propostaDtoDouble2);

        // Act
        List<PropostaDTO> listPropostasDtoFiltrada = propostaService.findAllPropostasByTitulo("WS");

        // Assert
        assertEquals(2,  listPropostasDtoFiltrada.size());
        assertEquals(listDto,  listPropostasDtoFiltrada);
    }


   @Test
    @DisplayName("Teste Get Update Estado Proposta")
    void shouldUpdateEstadoPropostasWithCorrectAttributes() throws Exception {

        // Arrange
        PropostaDTOParcial dtoParcialDouble = mock(PropostaDTOParcial.class);
        when(dtoParcialDouble.getCodProposta()).thenReturn(1);
        when(dtoParcialDouble.getEstado()).thenReturn("ACEITE");

        Proposta propostaDouble = mock(Proposta.class);
        Optional<Proposta> opPropostaDouble = Optional.of(propostaDouble);

        when( propostaRepository.findById(1)).thenReturn(opPropostaDouble);


        PropostaDTO propostaDtoDouble = mock(PropostaDTO.class);
        Proposta propostaDouble2 = mock(Proposta.class);
        when(propostaRepository.save(opPropostaDouble.get())).thenReturn(propostaDouble2);
        when(propostaAssembler.toDTO(propostaDouble2)).thenReturn(propostaDtoDouble);

       ProjetoRestDto projetoRestDto = mock(ProjetoRestDto.class);
       when(projetoRestDto.getCodEstudante()).thenReturn(1);
       when(projetoRestDto.getCodProposta()).thenReturn(1);
       when(projetoRestDto.getCodProjeto()).thenReturn(1);
       when(projetoRestDto.getCodOrientador()).thenReturn(1);
       when(projetoWebRepository.createAndSaveProjeto(projetoRestDto)).thenReturn(true);


        // Act
        PropostaDTO propostaUpdated = propostaService.updateEstadoProposta(dtoParcialDouble,1);

        // Assert
        assertEquals(propostaDtoDouble,  propostaUpdated);
        assertEquals(propostaDtoDouble.getEstado(),  propostaUpdated.getEstado());

    }
}