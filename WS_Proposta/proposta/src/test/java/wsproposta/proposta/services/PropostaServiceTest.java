package wsproposta.proposta.services;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PropostaServiceTest {


    /*@MockBean
    NewTipoAlojamentoInfoDTO tipoAlojamentoInfoDTO;*/
    @InjectMocks
    PropostaService propostaService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }



}