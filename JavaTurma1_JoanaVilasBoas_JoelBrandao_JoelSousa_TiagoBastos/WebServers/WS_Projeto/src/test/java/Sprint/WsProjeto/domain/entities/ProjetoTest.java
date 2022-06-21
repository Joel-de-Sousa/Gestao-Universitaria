package Sprint.WsProjeto.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjetoTest {

    @Test
    void shouldCreateNewProjeto(){

        new Projeto(2,3,3);
    }

    @Test
    void shouldCreateNewProjetoWithCorrectAttributes(){
        Projeto projeto = new Projeto(1,2,3);

        assertEquals(projeto.getCodEstudante(),1);
        assertEquals(projeto.getCodOrientador(),2);
        assertEquals(projeto.getCodProposta(),3);

    }

    @Test
    void shouldCreateNewProjetoWithCorrectAttributes2(){

        Projeto projeto = new Projeto(1,2);

        assertEquals(projeto.getCodEstudante(),1);
        assertEquals(projeto.getCodProposta(),2);
    }

    @Test
    void shouldCreateNewProjetoWithCorrectAttributesALL(){
        Projeto projeto = new Projeto(1,2,3,4);

        assertEquals(projeto.getCodProjeto(),1);
        assertEquals(projeto.getCodEstudante(),2);
        assertEquals(projeto.getCodOrientador(),3);
        assertEquals(projeto.getCodProposta(),4);
    }

    @Test

    void shouldReturnEqualSameProjeto(){
        Projeto projeto = new Projeto(1,2,3);

        assertEquals(projeto,projeto);
    }

    @Test
    void shouldReturnNotEqualsDifferentProjetos() {

        Projeto projeto = new Projeto(1,2,3);
        Projeto projeto1 = new Projeto(1,2,4);

        assertNotEquals(projeto1,projeto);

    }


}