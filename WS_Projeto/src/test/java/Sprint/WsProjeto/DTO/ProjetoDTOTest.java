package Sprint.WsProjeto.DTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoDTOTest {


    @Test
    void shouldCreateProjetoDTO(){

        ProjetoDTO projetoDTO = new ProjetoDTO(1,2,3,4);

        assertEquals(projetoDTO.getCodProjeto(),1);
        assertEquals(projetoDTO.getCodProposta(),2);
        assertEquals(projetoDTO.getCodEstudante(),3);
        assertEquals(projetoDTO.getCodOrientador(),4);
    }
}