package Sprint.WsProjeto.domain.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoTest {

    Juri juri = new Juri(1,2,3,4);
    Submissao submissao = new Submissao(1,"teste",new File("e"),"tuga");

    @Test
    void shouldCreateNewAvaliacaoAll(){
        new Avaliacao(1,1,juri,submissao);

    }

    @Test
    void shouldCreateNewAvaliacaoWithCorrectAttributes(){

        Avaliacao avaliacao = new Avaliacao(1,1,juri,submissao);

        assertEquals(avaliacao.getCodAvaliacao(),1);
        assertEquals(avaliacao.getCodMA(),1);
        assertEquals(avaliacao.getJuri(),juri);
        assertEquals(avaliacao.getSubmissao(),submissao);


    }

    @Test
    void shouldCreateNewAvaliacaoWithCorrectAttributes2(){

        Avaliacao avaliacao =  new Avaliacao(1,juri,submissao);

        assertEquals(avaliacao.getCodMA(),1);
        assertEquals(avaliacao.getJuri(),juri);
        assertEquals(avaliacao.getSubmissao(),submissao);
    }

    @Test
    void shouldCreateNewAvaliacaoWithCorrectAttributes3(){
      Avaliacao avaliacao = new Avaliacao(1);

      assertEquals(avaliacao.getCodMA(),1);
    }

    @Test

    void shouldReturnEqualSameAvaliacao(){

        Avaliacao avaliacao = new Avaliacao(1,1,juri,submissao);

        assertEquals(avaliacao,avaliacao);
    }

    @Test
    void shouldReturnNotEqualsDifferentAvaliacoes() {

        Avaliacao avaliacao = new Avaliacao(1,1,juri,submissao);
        Avaliacao avaliacao1 = new Avaliacao(1,2,juri,submissao);


        assertNotEquals(avaliacao,avaliacao1);

    }

}