package org.sprint3.model.repository;

import org.sprint3.model.DTO.ConviteRestDTO;
import org.sprint3.model.DTO.JuriRestDTO;
import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.repository.REST.ProjetoRestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoWebRepository {

    ProjetoRestRepository projetoRestRepository;

    public ProjetoWebRepository() {
        projetoRestRepository = new ProjetoRestRepository();
    }

    public Optional<ProjetoRestDTO> getProjetoByCodEstudante(int codEstudante) {
        Optional<ProjetoRestDTO> projeto = projetoRestRepository.findByCodEstudante(codEstudante);

        return projeto;
    }

    public Optional<ProjetoRestDTO> getProjetoByCodOrientador(int codOrientador) {
        Optional<ProjetoRestDTO> projeto = projetoRestRepository.findByCodOrientador(codOrientador);

        return projeto;
    }

    public Optional<ProjetoRestDTO> getProjetoByCodProjeto (int CodProjeto) {
        Optional<ProjetoRestDTO> projeto = projetoRestRepository.findByCodProjeto (CodProjeto);

        return projeto;
    }

    public Optional<List<ProjetoRestDTO>> getAllProjetos() {
        Optional<List<ProjetoRestDTO>> lista = projetoRestRepository.findAllProjetos();

        return lista;
    }

    public Optional<List<ProjetoRestDTO>> getAllProjetosDeEdicao(int edicao) {
        Optional<List<ProjetoRestDTO>> lista = projetoRestRepository.findAllProjetosByEdicao(edicao);

        return lista;
    }
    public Optional<List<ProjetoRestDTO>> getListaProjetosByCodOrientador (int codOrientador) {
        Optional<List<ProjetoRestDTO>> lista = projetoRestRepository.findAllProjetosByCodOrientador (codOrientador);

        return lista;
    }

    public boolean createJuri (JuriRestDTO juri) throws Exception {
        boolean create= projetoRestRepository.createJuri (juri);
        return create;
    }
}
