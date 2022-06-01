package WSEdicao.repositories;

import WSEdicao.datamodel.UcJpa;
import WSEdicao.datamodel.assemblers.UcDomainDataAssembler;
import WSEdicao.domain.entities.Uc;
import WSEdicao.repositories.jpa.UcJpaRepository;
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
class UcRepositoryTest {

    @MockBean
    UcDomainDataAssembler ucDomainDataAssembler;

    @MockBean
    UcJpaRepository ucJpaRepository;

    @MockBean
    Uc uc;
    @MockBean
    UcJpa ucJpa;

    @InjectMocks
    UcRepository ucRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveUc() {
        //Arrange
        when(ucDomainDataAssembler.toData(uc)).thenReturn(ucJpa);

        UcJpa savedUcJpa = mock(UcJpa.class);
        when(ucJpaRepository.save(ucJpa)).thenReturn(savedUcJpa);

        when(ucDomainDataAssembler.toDomain(savedUcJpa)).thenReturn(uc);

        //Act
        Uc savedUc = ucRepository.save(uc);

        //Assert
        assertEquals(savedUc,uc);
    }

    @Test
    void shouldFindSpecificUcSearchingById() {
        //Arrange
        when(ucJpa.getSSigla()).thenReturn("POOJ");
        when(ucJpa.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        Optional<UcJpa> opUcJpa = Optional.of(ucJpa);
        when(ucJpaRepository.findBycodUc(1)).thenReturn(opUcJpa);

        when(ucDomainDataAssembler.toDomain(opUcJpa.get())).thenReturn(uc);

        //Act
        Optional<Uc> opUcAct = ucRepository.findBycodUc(1);

        //Assert
        assertEquals(opUcAct,Optional.of(uc));
    }

    @Test
    void findAll() {
        //Arrange
        UcJpa ucJpa1 = mock(UcJpa.class);
        when(ucJpa1.getSSigla()).thenReturn("POOJ");
        when(ucJpa1.getSDenominacao()).thenReturn("ProgramacaoOrientadaAObjetos");

        UcJpa ucJpa2 = mock(UcJpa.class);
        when(ucJpa2.getSSigla()).thenReturn("LDP");     //não há necessidade de atribuir valores, o objetivo do teste
        when(ucJpa2.getSDenominacao()).thenReturn("LaboratorioDeProgramacao"); //é encontrar os locais existentes, por isso não é preciso!

        List<UcJpa> listUcJpa = new ArrayList<>();
        listUcJpa.add(ucJpa1);
        listUcJpa.add(ucJpa2);

        when(ucJpaRepository.findAll()).thenReturn(listUcJpa);

        List<Uc> listUc = new ArrayList<>();
        for(UcJpa ucJpa : listUcJpa){
            when(ucDomainDataAssembler.toDomain(ucJpa)).thenReturn(uc);
            listUc.add(uc);
        }

        //Act
        List<Uc> listUcAct = ucRepository.findAll();

        //Assert
        assertEquals(listUcAct,listUc);
        assertTrue(listUcAct.size()==2);
    }

}