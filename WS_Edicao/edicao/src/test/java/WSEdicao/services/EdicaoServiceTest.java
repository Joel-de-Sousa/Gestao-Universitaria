/*package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.EdicaoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.repositories.EdicaoRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EdicaoServiceTest {


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

    @Test
    void shouldCreateAEdicaoWithCorrectAttributes() {
        // Arrange
        Uc uc = new Uc(1,"POOJ","ProgramacaoOrientadaAObjectos");
        AnoLetivo anoLetivo = new AnoLetivo(1,"2015");

        //when(edicao.getUc()).thenReturn("2015-2016");

        when(edicaoFactory.createEdicao(uc,anoLetivo)).thenReturn(edicao);

        when(edicaoRepository.save(edicao)).thenReturn(edicao);

        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);

        // Act
        EdicaoDTO edicao1 = edicaoService.createAndSaveEdicao(1,1);

        // Assert
        assertEquals(edicaoDTO, edicao1);
    }

    @Test
    void shouldFindSpecificEdicaoSearchingById() {
        //Arrange
        when(edicaoDTO.getCodEdicao()).thenReturn(1);
        when(edicaoDTO.getAno()).thenReturn("2015-2016");

        Optional<EdicaoDTO> opEdicao = Optional.of(edicaoDTO);

        when(edicaoRepository.findBycodEdicao(1)).thenReturn(opEdicao);

        // Act
        Optional<EdicaoDTO> edicao1 = edicaoService.getEdicaoByCode(1);
        String sANo = edicao1.get().getAno();

        // Assert
        assertEquals(edicao1, opEdicao);
        assertEquals(sANo, "2015-2016");
    }

    @Test
    void shouldFindEveryEdicaoCreatedCorrectly() {

        Edicao edicaoDouble = mock(Edicao.class);
        when(edicaoDouble.getAno()).thenReturn("2015-2016");

        Edicao edicaoDouble2 = mock(Edicao.class);
        when(edicaoDouble2.getAno()).thenReturn("2017-2018");


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