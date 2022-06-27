package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ConviteDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.repositories.JPA.ConviteJPARepository;
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
class ConviteRepositoryTest {

    @MockBean
    ConviteJPARepository conviteJPARepository;

    @MockBean
    ConviteDomainDataAssembler conviteDomainDataAssembler;

    @InjectMocks
    ConviteRepository conviteRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void shouldSaveConvite(){
        //arrange
        Convite convite = mock(Convite.class);
        ConviteJPA conviteJPA = mock(ConviteJPA.class);
        when(conviteDomainDataAssembler.toData(convite)).thenReturn(conviteJPA);
        ConviteJPA conviteJPASaved = mock(ConviteJPA.class);
        when(conviteJPARepository.save(conviteJPA)).thenReturn(conviteJPASaved);
        when(conviteDomainDataAssembler.toDomain(conviteJPASaved)).thenReturn(convite);

        //act
        Convite result = conviteRepository.save(convite);

        assertEquals(result,convite);


    }
    @Test
    void shouldFindConviteId(){
        //arrange
        Convite convite = mock(Convite.class);
        ConviteJPA conviteJPA = mock(ConviteJPA.class);
        Optional<ConviteJPA> optionalConviteJPA = Optional.of(conviteJPA);
        when(conviteJPARepository.findById(1)).thenReturn(optionalConviteJPA);
        when(conviteDomainDataAssembler.toDomain(optionalConviteJPA.get())).thenReturn(convite);


        //act
        Optional<Convite> result = conviteRepository.findById(1);

        //assert
        assertEquals(result,Optional.of(convite));
    }


}