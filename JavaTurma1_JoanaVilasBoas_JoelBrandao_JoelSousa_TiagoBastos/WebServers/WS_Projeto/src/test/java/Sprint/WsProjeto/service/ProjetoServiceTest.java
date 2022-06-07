package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.ProjetoFactory;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProjetoServiceTest {

   @MockBean
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @MockBean
    PropostaWebRepository propostaWebRepository;

    @MockBean
    UtilizadorWebRepository utilizadorWebRepository;

    @MockBean
    ProjetoDTO projetoDTO;

    @MockBean
    ProjetoRepository projetoRepository;

    @MockBean
    ProjetoFactory projetoFactory;

    @MockBean
    Projeto projeto;

    @InjectMocks
    ProjetoService projetoService;

    @BeforeEach
    public void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindProjetoSearchingByCode() {
        //ARRANGE

        Optional<Projeto> opProjecto = Optional.of(projeto);

        when(projetoRepository.findById(1)).thenReturn(opProjecto);

        ProjetoDTO projetoDTO1 = mock(ProjetoDTO.class);
        when(projetoDomainDTOAssembler.toDto(opProjecto.get())).thenReturn(projetoDTO1);

        //ACT
        ProjetoDTO projeto1 = projetoService.findProjetoByCode(1);



        //ASSERT
        assertEquals(projetoDTO1,projeto1);

    }
}


