package org.sprint3.model.repository;

import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.repository.REST.EdicaoRestRepository;

import java.util.List;
import java.util.Optional;

public class EdicaoWebRepository {
    EdicaoRestRepository edicaoRestRepository;

    public EdicaoWebRepository() {
        edicaoRestRepository=new EdicaoRestRepository();
    }

    public Optional<EdicaoRestDTO> findByEdicaoByCodRUC(int codRUC) {
        Optional<EdicaoRestDTO> opEdicao = edicaoRestRepository.findByEdicaoByCodRUC(codRUC);

        return opEdicao;
    }
    public List<EdicaoRestDTO> getListaEdicoesByCodRUC(int codRuc){

        Optional<List<EdicaoRestDTO>> lista =edicaoRestRepository.getAllEdicoesByCodRUC(codRuc);
        if (lista.isPresent()){
            List<EdicaoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            return null;
    }

    public boolean criarNovaEdicao(EdicaoRestDTO novo) throws Exception {
        return edicaoRestRepository.createEdicao(novo);

    }

    public List<EdicaoRestDTO> getListaEdicoes(){
        Optional<List<EdicaoRestDTO>> lista =edicaoRestRepository.getAllEdicoes();
        if (lista.isPresent()){
            List<EdicaoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            return null;
    }
}
