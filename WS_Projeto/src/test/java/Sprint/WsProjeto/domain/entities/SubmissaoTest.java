package Sprint.WsProjeto.domain.entities;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SubmissaoTest {

    File file = new File("teste");
    
    @Test
    void shouldCreateNewSubmissao(){

        new Submissao("teste1",file,"ESPANHOL");
    }

    @Test
    void shouldCreateNewSubmissaoWithCorrectAttributes(){

        Submissao submissao =  new Submissao("teste1",file,"ESPANHOL");



        assertEquals(submissao.getTitulo(),"teste1");
        assertEquals(submissao.getFile(),file);
        assertEquals(submissao.getLinguagemFile(),"ESPANHOL");

    }

    @Test
    void shouldCreateNewSubmissaoWithCorrectAttributesAllArgs(){

        Submissao submissao =  new Submissao(1,"teste1",file,"ESPANHOL", Submissao.Estado.PENDENTE);


        assertEquals(submissao.getCodSubmissao(),1);
        assertEquals(submissao.getTitulo(),"teste1");
        assertEquals(submissao.getFile(),file);
        assertEquals(submissao.getLinguagemFile(),"ESPANHOL");
        assertEquals(submissao.getEstado(), Submissao.Estado.PENDENTE);
    }


    @Test

    void shouldReturnEqualSameSubmissao(){
        Submissao submissao =  new Submissao("teste1",file,"ESPANHOL");


        assertEquals(submissao,submissao);
    }

    @Test
    void shouldReturnNotEqualsDifferentSubmissoes() {

        Submissao submissao =  new Submissao("teste1",file,"ESPANHOL");
        Submissao submissao1 =  new Submissao("teste1",file,"PT");



        assertNotEquals(submissao,submissao1);

    }



}