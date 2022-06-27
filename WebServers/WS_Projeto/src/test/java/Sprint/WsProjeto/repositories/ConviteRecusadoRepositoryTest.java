package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.ConviteRecusadoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ConviteDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.repositories.JPA.ConviteJPARepository;
import Sprint.WsProjeto.repositories.JPA.ConviteRecusadoJPARepository;
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
class ConviteRecusadoRepositoryTest {

    @MockBean
    ConviteRecusadoJPARepository conviteJPARepository;

    @MockBean
    ConviteDomainDataAssembler conviteDomainDataAssembler;

    @InjectMocks
    ConviteRecusadoRepository conviteRecusadoRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ShouldSaveConviteRecusado(){
        Convite convite = mock(Convite.class);

        ConviteRecusadoJPA conviteRecusadoJPA = mock(ConviteRecusadoJPA.class);

        when(conviteDomainDataAssembler.toDataRecusado(convite)).thenReturn(conviteRecusadoJPA);

        ConviteRecusadoJPA savedConviteRecusado = mock(ConviteRecusadoJPA.class);

        when(conviteJPARepository.save(conviteRecusadoJPA)).thenReturn(savedConviteRecusado);

        when(conviteDomainDataAssembler.toDomainRecusado(savedConviteRecusado)).thenReturn(convite);


        //act
        Convite result = conviteRecusadoRepository.save(convite);

        //assert

        assertEquals(result,convite);
    }


    @Test
    void shouldFindById(){

        Convite convite = mock(Convite.class);
        ConviteRecusadoJPA conviteRecusadoJPA = mock(ConviteRecusadoJPA.class);
        Optional<ConviteRecusadoJPA> optionalConviteRecusadoJPA = Optional.of(conviteRecusadoJPA);
        when(conviteJPARepository.findById(1)).thenReturn(optionalConviteRecusadoJPA);

        when(conviteDomainDataAssembler.toDomainRecusado(optionalConviteRecusadoJPA.get())).thenReturn(convite);

        Optional<Convite> result = conviteRecusadoRepository.findById(1);

        assertEquals(result,Optional.of(convite));
    }


}