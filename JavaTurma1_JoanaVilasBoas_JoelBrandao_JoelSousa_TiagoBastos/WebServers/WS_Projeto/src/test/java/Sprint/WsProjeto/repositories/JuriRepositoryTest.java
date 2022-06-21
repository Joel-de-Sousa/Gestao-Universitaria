package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class JuriRepositoryTest {

    @MockBean
    JuriJPARepository juriJPARepository;
    @MockBean
    JuriDomainDataAssembler juriDomainDataAssembler;

    @InjectMocks
   JuriRepository juriRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveJuri(){
        //arrange
        Juri juri = mock(Juri.class);
        JuriJPA juriJPA = mock(JuriJPA.class);
        when(juriDomainDataAssembler.toData(juri)).thenReturn(juriJPA);
        JuriJPA savedJpa = mock(JuriJPA.class);
        when(juriJPARepository.save(juriJPA)).thenReturn(savedJpa);

        when(juriDomainDataAssembler.toDomain(savedJpa)).thenReturn(juri);


        //act
        Juri result = juriRepository.save(juri);

        assertEquals(result,juri);



    }

    @Test
    void shouldFindById(){
        //arrange

        JuriJPA juriJPA = mock(JuriJPA.class);
        Optional<JuriJPA> optionalJuriJPA = Optional.of(juriJPA);
        when(juriJPARepository.findById(1)).thenReturn(optionalJuriJPA);
        Juri juri = mock(Juri.class);
        when(juriDomainDataAssembler.toDomain(optionalJuriJPA.get())).thenReturn(juri);


        //act
        Optional<Juri> result = juriRepository.findById(1);

        //assert
        assertEquals(result,Optional.of(juri));

    }


}