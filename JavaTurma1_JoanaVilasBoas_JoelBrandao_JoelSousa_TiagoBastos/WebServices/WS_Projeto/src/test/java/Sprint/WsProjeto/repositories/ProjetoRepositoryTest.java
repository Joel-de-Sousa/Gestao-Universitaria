package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
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
class ProjetoRepositoryTest {

    @MockBean
    ProjetoDomainDataAssembler projetoDomainDataAssembler;

    @MockBean
    ProjetoJPARepository projetoJPARepository;

    @MockBean
    Projeto projeto;

    @MockBean
    ProjetoJPA projetoJPA;

    @InjectMocks
    ProjetoRepository projetoRepository;

    @BeforeEach
    public void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveProjeto(){
        when(projetoDomainDataAssembler.toData(projeto)).thenReturn(projetoJPA);

        ProjetoJPA savedProjetoJpa = mock(ProjetoJPA.class);
        when(projetoJPARepository.save(projetoJPA)).thenReturn(savedProjetoJpa);

        when(projetoDomainDataAssembler.toDomain(savedProjetoJpa)).thenReturn(projeto);

        Projeto savedProjeto = projetoRepository.save(projeto);

        assertEquals(savedProjeto,projeto);
    }
    @Test
    void shouldFindSpecificProjetoSearchingByCode(){
        when(projetoJPA.getCodProjeto()).thenReturn(1);
        when(projetoJPA.getCodEstudante()).thenReturn(2);
        when(projetoJPA.getCodOrientador()).thenReturn(3);
        when(projetoJPA.getCodProposta()).thenReturn(4);

        Optional<ProjetoJPA> optionalProjetoJPA = Optional.of(projetoJPA);
        when(projetoJPARepository.findById(1)).thenReturn(optionalProjetoJPA);

        when(projetoDomainDataAssembler.toDomain(optionalProjetoJPA.get())).thenReturn(projeto);

        Optional<Projeto> opProjetoAct = projetoRepository.findById(1);

        assertEquals(opProjetoAct,Optional.of(projeto));
    }




}