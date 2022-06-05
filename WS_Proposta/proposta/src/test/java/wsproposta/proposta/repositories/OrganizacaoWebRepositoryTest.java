package wsproposta.proposta.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.repositories.REST.OrganizacaoRestRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrganizacaoWebRepositoryTest {

    @MockBean
    OrganizacaoRestRepository organizacaoRestRepository;

    @InjectMocks
    OrganizacaoWebRepository organizacaoWebRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Teste Get Organizacao by NIF")
    void shouldGetOrganizacaoByNifOrganizacaoWithCorrectAttributes() {

        // Arrange
        OrganizacaoRestDTO organizacaoRestDTODouble = mock(OrganizacaoRestDTO.class);
        Optional<OrganizacaoRestDTO> opOrganizacaoRestDTODouble = Optional.of(organizacaoRestDTODouble);
        when(opOrganizacaoRestDTODouble.get().getNr()).thenReturn(257837248L);

        when(organizacaoRestRepository.findOrganizacaoByNifOrganizacao(257837248)).thenReturn(opOrganizacaoRestDTODouble);

        // Act
        Optional<OrganizacaoRestDTO> opOrganizacaoRestDTO = organizacaoWebRepository.findOrganizacaoByNifOrganizacao(257837248);

        // Assert
        assertEquals(opOrganizacaoRestDTODouble, opOrganizacaoRestDTO);
    }



}