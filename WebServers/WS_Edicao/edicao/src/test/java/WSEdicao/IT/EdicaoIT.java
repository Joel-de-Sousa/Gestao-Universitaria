package WSEdicao.IT;
/*
import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.NewEdicaoInfoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.services.EdicaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.commons.lang3.RandomStringUtils;

@AutoConfigureMockMvc
@SpringBootTest
public class EdicaoIT {

    @Autowired
    EdicaoRepository edicaoRepository;
    @Autowired
    EdicaoService edicaoService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPostNewEdicaoAndOK() throws Exception {

        UcDTO ucDTO = mock(UcDTO.class);
        when(ucDTO.getCodUc()).thenReturn(1);

        AnoLetivoDTO anoLetivoDTO = mock(AnoLetivoDTO.class);
        when(anoLetivoDTO.getCodAnoLetivo()).thenReturn(1);

        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodUc = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodAnoLetivo = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodRUC = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        String generatedDenominacao = RandomStringUtils.randomAlphabetic(10,20);

        NewEdicaoInfoDTO newEdicaoInfoDTO = new NewEdicaoInfoDTO(generatedCodEdicao,generatedCodUc,
                generatedCodAnoLetivo,generatedCodRUC);

        // GET edicao/{generatedCode}

        MvcResult result1 = mockMvc
                .perform(MockMvcRequestBuilders.get("/edicao/" + generatedCodEdicao)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent1 = result1.getResponse().getContentAsString();

        String msgErro = "O codigo da Edição não consta na Base de Dados";
        assertEquals(msgErro, resultContent1);


        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/edicao")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newEdicaoInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject(resultContentStr);

        int codEdicao = newEdicaoInfoDTO.getCodEdicao();
        int codUc = newEdicaoInfoDTO.getCodUc();
        int codAnoLetivo = newEdicaoInfoDTO.getCodAnoLetivo();
        int codRUC = newEdicaoInfoDTO.getCodRUC();
        String estado = String.valueOf(Edicao.Estado.PENDENTE);

        assertEquals(codEdicao, resultJsonObject.getInt("codEdicao"));
        assertEquals(codUc, resultJsonObject.getInt("codUc"));
        assertEquals(codAnoLetivo, resultJsonObject.getInt("codAnoLetivo"));
        assertEquals(codRUC, resultJsonObject.get("codRUC"));
        assertEquals(estado, resultJsonObject.get("estado"));


        // GET Edicao/{codEdicao = 1}

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/edicao/" + 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject(resultContentStr3);

        int codEdicao3 = newEdicaoInfoDTO.getCodEdicao();
        int codUc3 = newEdicaoInfoDTO.getCodUc();
        int codAnoLetivo3 = newEdicaoInfoDTO.getCodAnoLetivo();
        int codRUC3 = newEdicaoInfoDTO.getCodRUC();
        String estado3 = String.valueOf(Edicao.Estado.PENDENTE);


        assertEquals(codEdicao3, resultJsonObject3.getInt("codEdicao"));
        assertEquals(codUc3, resultJsonObject3.getInt("codUc"));
        assertEquals(codAnoLetivo3, resultJsonObject3.getInt("codAnoLetivo"));
        assertEquals(codRUC3, resultJsonObject3.get("codRUC"));
        assertEquals(estado3, resultJsonObject3.get("estado"));

        assertNotNull(resultContentStr3);
    }
}*/
