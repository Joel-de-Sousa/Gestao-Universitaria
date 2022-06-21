package Sprint.WsProjeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuriTest {

    @Test
    void shouldCreateNewJuri(){

        new Juri(1,2,3);
    }

    @Test
    void shouldCreateNewJuriWithCorrectAttributes(){

        Juri juri = new Juri(1,2,3);



        assertEquals(juri.getCodPresidente(),1);
        assertEquals(juri.getCodOrientador(),2);
        assertEquals(juri.getCodArguente(),3);

    }


    @Test

    void shouldReturnEqualSameJuri(){
        Juri juri = new Juri(1,2,3);


        assertEquals(juri,juri);
    }

    @Test
    void shouldReturnNotEqualsDifferentJuris() {

        Juri juri = new Juri(1,2,3);
        Juri juri1 = new Juri(1,2,4);



        assertNotEquals(juri,juri1);

    }


}

