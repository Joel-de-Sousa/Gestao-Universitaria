package com.project.sprint.repository;

import com.project.sprint.datamodel.UtilizadorJPA;
import com.project.sprint.datamodel.assembler.UtilizadorDomainDataAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.repository.JPA.UtilizadorJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/*

@SpringBootTest
class IUtilizadorRepositoryTest {

    @MockBean
    UtilizadorDomainDataAssembler utilizadorDomainDataAssembler;

    @MockBean
    Utilizador utilizador;

    @MockBean
    UtilizadorJPA utilizadorJPA;

    @MockBean
    UtilizadorRepository utilizadorRepository;

    @MockBean
    UtilizadorJpaRepository utilizadorJpaRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {

        when(utilizadorDomainDataAssembler.toData(utilizador)).thenReturn(utilizadorJPA);

        UtilizadorJPA savedUtilizadorJpa = mock(UtilizadorJPA.class);
        when(savedUtilizadorJpa.getEmail()).thenReturn("teste@gmail.com");
        when(utilizadorJpaRepository.existsByEmail(savedUtilizadorJpa.getEmail())).thenReturn(false);
        when(utilizadorJpaRepository.save(utilizadorJPA)).thenReturn(savedUtilizadorJpa);

        when(utilizadorDomainDataAssembler.toDomain(savedUtilizadorJpa)).thenReturn(utilizador);

        Utilizador savedUtilizador = utilizadorRepository.save(utilizador);

        assertEquals(savedUtilizador,savedUtilizadorJpa);

    }

    @Test
    void findById() {
    }
}

*/


