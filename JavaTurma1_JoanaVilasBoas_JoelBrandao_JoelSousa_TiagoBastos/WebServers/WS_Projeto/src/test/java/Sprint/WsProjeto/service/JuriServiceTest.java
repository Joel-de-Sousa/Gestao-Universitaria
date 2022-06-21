package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.factories.JuriFactory;
import Sprint.WsProjeto.repositories.JuriRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.nio.channels.NonWritableChannelException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
class JuriServiceTest {

    @MockBean
    JuriFactory juriFactory;

    @MockBean
    JuriRepository juriRepository;

    @MockBean
    JuriDomainDTOAssembler juriDomainDTOAssembler;

    @InjectMocks
    JuriService juriService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAndSaveJuri(){
        //arrange
        NewJuriInfoDTO infoDTO = mock(NewJuriInfoDTO.class);
        Juri juri = mock(Juri.class);
        when(juriFactory.createJuri(infoDTO.getCodPresidente(),infoDTO.getCodOrientador(),infoDTO.getCodArguente())).thenReturn(juri);
        Juri juri1 = mock(Juri.class);
        when(juriRepository.save(juri)).thenReturn(juri1);
        JuriDTO juriDTO1 = mock(JuriDTO.class);
        when(juriDomainDTOAssembler.toDto(juri1)).thenReturn(juriDTO1);

        //act
        JuriDTO juriDTO = juriService.createAndSaveJuri(infoDTO);

        //assert
        assertEquals(juriDTO,juriDTO1);


    }

    @Test
    void shouldFindJuriByCode(){
        //arrange
        Juri juri = mock(Juri.class);
        Optional<Juri> optionalJuri = Optional.of(juri);
        when(juriRepository.findById(1)).thenReturn(optionalJuri);

        JuriDTO juriDTO1 = mock(JuriDTO.class);
        when(juriDomainDTOAssembler.toDto(optionalJuri.get())).thenReturn(juriDTO1);


        //act
        JuriDTO juriDTO = juriService.findJuriByCode(1);

        //assert

        assertEquals(juriDTO,juriDTO1);

    }



}