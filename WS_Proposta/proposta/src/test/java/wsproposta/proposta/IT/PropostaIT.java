package wsproposta.proposta.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
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

import static org.apache.commons.lang3.RandomStringUtils.*;

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


    // Este teste verifica que proposta/{generatedCode} não existe inicialmente.
    // Depois, cria proposta {"code": ?generatedCode?, "name": ?generatedName?}.
    // Finalmente, verifica que proposta/{generatedCode} passa a existir.
    // NB: Depois de executados todos os testes, é feito o rollback à base de dados, removendo os dados criados durante os testes.

    @Test
    void shouldReturnNewPropostaAndOk() throws Exception {

        int generatedCodProposta = 1; //Integer.parseInt(RandomStringUtils.randomNumeric(4));

        UtilizadorRestDTO utilizadorDouble = mock(UtilizadorRestDTO.class);
        when(utilizadorDouble.getCodUtilizador()).thenReturn(1);//Integer.parseInt(RandomStringUtils.randomNumeric(8));

        OrganizacaoRestDTO organizacaoDouble = mock(OrganizacaoRestDTO.class);
        when(organizacaoDouble.getNr()).thenReturn(257837248L);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedTitulo = RandomStringUtils.randomAlphanumeric(20);
        String generatedProblema = RandomStringUtils.randomAlphanumeric(20);
        String generatedObjectivo = RandomStringUtils.randomAlphanumeric(20);
        String generatedEstado = String.valueOf(Proposta.Estado.PENDENTE);

        NewPropostaInfoDTO newPropostaInfoDTO = new NewPropostaInfoDTO(utilizadorDouble.getCodUtilizador(), (int) organizacaoDouble.getNr(), generatedCodEdicao,
                generatedTitulo, generatedProblema, generatedObjectivo);


        Optional<UtilizadorRestDTO> opUtilizador = Optional.of(utilizadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCodUtilizador(1)).thenReturn(opUtilizador);

        Optional<OrganizacaoRestDTO> opOrganizacao = Optional.of(organizacaoDouble);
        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(257837248L)).thenReturn(opOrganizacao);


        Map<String, Object> newPropostaInfoMap = new HashMap<String, Object>();
        newPropostaInfoMap.put("codProposta", 1);
        newPropostaInfoMap.put("codUtilizador", utilizadorDouble);
        newPropostaInfoMap.put("nifOrganizacao", organizacaoDouble);
        newPropostaInfoMap.put("codEdicao", generatedCodEdicao);
        newPropostaInfoMap.put("titulo", generatedTitulo);
        newPropostaInfoMap.put("problema", generatedProblema);
        newPropostaInfoMap.put("objetivo", generatedObjectivo);
        newPropostaInfoMap.put("estado", generatedEstado);


        //.................................................................
        // First call: GET country/{generatedCode}

        MvcResult result1 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/propostas/" + generatedCodProposta)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent1 = result1.getResponse().getContentAsString();
        assertNotNull(resultContent1);
        assertEquals("", resultContent1);


        // Second call: POST country [{"code":?generatedCode?, "name":?generatedName?}]

        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/propostas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newPropostaInfoDTO)) // or newCountryInfoMap
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String resultContent = result2.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals(objectMapper.writeValueAsString(newPropostaInfoMap), resultContent); // or newCountryInfoMap


        // Third call: GET country/{generatedCode}

        MvcResult result3 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/propostas/" + generatedCodProposta)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent3 = result3.getResponse().getContentAsString();
        assertNotNull(resultContent3);
        assertEquals(objectMapper.writeValueAsString(newPropostaInfoMap), resultContent3); // or newCountryInfoMap
    }

        // Este teste não funciona pois (previsivelmente) não existe country/{generatedCode} na BD.
        // NB: generatedCode é gerado neste método e (previsivelmente) diferente do gerado no método anterior.
/*@Test
    void shouldReturnCountryAndOk() throws Exception {

        String generatedCode = RandomStringUtils.randomAlphanumeric(10);
        String generatedName = RandomStringUtils.randomAlphanumeric(20);

        NewCountryInfoDTO newCountryInfoDTO = new NewCountryInfoDTO(generatedCode, generatedName);

        MvcResult result = mockMvc
                                .perform(MockMvcRequestBuilders.get("/country/" + generatedCode)
                                .accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals(objectMapper.writeValueAsString(newCountryInfoDTO), resultContent);
    }*/


}


