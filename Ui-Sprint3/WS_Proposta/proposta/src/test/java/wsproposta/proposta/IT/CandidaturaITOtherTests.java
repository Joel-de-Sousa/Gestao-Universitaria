package wsproposta.proposta.IT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.repositories.CandidaturaRepository;
import wsproposta.proposta.repositories.JPA.ICandidaturaJPARepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class CandidaturaITOtherTests {


    @Autowired
    CandidaturaRepository candidaturaRepository;
    @Autowired
    ICandidaturaJPARepository candidaturaJPARepository;



    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


  /*@Test
    void shouldGetCandidaturaByCodCandidatura() throws Exception{

        //ARRANGE
        int size = candidaturaRepository.findAll().size();
        assertEquals(size, 0);

        Candidatura candidatura1 = new Candidatura(1,125, 1, Candidatura.Estado.PENDENTE);
        Candidatura candidatura2 = new Candidatura(2,25, 2 , Candidatura.Estado.PENDENTE);

        candidaturaRepository.save(candidatura1);
        candidaturaRepository.save(candidatura2);

        int size2 = candidaturaRepository.findAll().size();
        assertEquals(size2, 2);

        //ACT
        Optional<Candidatura> candidatura = candidaturaRepository.findById(1);      //propostaRepository.findAllByCodUtilizador(1);
        candidatura.isPresent();
        //ASSERT
        assertEquals(candidatura.get().getCodProposta(),candidatura1.getCodProposta());
        //assertEquals(candidatura2.getCodEstudante(), candidatura.get().getCodEstudante());

    }*/
    @Test

    void shouldGetCandidaturaByCodIT() {

        //ARRANGE
        int size = candidaturaRepository.findAll().size();
        assertEquals(size, 0);

        Candidatura candidatura1 = new Candidatura(1,2,3, Candidatura.Estado.ACEITE);

        Candidatura candidatura2 = new Candidatura(2,2,3, Candidatura.Estado.ACEITE);

        candidaturaRepository.save(candidatura1);
        candidaturaRepository.save(candidatura2);
/*
        int size2 = candidaturaRepository.findAll().size();
        assertEquals(size2, 2);*/

        //ACT
        List<Candidatura> listCandidaturas = candidaturaRepository.findAll();

        //ASSERT
        assertEquals(2, listCandidaturas.size());
        assertEquals(1, listCandidaturas.get(0).getCodCandidatura());

    }
}
