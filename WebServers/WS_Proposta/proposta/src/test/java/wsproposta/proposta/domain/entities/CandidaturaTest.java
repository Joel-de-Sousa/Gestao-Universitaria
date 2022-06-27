package wsproposta.proposta.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidaturaTest {

    @Test
    void shouldCreateCandidaturaWithCorrectAttributes() {
        Candidatura candidatura = new Candidatura(1,2,3, Candidatura.Estado.PENDENTE);

    }

    @Test
    void shouldCreateNewUtilizadorWithCorrectAttributes(){
        Candidatura candidatura = new Candidatura(1,2,3, Candidatura.Estado.ACEITE);

        assertEquals(candidatura.getCodCandidatura(),1);
        assertEquals(candidatura.getCodProposta(),2);
        assertEquals(candidatura.getCodEstudante(),3);
        assertEquals(candidatura.getEstadoEstudante(), Candidatura.Estado.ACEITE);
    }

    @Test
    public void shouldReturnTrueEqualsWithSameObject()
    {
        Candidatura candidatura = new Candidatura(1,2,3, Candidatura.Estado.PENDENTE
                );

        boolean isEquals = candidatura.equals(candidatura);

        assertTrue( isEquals );
    }

    @Test
    public void shouldReturnTrueEqualsCandidaturasWithSameParameters()
    {
        Candidatura candidatura = new Candidatura(1,2,3, Candidatura.Estado.PENDENTE);
        Candidatura candidatura1 = new Candidatura(1,2,3, Candidatura.Estado.PENDENTE);

        boolean isEquals = candidatura.equals(candidatura1);

        assertTrue( isEquals );
    }

    @Test
    void shouldReturnNotEqualsDifferentCandidaturas() {

        Candidatura candidatura = new Candidatura(1,2,3, Candidatura.Estado.PENDENTE);
        Candidatura candidatura2 = new Candidatura(1,2,3, Candidatura.Estado.ACEITE);

        assertNotEquals(candidatura,candidatura2);

    }

}