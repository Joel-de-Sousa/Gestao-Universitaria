package wsproposta.proposta.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PropostaTest {

    @Test
    void shouldCreatePropostaWithCorrectAttributes() {
        Proposta propostaTeste = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");
    }

    @Test
    void shouldCreatePropostaWithCorrectAttributesEquals() {
        Proposta propostaTeste = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        assertEquals(1, propostaTeste.getCodUtilizador());
        assertEquals(257837248, propostaTeste.getNifOrganizacao());
        assertEquals(1, propostaTeste.getCodEdicao());
        assertEquals("Cria Proposta com atributos correctos", propostaTeste.getTitulo());
        assertEquals("A proposta está a ser criada correctamente?", propostaTeste.getProblema());
        assertEquals("Testar criação de proposta", propostaTeste.getObjetivo());
        assertEquals(Proposta.Estado.PENDENTE, propostaTeste.getEstado());

    }

    //TESTES EXCEPÇOES TITULO
    @Test
    public void shouldThrowExceptionWithEmptyTitulo() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "", "A proposta está a ser criada correctamente?", "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Título' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithNullTitulo() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    null, "A proposta está a ser criada correctamente?", "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Título' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithTenBlankSpacesTitulo() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "          ", "A proposta está a ser criada correctamente?", "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Título' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    //TESTES EXCEPÇOES PROBLEMA
    @Test
    public void shouldThrowExceptionWithEmptyProblema()  {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "Cria Proposta com atributos correctos", "", "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Problema' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithNullProblema()  {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                   "Cria Proposta com atributos correctos" , null, "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Problema' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithTenBlankSpacesProblema() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "Cria Proposta com atributos correctos", "          ", "Testar criação de proposta");
        });
        String expectedMessage = "O valor do parâmetro 'Problema' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    //TESTES EXCEPÇOES OBJECTIVO

    @Test
    public void shouldThrowExceptionWithEmptyObjectivo()  {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "");
        });
        String expectedMessage = "O valor do parâmetro 'Objectivo' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithNullObjectivo() /*throws Exception*/ {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "Cria Proposta com atributos correctos" , "A proposta está a ser criada correctamente?", null);
        });
        String expectedMessage = "O valor do parâmetro 'Objectivo' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowExceptionWithTenBlankSpacesObjectivo() {

        Exception exception = assertThrows(Exception.class, () -> {
            new Proposta(1, 257837248, 1,
                    "Cria Proposta com atributos correctos","A proposta está a ser criada correctamente?" , "          ");
        });
        String expectedMessage = "O valor do parâmetro 'Objectivo' deve ter no mínimo 10 caracteres não brancos";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    //TESTES EQUALS ?????
    @Test
    public void shouldReturnTrueEqualsWithSameObject()
    {
        Proposta propostaTeste = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        boolean isEquals = propostaTeste.equals(propostaTeste);

        assertTrue( isEquals );
    }

    @Test
    public void shouldReturnTrueEqualsAlojamentosWithSameParameters()
    {
        Proposta propostaTeste = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        Proposta propostaTeste2 = new Proposta(1, 257837248, 1,
                "Cria Proposta com atributos correctos", "A proposta está a ser criada correctamente?", "Testar criação de proposta");

        boolean isEquals = propostaTeste.equals(propostaTeste2);

        assertTrue( isEquals );
    }

}