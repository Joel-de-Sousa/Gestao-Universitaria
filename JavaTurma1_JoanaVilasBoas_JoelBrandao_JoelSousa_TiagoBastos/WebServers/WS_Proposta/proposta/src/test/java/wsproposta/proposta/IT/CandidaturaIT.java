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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import wsproposta.proposta.DTO.CandidaturaDTO;
import wsproposta.proposta.DTO.NewCandidaturaInfoDTO;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.domain.entities.Proposta;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CandidaturaIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void test() throws Exception {

    /*    Candidatura candidaturaDouble = mock(Candidatura.class);
        assert candidaturaDouble != null;
        when(candidaturaDouble.getCodCandidatura());
        when(candidaturaDouble.getEstadoEstudante());*/


       /* int generetedCodCandidatura = Integer.parseInt(RandomStringUtils.randomNumeric(4));*/
        int genereteCodProposta = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generetedCodEstudante = Integer.parseInt(RandomStringUtils.randomNumeric(4));
/*        String estadoEstudante = java.lang.String.valueOf(Candidatura.Estado.valueOf("PENDENTE"));*/

        NewCandidaturaInfoDTO newCandidaturaInfoDTO = new NewCandidaturaInfoDTO(genereteCodProposta,generetedCodEstudante);

        // GET

        MvcResult resultGet11 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/candidaturas/" + genereteCodProposta )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContentStr11 = resultGet11.getResponse().getContentAsString();

        String msgErro = "O codigo da candidatura nao consta na Base de Dados";
        assertEquals( msgErro ,resultContentStr11);

        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/candidaturas")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newCandidaturaInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );

        int estado2 = newCandidaturaInfoDTO.getCodEstudante();
        assertEquals(estado2, resultJsonObject.get("codEstudante"));


        // GET

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/candidaturas/" + resultJsonObject.get("codCandidatura"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        int estado3 = newCandidaturaInfoDTO.getCodEstudante();
        assertEquals(estado3, resultJsonObject3.get("codEstudante"));

        assertNotNull(resultContentStr3);
    }
}





