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
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PropostaIT {
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

        int generatedCodProposta = 1;

        UtilizadorRestDTO utilizadorDouble = mock(UtilizadorRestDTO.class);
        when(utilizadorDouble.getCodUtilizador()).thenReturn(1);

        OrganizacaoRestDTO organizacaoDouble = mock(OrganizacaoRestDTO.class);
        when(organizacaoDouble.getNr()).thenReturn(257837248L);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedTitulo = RandomStringUtils.randomAlphanumeric(20);
        String generatedProblema = RandomStringUtils.randomAlphanumeric(20);
        String generatedObjectivo = RandomStringUtils.randomAlphanumeric(20);
        String generatedEstado = String.valueOf(Proposta.Estado.PENDENTE);

        NewPropostaInfoDTO newPropostaInfoDTO = new NewPropostaInfoDTO(utilizadorDouble.getCodUtilizador(),
                (int) organizacaoDouble.getNr(), generatedCodEdicao,
                generatedTitulo, generatedProblema, generatedObjectivo);


        Optional<UtilizadorRestDTO> opUtilizador = Optional.of(utilizadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizador);

        Optional<OrganizacaoRestDTO> opOrganizacao = Optional.of(organizacaoDouble);
        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(257837248L)).thenReturn(opOrganizacao);


        Map<String, Object> newPropostaInfoMap = new HashMap<String, Object>();
        //newPropostaInfoMap.put("codProposta", 1);
        newPropostaInfoMap.put("codUtilizador", utilizadorDouble.getCodUtilizador());
        newPropostaInfoMap.put("nifOrganizacao", (int) organizacaoDouble.getNr());
        newPropostaInfoMap.put("codEdicao", generatedCodEdicao);
        newPropostaInfoMap.put("titulo", generatedTitulo);
        newPropostaInfoMap.put("problema", generatedProblema);
        newPropostaInfoMap.put("objetivo", generatedObjectivo);
        newPropostaInfoMap.put("estado", generatedEstado);


        //.................................................................
        // First call: GET proposta/{generatedCodProposta}

        /*MvcResult result1 = mockMvc
                .perform(MockMvcRequestBuilders
                .get("/propostas/" + generatedCodProposta)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContentStr1 = result1.getResponse().getContentAsString();
        //assertNotNull(resultContentStr1);
        assertEquals("O codigo da proposta nao consta na Base de Dados", resultContentStr1);*/


        // Second call: POST p [{"code":?generatedCode?, "name":?generatedName?}]

        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders
                .post("/propostas")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newPropostaInfoDTO)) // or newCountryInfoMap
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        //String newObjectAsString = objectMapper.writeValueAsString(newPropostaInfoMap);

        String resultContentStr = result2.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );


        Integer codUtilizador = (Integer) newPropostaInfoMap.get("codUtilizador");
        Integer nif = (Integer) newPropostaInfoMap.get("nifOrganizacao");
        Integer codEdicao = (Integer) newPropostaInfoMap.get("codEdicao");
        String titulo = (String) newPropostaInfoMap.get("titulo");
        String problema = (String) newPropostaInfoMap.get("problema");
        String objetivo = (String) newPropostaInfoMap.get("objetivo");
        String estado = (String) newPropostaInfoMap.get("estado");

        assertEquals(codUtilizador.intValue(), resultJsonObject.getInt("codUtilizador"));
        assertEquals(nif.intValue(), resultJsonObject.getInt("nifOrganizacao"));
        assertEquals(codEdicao.intValue(), resultJsonObject.getInt("codEdicao"));
        assertEquals(titulo , resultJsonObject.get("titulo"));
        assertEquals(problema , resultJsonObject.get("problema"));
        assertEquals(objetivo , resultJsonObject.get("objetivo"));
        assertEquals(estado , resultJsonObject.get("estado"));


        // Third call: GET country/{generatedCode}
        //Integer codPropostaJson = resultJsonObject.getInt("codProposta");
        MvcResult result3 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/propostas/" + generatedCodProposta)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = result3.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        Integer codUtilizador3 = (Integer) newPropostaInfoMap.get("codUtilizador");
        Integer nif3 = (Integer) newPropostaInfoMap.get("nifOrganizacao");
        Integer codEdicao3 = (Integer) newPropostaInfoMap.get("codEdicao");
        String titulo3 = (String) newPropostaInfoMap.get("titulo");
        String problema3 = (String) newPropostaInfoMap.get("problema");
        String objetivo3 = (String) newPropostaInfoMap.get("objetivo");
        String estado3 = (String) newPropostaInfoMap.get("estado");

        assertEquals(codUtilizador3.intValue(), resultJsonObject3.getInt("codUtilizador"));
        assertEquals(nif3.intValue(), resultJsonObject3.getInt("nifOrganizacao"));
        assertEquals(codEdicao3.intValue(), resultJsonObject3.getInt("codEdicao"));
        assertEquals(titulo3 , resultJsonObject3.get("titulo"));
        assertEquals(problema3 , resultJsonObject3.get("problema"));
        assertEquals(objetivo3 , resultJsonObject3.get("objetivo"));
        assertEquals(estado3 , resultJsonObject3.get("estado"));

        assertNotNull(resultContentStr3);
       // assertEquals(objectMapper.writeValueAsString(newPropostaInfoMap), resultContent3); // or newCountryInfoMap
    }


    @Test
    void shouldGetPropostaByIdUtilizadorIT() throws Exception {

        int generatedCodProposta = 1;
        int generatedCodUtilizador = Integer.parseInt(RandomStringUtils.randomNumeric(4));

        UtilizadorRestDTO utilizadorDouble = mock(UtilizadorRestDTO.class);
        when(utilizadorDouble.getCodUtilizador()).thenReturn(generatedCodUtilizador);

        int generatedNifOrganizacao = Integer.parseInt(RandomStringUtils.randomNumeric(9));

        OrganizacaoRestDTO organizacaoDouble = mock(OrganizacaoRestDTO.class);
        when(organizacaoDouble.getNr()).thenReturn((long) generatedNifOrganizacao);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedTitulo = RandomStringUtils.randomAlphanumeric(20);
        String generatedProblema = RandomStringUtils.randomAlphanumeric(20);
        String generatedObjectivo = RandomStringUtils.randomAlphanumeric(20);
        String generatedEstado = String.valueOf(Proposta.Estado.PENDENTE);

        NewPropostaInfoDTO newPropostaInfoDTO = new NewPropostaInfoDTO(utilizadorDouble.getCodUtilizador(),
                (int) organizacaoDouble.getNr(), generatedCodEdicao,
                generatedTitulo, generatedProblema, generatedObjectivo);


        Optional<UtilizadorRestDTO> opUtilizador = Optional.of(utilizadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(generatedCodUtilizador)).thenReturn(opUtilizador);

        Optional<OrganizacaoRestDTO> opOrganizacao = Optional.of(organizacaoDouble);
        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(generatedNifOrganizacao)).thenReturn(opOrganizacao);


        /*Map<String, Object> newPropostaInfoMap = new HashMap<String, Object>();
        //newPropostaInfoMap.put("codProposta", 1);
        newPropostaInfoMap.put("codUtilizador", utilizadorDouble.getCodUtilizador());
        newPropostaInfoMap.put("nifOrganizacao", (int) organizacaoDouble.getNr());
        newPropostaInfoMap.put("codEdicao", generatedCodEdicao);
        newPropostaInfoMap.put("titulo", generatedTitulo);
        newPropostaInfoMap.put("problema", generatedProblema);
        newPropostaInfoMap.put("objetivo", generatedObjectivo);
        newPropostaInfoMap.put("estado", generatedEstado);*/


        //.................................................................
        // First call: GET proposta/{generatedCodProposta}

        /*MvcResult result1 = mockMvc
                .perform(MockMvcRequestBuilders
                .get("/propostas/" + generatedCodProposta)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContentStr1 = result1.getResponse().getContentAsString();
        //assertNotNull(resultContentStr1);
        assertEquals("O codigo da proposta nao consta na Base de Dados", resultContentStr1);*/


        // Second call: POST p [{"code":?generatedCode?, "name":?generatedName?}]

        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/propostas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newPropostaInfoDTO)) // or newCountryInfoMap
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        //String newObjectAsString = objectMapper.writeValueAsString(newPropostaInfoMap);

        String resultContentStr = result2.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );


        Integer codUtilizador = (Integer) newPropostaInfoDTO.getCodUtilizador();
        /*Integer nif = (Integer) newPropostaInfoMap.get("nifOrganizacao");
        Integer codEdicao = (Integer) newPropostaInfoMap.get("codEdicao");
        String titulo = (String) newPropostaInfoMap.get("titulo");
        String problema = (String) newPropostaInfoMap.get("problema");
        String objetivo = (String) newPropostaInfoMap.get("objetivo");
        String estado = (String) newPropostaInfoMap.get("estado");*/

        Integer codUtilizadorJson = resultJsonObject.getInt("codUtilizador");

        assertEquals(codUtilizador.intValue(), codUtilizadorJson);
        /*assertEquals(nif.intValue(), resultJsonObject.getInt("nifOrganizacao"));
        assertEquals(codEdicao.intValue(), resultJsonObject.getInt("codEdicao"));
        assertEquals(titulo , resultJsonObject.get("titulo"));
        assertEquals(problema , resultJsonObject.get("problema"));
        assertEquals(objetivo , resultJsonObject.get("objetivo"));
        assertEquals(estado , resultJsonObject.get("estado"));*/


        // Third call: GET country/{generatedCode}
        //Integer codPropostaJson = resultJsonObject.getInt("codProposta");
        MvcResult result3 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/propostas/titulo/" + codUtilizadorJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = result3.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );


        //assertEquals(resultJsonObject, resultJsonObject3);
        //Integer codUtilizador3 = (Integer) newPropostaInfoDTO.getCodUtilizador();
        /*Integer nif3 = (Integer) newPropostaInfoMap.get("nifOrganizacao");
        Integer codEdicao3 = (Integer) newPropostaInfoMap.get("codEdicao");
        String titulo3 = (String) newPropostaInfoMap.get("titulo");
        String problema3 = (String) newPropostaInfoMap.get("problema");
        String objetivo3 = (String) newPropostaInfoMap.get("objetivo");
        String estado3 = (String) newPropostaInfoMap.get("estado");*/

        //assertEquals(codUtilizador3.intValue(), resultJsonObject3.getInt("codUtilizador"));
        /*assertEquals(nif3.intValue(), resultJsonObject3.getInt("nifOrganizacao"));
        assertEquals(codEdicao3.intValue(), resultJsonObject3.getInt("codEdicao"));
        assertEquals(titulo3 , resultJsonObject3.get("titulo"));
        assertEquals(problema3 , resultJsonObject3.get("problema"));
        assertEquals(objetivo3 , resultJsonObject3.get("objetivo"));
        assertEquals(estado3 , resultJsonObject3.get("estado"));*/

        assertNotNull(resultContentStr3);
        // assertEquals(objectMapper.writeValueAsString(newPropostaInfoMap), resultContent3); // or newCountryInfoMap
    }
}


