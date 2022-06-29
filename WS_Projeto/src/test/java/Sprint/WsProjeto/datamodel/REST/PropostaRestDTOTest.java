/*
package Sprint.WsProjeto.datamodel.REST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropostaRestDTOTest {

    @Test
    void shouldCreateANewPropostaRestDto() {
        new PropostaRestDTO(1, 245490493,
                2,
                "teste", "teste1",
                "testar", "em teste");

    }@Test
    void shouldNotCreateNewPropostaRestDto(){
        //???
    }
    @Test
    void shouldCreateAPropostaRestDtoWithCorrectAttributes(){
        PropostaRestDTO propostaRestDTO = new  PropostaRestDTO(1,245490493,2,"teste","teste1","testar","em teste");

        assertEquals(propostaRestDTO.getCodUtilizador(),1);
        assertEquals(propostaRestDTO.getNifOrganizacao(),245490493);
        assertEquals(propostaRestDTO.getCodEdicao(),2);
        assertEquals(propostaRestDTO.getTitulo(),"teste");
        assertEquals(propostaRestDTO.getProblema(),"teste1");
        assertEquals(propostaRestDTO.getObjetivo(),"testar");
        assertEquals(propostaRestDTO.getEstado(),"em teste");

    }

    @Test
    void shouldReturnEqualSamePropostaRestDto(){

        PropostaRestDTO propostaRestDTO = new  PropostaRestDTO(1,245490493,2,"teste","teste1","testar","em teste");

        assertEquals(propostaRestDTO,propostaRestDTO);

    }   @Test
    void shouldReturnNotEqualsDifferentPropostasRestDto(){

        PropostaRestDTO propostaRestDTO = new PropostaRestDTO(1,245490493,2,"teste","teste1","testar","em teste");
        PropostaRestDTO propostaRestDTO1 = new PropostaRestDTO(2,245490495,3,"teste","teste1","testar","em teste");


        assertNotEquals(propostaRestDTO,propostaRestDTO1);
    }


}*/
