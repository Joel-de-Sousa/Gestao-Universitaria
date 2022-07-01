package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.ConviteDTO;
import Sprint.WsProjeto.DTO.ConvitePartialDTO;
import Sprint.WsProjeto.DTO.NewConviteInfoDTO;
import Sprint.WsProjeto.DTO.assembler.ConviteDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.ProjetoNotExists;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.ConviteFactory;
import Sprint.WsProjeto.repositories.ConviteRecusadoRepository;
import Sprint.WsProjeto.repositories.ConviteRepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ConviteServiceTest {

    @MockBean
    ProjetoJPARepository projetoJPARepository;

    @MockBean
    ConviteRecusadoRepository conviteRecusadoRepository;

    @MockBean
    ProjetoRepository projetoRepository;

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
    void shouldFindConviteByCode() throws Exception {

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

    @Test
    void shouldUpdateEstadoConviteIfAceite() throws Exception {

        //arrange
        ConvitePartialDTO update = mock(ConvitePartialDTO.class);
        when(update.getEstado()).thenReturn("ACEITE");
        Convite convite = mock(Convite.class);
        Optional<Convite> optionalConvite = Optional.of(convite);
        when(conviteRepository.findById(update.getCodConvite())).thenReturn(optionalConvite);
        optionalConvite.get().setCodConvite(update.getCodConvite());
        optionalConvite.get().setEstado(Convite.Estado.valueOf(update.getEstado()));

        Convite conviteSaved = mock(Convite.class);
        when(conviteRepository.save(optionalConvite.get())).thenReturn(conviteSaved);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);
        when(conviteDomainDTOAssembler.toDto(conviteSaved)).thenReturn(conviteDTO);

        Projeto projeto = mock(Projeto.class);
        Optional<Projeto> optionalProjeto = Optional.of(projeto);
        when(projetoRepository.findById(conviteDTO.getCodProjeto())).thenReturn(optionalProjeto);
        optionalProjeto.get().setCodOrientador(conviteDTO.getCodDocente());
        Projeto projetoSaved = mock(Projeto.class);
        when(projetoRepository.save(optionalProjeto.get())).thenReturn(projetoSaved);

        //act
        ConviteDTO act = conviteService.updateEstadoConvite(update);

        //assert
        assertEquals(act,conviteDTO);
    }
    @Test
    void shouldNotUpdateConviteIfRejeitado () throws Exception {

        ConvitePartialDTO update = mock(ConvitePartialDTO.class);
        when(update.getEstado()).thenReturn("REJEITADO");
        Convite convite = mock(Convite.class);
        Optional<Convite> optionalConvite = Optional.of(convite);
        when(conviteRepository.findById(update.getCodConvite())).thenReturn(optionalConvite);


        optionalConvite.get().setCodConvite(update.getCodConvite());
        optionalConvite.get().setEstado(Convite.Estado.valueOf(update.getEstado()));
        Convite conviteSaved = mock(Convite.class);
        when(conviteRepository.save(optionalConvite.get())).thenReturn(conviteSaved);
        ConviteDTO conviteDTO = mock(ConviteDTO.class);
        Convite conviteSavedRecusado = mock(Convite.class);
        when(conviteRecusadoRepository.save(conviteSaved)).thenReturn(conviteSavedRecusado);
        conviteRepository.deleteByCodConvite(conviteSaved.getCodConvite());
        when(conviteDomainDTOAssembler.toDto(conviteSavedRecusado)).thenReturn(conviteDTO);


        //act
        ConviteDTO act = conviteService.updateEstadoConvite(update);

        //assert
        assertEquals(act,conviteDTO);



    }


}