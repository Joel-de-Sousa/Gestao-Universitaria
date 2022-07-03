package Sprint.WsProjeto.IT;

import Sprint.WsProjeto.DTO.NewAvaliacaoInfoDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import Sprint.WsProjeto.repositories.JDBC.JuriJDBCRepository;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ITTestAvaliacao {

   @MockBean
   JuriJDBCRepository juriJDBCRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPostNewAvaliacaoIT() throws Exception {


    int generatedCodProjeto = Integer.parseInt(RandomStringUtils.randomNumeric(4));
    int generatedCodMA = Integer.parseInt(RandomStringUtils.randomNumeric(4));

   /* Juri juri = mock(Juri.class);
    when(juriJDBCRepository.getById(resultJsonObject.getInt("codProjeto")).thenReturn(generatedCodAvaliacao);
    when(ava.getCodProjeto()).thenReturn(generatedCodProjeto);
    when(ava.getCodMA()).thenReturn(generatedCodMA);*/
        /*when(ava.getJuri()).thenReturn(1);
        when(ava.getSubmissao()).thenReturn();
        when(ava.getNota()).thenReturn(1.0);
        when(ava.getJustificacao()).thenReturn(1);
        when(ava.getDate()).thenReturn(1);
        when(ava.getEstado()).thenReturn(1);*/

    /*Avaliacao ava2 = mock(Avaliacao.class);
    Optional<Avaliacao> opAva = Optional.of(ava);
    when(avaliacaoRepository.findById(generatedCodAvaliacao)).thenReturn(opAva);
    when(avaliacaoRepository.save(ava)).thenReturn(ava2);*/

    NewAvaliacaoInfoDTO newAvaliacaoInfoDTO = new NewAvaliacaoInfoDTO(generatedCodProjeto, generatedCodMA);

    //GET
    /*int generatedCodJuri = Integer.parseInt(RandomStringUtils.randomNumeric(4));*/


    MvcResult resultGet1 = mockMvc
            .perform(MockMvcRequestBuilders
                    .get("/avaliacoes/" + generatedCodProjeto)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andReturn();

    String resultContent1 = resultGet1.getResponse().getContentAsString();
    assertNotNull(resultContent1);
    assertEquals("", resultContent1);

    // POST

    MvcResult resultPost = mockMvc
            .perform(MockMvcRequestBuilders
                    .post("/avaliacoes")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(newAvaliacaoInfoDTO))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andReturn();


 /*   String resultContentStr = resultPost.getResponse().getContentAsString();
    JSONObject resultJsonObject = new JSONObject( resultContentStr );

    int codProjeto = newAvaliacaoInfoDTO.getCodprojeto();


    assertEquals(codProjeto, resultJsonObject.getInt("codProjeto"));*/


/*
    // GET Juri/{codJuri}

    int codJuri = resultJsonObject.getInt("codJuri");

    MvcResult resultGet = mockMvc
            .perform(MockMvcRequestBuilders
                    .get("/juris/" + codJuri)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

    String resultContentStr3 = resultGet.getResponse().getContentAsString();
    JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

    int codPres = newJuriInfoDTO.getCodPresidente();

    assertEquals(codPres, resultJsonObject3.getInt("codPresidente"));

    assertNotNull(resultContentStr3);*/
}
}
