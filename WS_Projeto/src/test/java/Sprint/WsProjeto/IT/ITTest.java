package Sprint.WsProjeto.IT;
import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.repositories.REST.EdicaoRestRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import Sprint.WsProjeto.repositories.REST.UtilizadorRestRepository;
import Sprint.WsProjeto.service.ProjetoService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ITTest {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    ProjetoService projetoService;

    @MockBean
    UtilizadorRestRepository utilizadorRestRepository;

    @MockBean
    PropostaRestRepository propostaRestRepository;

    @MockBean
     EdicaoRestRepository edicaoRestRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldPostNewProjetoIT() throws Exception {
        int generatedCodProjeto = Integer.parseInt(RandomStringUtils.randomNumeric(4));

        int generatedCodEstudadnte = Integer.parseInt(RandomStringUtils.randomNumeric(4));

       // int generatedCodOrientador = Integer.parseInt(RandomStringUtils.randomNumeric(4));

        int generatedCodProposta = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        int generatedCodEdicao = Integer.parseInt(RandomStringUtils.randomNumeric(4));

       /* UtilizadorRestDTO estudanteDouble = mock(UtilizadorRestDTO.class);
        when(estudanteDouble.getCodUtilizador()).thenReturn(generatedCodEstudadnte);
*/
       /* UtilizadorRestDTO orientadorDouble = mock(UtilizadorRestDTO.class);
        when(orientadorDouble.getCodUtilizador()).thenReturn(generatedCodOrientador);*/

        PropostaRestDTO propostaDouble = mock(PropostaRestDTO.class);
        when(propostaDouble.getCodProposta()).thenReturn(generatedCodProposta);
        when(propostaDouble.getCodEdicao()).thenReturn(generatedCodEdicao);

        EdicaoRestDTO edicaoDouble = mock(EdicaoRestDTO.class);
       // when(edicaoDouble.getCodEdicao()).thenReturn(generatedCodProposta);

        NewProjetoInfoDto newProjetoInfoDto = new NewProjetoInfoDto(propostaDouble.getCodProposta(),generatedCodEstudadnte);

       // NewProjetoInfoDto newProjetoInfoDto = new NewProjetoInfoDto(propostaDouble.getCodProposta(),estudanteDouble.getCodUtilizador());

        /*Optional<UtilizadorRestDTO> optionalEstudante = Optional.of(estudanteDouble);
        when(utilizadorRestRepository.findUtilizadorByCode(1)).thenReturn(optionalEstudante);

        Optional<UtilizadorRestDTO> optionalOrientador = Optional.of(orientadorDouble);
        when(utilizadorRestRepository.findUtilizadorByCode(1)).thenReturn(optionalOrientador);*/

        Optional<PropostaRestDTO> optionalProposta = Optional.of(propostaDouble);
        when(propostaRestRepository.findPropostaByCode(1)).thenReturn(optionalProposta);
        Optional<EdicaoRestDTO> optionalEdicao = Optional.of(edicaoDouble);
        when(edicaoRestRepository.findEdicaoByCode(1)).thenReturn(optionalEdicao);

        // GET Projeto/{codProjeto = 1}

        MvcResult resultGet11 = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/projetos/" + generatedCodProjeto)
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
                        .post("/projetos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(newProjetoInfoDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();


        String resultContentStr = resultPost.getResponse().getContentAsString();
        JSONObject resultJsonObject = new JSONObject( resultContentStr );


        int codEstudante = newProjetoInfoDto.getCodEstudante();

        int codProposta = newProjetoInfoDto.getCodProposta();

        assertEquals(codEstudante, resultJsonObject.getInt("codEstudante"));

        assertEquals(codProposta, resultJsonObject.getInt("codProposta"));


        // GET Projeto/{codProjeto = 1}
        MvcResult resultGet = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/projetos/" + resultJsonObject.getInt("codProjeto"))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();



        String resultContentStr3 = resultGet.getResponse().getContentAsString();
        JSONObject resultJsonObject3 = new JSONObject( resultContentStr3 );

        int codEstudante3 = newProjetoInfoDto.getCodEstudante();

        int codProposta3 = newProjetoInfoDto.getCodProposta();

        assertEquals(codEstudante3, resultJsonObject3.getInt("codEstudante"));

        assertEquals(codProposta3, resultJsonObject3.getInt("codProposta"));

        assertNotNull(resultContentStr3);

    }



}

