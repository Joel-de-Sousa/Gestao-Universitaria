package WSEdicao.datamodel.REST;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilizadorRestDTOTest {

    @Test
    void shouldCreateANewUtilizadorRestDTO() {
        new UtilizadorRestDTO(1,"John","Doe","myemail@domain.pt","ESTUDANTE");
    }

    @Test
    void shouldCreateAUtilizadorRestDTOWithCorrectAttributes() {
        UtilizadorRestDTO utilizadorRestDTO1 = new UtilizadorRestDTO(1,"John","Doe","myemail@domain.pt","ESTUDANTE");

        assertEquals(utilizadorRestDTO1.codUtilizador, 1);
        assertEquals(utilizadorRestDTO1.getNome(),"John");
        assertEquals(utilizadorRestDTO1.getSobrenome(),"Doe");
        assertEquals(utilizadorRestDTO1.getEmail(),"myemail@domain.pt");
        assertEquals(utilizadorRestDTO1.getTipoUtilizador(),"ESTUDANTE");
    }

    @Test
    void shouldReturnTheSameUtilizadorRestDTO() {
        UtilizadorRestDTO utilizadorRestDTO1 = new UtilizadorRestDTO(1,"John","Doe","myemail@domain.pt","ESTUDANTE");
        assertEquals(utilizadorRestDTO1,utilizadorRestDTO1);
    }
    @Test
    void shouldReturnNotEqualsDifferentUtilizadorRestDTO(){
        UtilizadorRestDTO utilizadorRestDTO1 = new UtilizadorRestDTO (1,"John","Doe","myemail@domain.pt","ESTUDANTE");
        UtilizadorRestDTO utilizadorRestDTO2 = new UtilizadorRestDTO (3,"Average","Joe","myemail@domain.pt","ESTUDANTE");

        assertNotEquals(utilizadorRestDTO1,utilizadorRestDTO2);
    }
}