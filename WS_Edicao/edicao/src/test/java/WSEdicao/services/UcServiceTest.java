
package WSEdicao.services;

import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.UcFactory;
import WSEdicao.dto.UcDTO;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class UcServiceTest {

    @MockBean
    UcDomainDTOAssembler ucDTOAssembler;

    @MockBean
    UcRepository ucRepository;

    @MockBean
    UcFactory ucFactory;

    @MockBean
    UcDTO ucDTO;

    @MockBean
    Uc uc;

    @InjectMocks
    UcService ucService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAUcWithCorrectAttributes() {
        // Arrange
        when(uc.getSigla()).thenReturn("POOJ");
        when(uc.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        when(ucFactory.createUc("POOJ", "ProgramacaoOrientadaAObjetos")).thenReturn(uc);

        when(ucRepository.save(uc)).thenReturn(uc);

        UcDTO ucDTO = ucDTOAssembler.toDTO(uc);

        // Act
        UcDTO uc1 = ucService.createAndSaveUc("POOJ", "ProgramacaoOrientadaAObjetos");

        // Assert
        assertEquals(ucDTO, uc1);
    }


   @Test
    void shouldFindSpecificUcSearchingById() {
        // Arrange
        when(ucDTO.getCodUc()).thenReturn(1);
        when(ucDTO.getSigla()).thenReturn("POOJ");
        when(ucDTO.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Optional<UcDTO> opUc = Optional.of(ucDTO);

        when(ucRepository.findBycodUc(1)).thenReturn(opUc);

        // Act
        Optional<UcDTO> uc1 = ucService.getUcByCode(1);
        String sigla = uc1.get().getSigla();
        String sDenominacao = uc1.get().getDenominacao();

        // Assert
        assertEquals(uc1, opUc);

        assertEquals(sigla, "POOJ");
        assertEquals(sDenominacao, "ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldFindEveryUcCreatedCorrectly() {

        Uc ucDouble = mock(Uc.class);
        when(ucDouble.getSigla()).thenReturn("POOJ");
        when(ucDouble.getDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Uc ucDouble2 = mock(Uc.class);
        when(ucDouble2.getSigla()).thenReturn("LDP");
        when(ucDouble2.getDenominacao()).thenReturn("LaboratorioDeProgramacao");

        List<Uc> listUcAux = new ArrayList<>();
        listUcAux.add(ucDouble);
        listUcAux.add(ucDouble2);

        when(ucRepository.findAll()).thenReturn(listUcAux);

        List<UcDTO> listaDto=new ArrayList<>();
        for (Uc uc:listUcAux) {
            UcDTO ucDTO = ucDTOAssembler.toDTO(uc);
            listaDto.add(ucDTO);
        }

        // Act
        List<UcDTO> listUcAct = ucService.getAllUc();

        // Assert
        assertEquals(listaDto, listUcAct);
        assertEquals(2, listUcAct.size());
    }
}

