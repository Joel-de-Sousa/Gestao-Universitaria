package wsproposta.proposta.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;
import wsproposta.proposta.services.PropostaService;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class PropostaIT {

    @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    PropostaService propostaService;
    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;
    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    void shouldPostNewPropostaIT() throws Exception {

        UtilizadorRestDTO utilizadorDouble = mock(UtilizadorRestDTO.class);
        when(utilizadorDouble.getCodUtilizador()).thenReturn(1);

        OrganizacaoRestDTO organizacaoDouble = mock(OrganizacaoRestDTO.class);
        when(organizacaoDouble.getNr()).thenReturn(257837248L);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedTitulo = RandomStringUtils.randomAlphanumeric(20);
        String generatedProblema = RandomStringUtils.randomAlphanumeric(20);
        String generatedObjectivo = RandomStringUtils.randomAlphanumeric(20);

        NewPropostaInfoDTO newPropostaInfoDTO = new NewPropostaInfoDTO(utilizadorDouble.getCodUtilizador(),
                (int) organizacaoDouble.getNr(), generatedCodEdicao, generatedTitulo,
                generatedProblema, generatedObjectivo);


        Optional<UtilizadorRestDTO> opUtilizador = Optional.of(utilizadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizador);
        Optional<OrganizacaoRestDTO> opOrganizacao = Optional.of(organizacaoDouble);
        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(257837248L)).thenReturn(opOrganizacao);


        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                .post("/propostas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newPropostaInfoDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );

        int codUtilizador = newPropostaInfoDTO.getCodUtilizador();
        int nif = newPropostaInfoDTO.getNifOrganizacao();
        int codEdicao = newPropostaInfoDTO.getCodEdicao();
        String titulo = newPropostaInfoDTO.getTitulo();
        String problema = newPropostaInfoDTO.getProblema();
        String objetivo = newPropostaInfoDTO.getObjetivo();
        String estado = String.valueOf(Proposta.Estado.PENDENTE);

        assertEquals(codUtilizador, resultJsonObject.getInt("codUtilizador"));
        assertEquals(nif, resultJsonObject.getInt("nifOrganizacao"));
        assertEquals(codEdicao, resultJsonObject.getInt("codEdicao"));
        assertEquals(titulo , resultJsonObject.get("titulo"));
        assertEquals(problema , resultJsonObject.get("problema"));
        assertEquals(objetivo , resultJsonObject.get("objetivo"));
        assertEquals(estado , resultJsonObject.get("estado"));


        // GET Proposta/{codProposta = 1}

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                .get("/propostas/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        int codUtilizador3 = newPropostaInfoDTO.getCodUtilizador();
        int nif3 = newPropostaInfoDTO.getNifOrganizacao();
        int codEdicao3 = newPropostaInfoDTO.getCodEdicao();
        String titulo3 = newPropostaInfoDTO.getTitulo();
        String problema3 = newPropostaInfoDTO.getProblema();
        String objetivo3 = newPropostaInfoDTO.getObjetivo();
        String estado3 = String.valueOf(Proposta.Estado.PENDENTE);


        assertEquals(codUtilizador3, resultJsonObject3.getInt("codUtilizador"));
        assertEquals(nif3, resultJsonObject3.getInt("nifOrganizacao"));
        assertEquals(codEdicao3, resultJsonObject3.getInt("codEdicao"));
        assertEquals(titulo3 , resultJsonObject3.get("titulo"));
        assertEquals(problema3 , resultJsonObject3.get("problema"));
        assertEquals(objetivo3 , resultJsonObject3.get("objetivo"));
        assertEquals(estado3 , resultJsonObject3.get("estado"));

        assertNotNull(resultContentStr3);
    }
}


