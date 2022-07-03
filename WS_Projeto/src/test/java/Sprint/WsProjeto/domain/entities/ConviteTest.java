package Sprint.WsProjeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConviteTest {



    @Test
    void shouldCreateNewConvite(){

        new Convite(1,2,3,4, Convite.Estado.PENDENTE);
    }

    @Test
    void shouldCreateNewConvite2(){

        new Convite(2,3,4);
    }

    @Test
    void shouldCreateNewConviteWithCorrectAttributes(){

        Convite convite = new Convite(1,2,3,4, Convite.Estado.PENDENTE);

        assertEquals(convite.getCodConvite(),1);
        assertEquals(convite.getCodProjeto(),2);
        assertEquals(convite.getCodEstudante(),3);
        assertEquals(convite.getCodDocente(),4);
        assertEquals(convite.getEstado(), Convite.Estado.PENDENTE);

    }



    @Test

    void shouldReturnEqualSameConvite(){


        Convite convite = new Convite(1,2,3,4, Convite.Estado.PENDENTE);

        assertEquals(convite,convite);
    }

    @Test
    void shouldReturnNotEqualsDifferentConvites() {

        Convite convite = new Convite(1,2,3,4, Convite.Estado.PENDENTE);
        Convite convite1 = new Convite(2,2,3,4, Convite.Estado.PENDENTE);


        assertNotEquals(convite,convite1);

    }


}

