package wsproposta.proposta.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.transaction.annotation.Transactional;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class PropostaITOtherTests {

    @Autowired
    PropostaRepository propostaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    //TESTE INTEGRACAO REPOSITORIO GET PROPOSTA BY COD UTILIZADOR
    @Test
    void shouldGetPropostaByIdUtilizadorIT() {

        //ARRANGE
        int size = propostaRepository.findAll().size();
        assertEquals(size, 0);

        Proposta proposta1 = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");
        Proposta proposta2 = new Proposta(12, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        propostaRepository.save(proposta1);
        propostaRepository.save(proposta2);

        int size2 = propostaRepository.findAll().size();
        assertEquals(size2, 2);

        //ACT
        List<Proposta> listPropostas = propostaRepository.findAllByCodUtilizador(1);

        //ASSERT
        assertEquals(1, listPropostas.size());
        assertEquals(1, listPropostas.get(0).getCodUtilizador());

    }

    //TESTE INTEGRACAO REPOSITORY GET BY CODPROPOSTA

    @Test
    void shouldGetPropostaByIdProposta() throws Exception {

        //ARRANGE
        int size = propostaRepository.findAll().size();
        assertEquals(size, 0);

        Proposta proposta1 = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");
        Proposta proposta2 = new Proposta(12, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        propostaRepository.save(proposta1);
        propostaRepository.save(proposta2);

        int size2 = propostaRepository.findAll().size();
        assertEquals(size2, 2);

        //ACT
        Optional<Proposta> proposta = propostaRepository.findById(2);

        //ASSERT
        assertEquals(2, proposta.get().getCodProposta());
        assertEquals(proposta2.getCodUtilizador(), proposta.get().getCodUtilizador());

    }
}




