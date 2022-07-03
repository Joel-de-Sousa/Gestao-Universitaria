package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.factories.JuriFactory;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import Sprint.WsProjeto.repositories.JuriRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@SpringBootTest
class JuriServiceTest {

    @MockBean
    JuriFactory juriFactory;

    @MockBean
    JuriRepository juriRepository;

    @MockBean
    AvaliacaoRepository avaliacaoRepository;

    @MockBean
    JuriDomainDTOAssembler juriDomainDTOAssembler;

    @InjectMocks
    JuriService juriService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void shouldFindJuriByCode() throws SQLException {
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

    @Test
    void teste( ) throws Exception {
        //arrange
        NewJuriInfoDTO juriInfoDto = mock(NewJuriInfoDTO.class);

        when(juriInfoDto.getCodArguente()).thenReturn(1);
        when(juriInfoDto.getCodPresidente()).thenReturn(2);
        when(juriInfoDto.getCodArguente()).thenReturn(3);

        Juri juri = mock(Juri.class);
        when(juriFactory.createJuri(juriInfoDto.getCodPresidente(), juriInfoDto.getCodOrientador(), juriInfoDto.getCodArguente())).thenReturn(juri);
        Juri savedOne = mock(Juri.class);
        when(juriRepository.save(juri)).thenReturn(savedOne);

        Avaliacao avaliacao =  mock(Avaliacao.class);
        Optional<Avaliacao> optional = Optional.of(avaliacao);
        when(avaliacaoRepository.findById(juriInfoDto.getCodAvaliacao())).thenReturn(optional);
        Avaliacao avaliacao1 = mock(Avaliacao.class);
        avaliacao1 = optional.get();
        optional.get().setJuri(savedOne);
        Avaliacao avaliacaoSavedone = mock(Avaliacao.class);
        when(avaliacaoRepository.save(avaliacao1)).thenReturn(avaliacaoSavedone);
        JuriDTO juriDTO = mock (JuriDTO.class);
        when(juriDomainDTOAssembler.toDto(savedOne)).thenReturn(juriDTO);
        //act
        JuriDTO act = juriService.createAndSaveJuri(juriInfoDto);
        //assert
        assertEquals(act,juriDTO);

    }



}