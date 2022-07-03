package Sprint.WsProjeto.IT;

import Sprint.WsProjeto.DTO.NewConviteInfoDTO;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ITTestConvite {

    @MockBean
    ProjetoJPARepository projetoJPARepository;
    /*@Autowired
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
    void shouldPostNewConviteIT() throws Exception {

    /*    int generatedCodProjeto = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodEstudante = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodDocente = Integer.parseInt(RandomStringUtils.randomNumeric(4));


        when(projetoJPARepository.existsById(generatedCodProjeto)).thenReturn(true);

        NewConviteInfoDTO newConviteInfoDTO = new NewConviteInfoDTO(46, generatedCodEstudante,
                generatedCodDocente);

        //GET A CONVITE
        int generatedCodConvite = Integer.parseInt(RandomStringUtils.randomNumeric(4));


        MvcResult resultGet1 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/convites/" + generatedCodConvite)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        String resultContent1 = resultGet1.getResponse().getContentAsString();
        assertNotNull(resultContent1);
        assertEquals("Convite nao encontrado", resultContent1);



        // POST

        MvcResult resultPost = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/convites")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newConviteInfoDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );

        int codProjeto = newConviteInfoDTO.getCodProjeto();


        assertEquals(codProjeto, resultJsonObject.getInt("codProjeto"));*/


/*
        // GET Proposta/{codProposta}
        int codDocente = resultJsonObject.getInt("codDocente");

        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/convites/" + codDocente)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        int codProj = newConviteInfoDTO.getCodProjeto();

        assertEquals(codProj, resultJsonObject3.getInt("codProjeto"));

        assertNotNull(resultContentStr3);*/
    }


}