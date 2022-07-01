package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.AnoLetivoFactory;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.domain.factories.UcFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.UcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EdicaoServiceTest {

    @MockBean
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @MockBean
    UcDomainDTOAssembler ucDTOAssembler;

    @MockBean
    AnoLetivo anoLetivo;

    @MockBean
    Uc uc;

    @MockBean
    AnoLetivoFactory anoLetivoFactory;

    @MockBean
    UcFactory ucFactory;

    @MockBean
    UcRepository ucRepository;

    @MockBean
    AnoLetivoRepository anoLetivoRepository;

    @MockBean
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    @MockBean
    EdicaoRepository edicaoRepository;

    @MockBean
    EdicaoFactory edicaoFactory;

    @MockBean
    EdicaoDTO edicaoDTO;

    @MockBean
    Edicao edicao;

    @InjectMocks
    EdicaoService edicaoService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    /*@Test
    void shouldCreateAEdicaoWithCorrectAttributes() {
        // Arrange
        UcDTO ucDTO = mock(UcDTO.class);
        AnoLetivoDTO anoLetivoDTO = mock(AnoLetivoDTO.class);
        when(ucRepository.findBycodUc(1)).thenReturn(Optional.of(ucDTO));
        when(anoLetivoRepository.findBycodAnoLetivo(1)).thenReturn(Optional.of(anoLetivoDTO));
        Optional<UcDTO> optionalUcDTO = ucRepository.findBycodUc(1);
        Optional<AnoLetivoDTO> optionalAnoLetivoDTO = anoLetivoRepository.findBycodAnoLetivo(1);
        UcDTO ucDTO = new UcDTO(1,"POG","PogChampion");
        Uc savedUc = ucDTOAssembler.toDomain(ucDTO);
        ucRepository.save(savedUc);

        AnoLetivoDTO anoLetivoDTO = new AnoLetivoDTO(1,"2015");
        AnoLetivo savedAnoLetivo = anoLetivoDTOAssembler.toDomain(anoLetivoDTO);
        anoLetivoRepository.save(savedAnoLetivo);

        Edicao edicao = edicaoFactory.createEdicao(uc, anoLetivo);
        Edicao savedEdicao = edicaoRepository.save(edicao);
        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(savedEdicao);

        // Act
        EdicaoDTO edicao1 = edicaoService.createAndSaveEdicao(1, 1);

        // Assert
        assertEquals(edicaoDTO, edicao1);
    }

    /*@Test
    void shouldFindSpecificEdicaoSearchingById() {
        //Arrange
        when(edicaoDTO.getCodEdicao()).thenReturn(1);
        when(edicaoDTO.getCodUc()).thenReturn(1);
        when(edicaoDTO.getCodAnoLetivo()).thenReturn(1);

        Optional<EdicaoDTO> opEdicao = Optional.of(edicaoDTO);

        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);

        // Act
        Optional<EdicaoDTO> edicao1 = edicaoService.getEdicaoByCode(1);


        // Assert
        assertEquals(edicao1, opEdicao);
    }*/

    /*@Test
    void shouldFindEveryEdicaoCreatedCorrectly() {

        Edicao edicaoDouble = mock(Edicao.class);
        when(edicaoDouble.getCodEdicao()).thenReturn(1);

        Edicao edicaoDouble2 = mock(Edicao.class);
        when(edicaoDouble2.getCodEdicao()).thenReturn(1);


        List<Edicao> listEdicaoAux = new ArrayList<>();
        listEdicaoAux.add(edicaoDouble);
        listEdicaoAux.add(edicaoDouble2);

        when(edicaoRepository.findAll()).thenReturn(listEdicaoAux);

        List<EdicaoDTO> listaDto = new ArrayList<>();
        for (Edicao edicao : listEdicaoAux) {
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);
            listaDto.add(edicaoDTO);
        }


        // Act
        List<EdicaoDTO> listEdicaoAct = edicaoService.getAllEdicao();

        // Assert
        assertEquals(listaDto, listEdicaoAct);
        assertEquals(2, listEdicaoAct.size());
    }*/
}