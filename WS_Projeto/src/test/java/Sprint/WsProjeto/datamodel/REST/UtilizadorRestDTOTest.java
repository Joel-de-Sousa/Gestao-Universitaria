package Sprint.WsProjeto.datamodel.REST;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilizadorRestDTOTest {

    @Test
    void shouldCreateANewUtilizadorRestDto() {
        new UtilizadorRestDTO(2,"Tiago","Bastos");

    }@Test
    void shouldNotCreateNewUtilizadorRestDto(){
        //???
    }
    @Test
    void shouldCreateAUtilizadorRestDtoWithCorrectAttributes(){
        UtilizadorRestDTO utilizadorRestDTO= new UtilizadorRestDTO(2,"Tiago","Bastos");
        assertEquals(utilizadorRestDTO.getCodUtilizador(),2);
        assertEquals(utilizadorRestDTO.getNome(),"Tiago");
        assertEquals(utilizadorRestDTO.getSobrenome(),"Bastos");


    }

    @Test
    void shouldReturnEqualSameUtilizadorRestDto(){

        UtilizadorRestDTO utilizadorRestDTO = new UtilizadorRestDTO(2,"Tiago","Bastos");

        assertEquals(utilizadorRestDTO,utilizadorRestDTO);

    }   @Test
    void shouldReturnNotEqualsDifferentUtilizadoresRestDto(){

        UtilizadorRestDTO utilizadorRestDTO = new UtilizadorRestDTO(2,"Tiago","Bastos");
        UtilizadorRestDTO utilizadorRestDTO1 = new UtilizadorRestDTO(2,"Tiago","Bastos");


        assertNotEquals(utilizadorRestDTO,utilizadorRestDTO1);
    }

}