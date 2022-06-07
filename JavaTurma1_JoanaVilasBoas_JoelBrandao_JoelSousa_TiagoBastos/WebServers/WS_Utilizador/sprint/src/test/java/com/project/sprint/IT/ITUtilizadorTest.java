package com.project.sprint.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.domain.entities.Utilizador;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ITUtilizadorTest {

   /* @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    PropostaService propostaService;
    @MockBean
    private UtilizadorRestRepository utilizadorRestRepository;
    @MockBean
    private OrganizacaoRestRepository organizacaoRestRepository;*/
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

        String generatedNome = StringUtils.capitalize(RandomStringUtils.randomAlphabetic(20));
        String generatedSobrenome = RandomStringUtils.randomAlphabetic(20);
        String email = "joana.gvb@gmail.com";
        String tipoUtilizador = java.lang.String.valueOf(Utilizador.TipoUtilizador.ESTUDANTE);

        NewUtilizadorInfoDTO newUtilizadorInfoDTO = new NewUtilizadorInfoDTO(generatedNome, generatedSobrenome,
                email, tipoUtilizador);


        // GET Proposta/{codProposta = 1}

        MvcResult resultGet11 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/utilizadores/" + 4)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContentStr11 = resultGet11.getResponse().getContentAsString();

        String msgErro = "";
        //assertNotNull(resultContent1);
        assertEquals( msgErro ,resultContentStr11);

        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/utilizadores")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newUtilizadorInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );

        String nome = newUtilizadorInfoDTO.getNome();
        assertEquals(nome, resultJsonObject.get("nome"));


        // GET Proposta/{codProposta = 1}

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/utilizadores/" + resultJsonObject.get("codUtilizador"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        String nome3 = newUtilizadorInfoDTO.getNome();
        assertEquals(nome3, resultJsonObject3.get("nome"));

        assertNotNull(resultContentStr3);
    }
}
