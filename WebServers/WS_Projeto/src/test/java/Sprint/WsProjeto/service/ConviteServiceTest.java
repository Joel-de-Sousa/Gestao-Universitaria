package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.ConviteDTO;
import Sprint.WsProjeto.DTO.NewConviteInfoDTO;
import Sprint.WsProjeto.DTO.assembler.ConviteDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.factories.ConviteFactory;
import Sprint.WsProjeto.repositories.ConviteRepository;
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
class ConviteServiceTest {

    @MockBean
    ConviteFactory conviteFactory;
    @MockBean
    ConviteRepository conviteRepository;

    @MockBean
    ConviteDomainDTOAssembler conviteDomainDTOAssembler;
    @InjectMocks
    ConviteService conviteService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAndSaveConvite() {

        //arrange

        NewConviteInfoDTO conviteInfoDTO = mock(NewConviteInfoDTO.class);
        Convite convite = mock(Convite.class);
        when(conviteFactory.createConvite(conviteInfoDTO.getCodProjeto(), conviteInfoDTO.getCodEstudante(), conviteInfoDTO.getCodDocente())).thenReturn(convite);
        Convite convite1 = mock(Convite.class);
        when(conviteRepository.save(convite)).thenReturn(convite1);
        ConviteDTO conviteDTO1 = mock(ConviteDTO.class);
        when(conviteDomainDTOAssembler.toDto(convite1)).thenReturn(conviteDTO1);

        //act
        ConviteDTO conviteDTO = conviteService.createAndSaveConvite(conviteInfoDTO);

        //assert
        assertEquals(conviteDTO, conviteDTO1);

    }

    @Test
    void shouldFindConviteByCode() {

        Convite convite = mock(Convite.class);
        Optional<Convite> optionalConvite = Optional.of(convite);
        when(conviteRepository.findById(1)).thenReturn(optionalConvite);

        ConviteDTO conviteDTO1 = mock(ConviteDTO.class);
        when(conviteDomainDTOAssembler.toDto(optionalConvite.get())).thenReturn(conviteDTO1);

        //act
        ConviteDTO conviteDTO = conviteService.findConviteByCode(1);

        //assert
        assertEquals(conviteDTO, conviteDTO1);


    }

}