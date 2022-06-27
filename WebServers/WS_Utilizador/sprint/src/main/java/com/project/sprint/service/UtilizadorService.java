package com.project.sprint.service;

import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.DTO.assembler.UtilizadorDomainDTOAssembler;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.domain.factories.IUtilizadorFactory;
import com.project.sprint.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {
    @Autowired
    IUtilizadorFactory utilizadorFactory;
    @Autowired
    UtilizadorRepository utilizadorRepository;
    @Autowired
    UtilizadorDomainDTOAssembler utilizadorDomainDTOAssembler;

    public UtilizadorService() {
    }

    /**
     * Método para criação e gravação de um novo Utilizador.
     * O método comunica com a Factory para criar Utilizador e depois com o Repository para gravação na BD.
     *
     * @param info DTO de entrada para a criaçao de um Utilizador
     * @return um UtilizadorDTO
     */
    public UtilizadorDTO createAndSaveUtilizador(NewUtilizadorInfoDTO info) {
        Utilizador utilizador = utilizadorFactory.createUtilizador(info.getNome(), info.getSobrenome(), info.getEmail(), Utilizador.TipoUtilizador.valueOf(info.getTipoUtilizador()));
        Utilizador utilizadorSaved = utilizadorRepository.save(utilizador);
        UtilizadorDTO utilizadorDTO = utilizadorDomainDTOAssembler.toDTO(utilizadorSaved);
        return utilizadorDTO;
    }

    /**
     *  Método para retornar um Utilizador quando pesquisado pelo seu ID. O método comunica com o Repository para encontrar o ID introduzido.
     *
     * @param id o ID do Utilizador
     *
     * @return Um UtilizadorDTO no caso de a pesquisa ser bem sucedida e null se nao encontar resultados.
     */
    public UtilizadorDTO getUtilizadorByID(int id) {

        Optional<Utilizador> opUtilizador = utilizadorRepository.findById(id);

        if (opUtilizador.isPresent()) {
            Utilizador utilizador = opUtilizador.get();
            UtilizadorDTO utilizadorDTO = utilizadorDomainDTOAssembler.toDTO(utilizador);
            return utilizadorDTO;
        } else return null;
    }


    public List<UtilizadorDTO> findAllByTipoUtilizador(String tipoUtilizador) {

        List<Utilizador> listUtilizador = utilizadorRepository.findAllByTipoUtilizador(tipoUtilizador);

        List<UtilizadorDTO> listUtilizadorDTO = new ArrayList<>();
        for (Utilizador utilizador : listUtilizador) {
            UtilizadorDTO utilizadorDTO = utilizadorDomainDTOAssembler.toDTO(utilizador);
            listUtilizadorDTO.add(utilizadorDTO);
        }
        return listUtilizadorDTO;

    }
}
