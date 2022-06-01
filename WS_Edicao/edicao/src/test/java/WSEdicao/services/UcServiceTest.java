package WSEdicao.services;

import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.UcFactory;
import WSEdicao.repositories.UcRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UcServiceTest {

    @MockBean
    UcRepository ucRepository;

    @MockBean
    UcFactory ucFactory;

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
        when(uc.getSSigla()).thenReturn("POOJ");
        when(uc.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        when( ucFactory.createUc("POOJ", "ProgramacaoOrientadaAObjetos")).thenReturn(uc);

        when(ucRepository.save( uc )).thenReturn(uc);

        // Act
        Uc uc1 = ucService.createAndSaveUc("POOJ", "ProgramacaoOrientadaAObjetos");

        String sSigla = uc1.getSSigla();
        String sDenominacao = uc1.getSDenominacao();

        // Assert
        assertEquals(uc,uc1);

        assertEquals(sSigla, "POOJ");
        assertEquals(sDenominacao, "ProgramacaoOrientadaAObjetos");
    }

    /*@Test
    void shouldFindSpecificUcSearchingById() {
        // Arrange
        when(uc.getSSigla()).thenReturn("POOJ");
        when(uc.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Optional<Uc> opUc = Optional.of(uc);

        when(ucRepository.findBycodUc(1)).thenReturn(opUc);

        // Act
        Optional<Uc> uc1 = ucService.getUcByCode(1);

        String sSigla = uc1.get().getSSigla();
        String sDenominacao = uc1.get().getSDenominacao();

        // Assert
        assertEquals(uc1,opUc);

        assertEquals(sSigla, "POOJ");
        assertEquals(sDenominacao, "ProgramacaoOrientadaAObjetos");
    }

    @Test
    void shouldFindEveryUcCreatedCorrectly() {

        Uc ucDouble = mock(Uc.class);
        when(ucDouble.getSSigla()).thenReturn("POOJ");
        when(ucDouble.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Uc ucDouble2 = mock(Uc.class);
        when(ucDouble2.getSSigla()).thenReturn("LDP");
        when(ucDouble2.getSDenominacao()).thenReturn("LaboratorioDeProgramacao");
        List<Uc> listUcAux = new ArrayList<>();
        listUcAux.add(ucDouble);
        listUcAux.add(ucDouble2);

        when(ucRepository.findAll()).thenReturn(listUcAux);

        // Act
        List<Uc> listUcAct = ucService.getAllUc();

        // Assert
        assertEquals(listUcAux, listUcAct);
        assertTrue(listUcAct.size()==2);
    }*/

}