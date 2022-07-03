/*
package com.project.sprint.repository;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.datamodel.assembler.UtilizadorDomainDataAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.repository.JPA.UtilizadorJpaRepository;
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
class UtilizadorRepositoryTest {


    @MockBean
    UtilizadorDomainDataAssembler utilizadorDomainDataAssembler;

    @MockBean
    UtilizadorJpaRepository utilizadorJpaRepository;
    @InjectMocks
    UtilizadorRepository utilizadorRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    // Para ambos os testes funcionarem deve se comentar os NewUtilizadorInfoDto presentes na WsUtilizadorApplication
*/
/*
    @Test
    void shouldSaveUtilizadorWithCorrectAttributes (){
        // Arrange
        Utilizador utilizadorDouble = mock(Utilizador.class);
        UtilizadorJPA utilizadorJPADouble = mock(UtilizadorJPA.class);
        UtilizadorJPA utilizadorJPASavedDouble = mock(UtilizadorJPA.class);
        Utilizador utilizadorSavedDouble = mock(Utilizador.class);

        when(utilizadorDomainDataAssembler.toData(utilizadorDouble)).thenReturn(utilizadorJPADouble);
        when(utilizadorJpaRepository.save(utilizadorJPADouble)).thenReturn(utilizadorJPASavedDouble);
        when(utilizadorDomainDataAssembler.toDomain(utilizadorJPASavedDouble)).thenReturn(utilizadorSavedDouble);

        // Act
        Utilizador utilizador = utilizadorRepository.save(utilizadorDouble);

        // Assert
        assertEquals(utilizadorSavedDouble, utilizador);
    }
    @Test
    void shouldFindUtilizadorByIdUtilizadorWithCorrectAttributes() {

        // Arrange
        UtilizadorJPA utilizadorJPADouble = mock(UtilizadorJPA.class);
        Optional<UtilizadorJPA> optionalUtilizadorJPA = Optional.of(utilizadorJPADouble);
        when(utilizadorJpaRepository.findById(1)).thenReturn(optionalUtilizadorJPA);

        UtilizadorJPA utilizadorJPADouble2 = optionalUtilizadorJPA.get();
        Utilizador utilizadorDouble = mock(Utilizador.class);
        when(utilizadorDomainDataAssembler.toDomain(utilizadorJPADouble2)).thenReturn(utilizadorDouble);


        // Act
        Optional<Utilizador> optionalUtilizador = utilizadorRepository.findById(1);

        // Assert
        Optional<Utilizador> opUtilizadorDouble = Optional.of(utilizadorDouble);
        assertEquals(opUtilizadorDouble, optionalUtilizador);
    }*//*

}

*/
