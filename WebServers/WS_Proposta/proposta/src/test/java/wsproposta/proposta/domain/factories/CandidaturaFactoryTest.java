package wsproposta.proposta.domain.factories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CandidaturaFactoryTest {

    @Test
    void factoryShouldCreateCandidatura(){

        CandidaturaFactory candidaturaFactory = new CandidaturaFactory();
        candidaturaFactory.createCandidatura(1,2);
    }

}