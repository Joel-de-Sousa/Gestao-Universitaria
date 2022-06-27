package org.sprint3.model.repository;

import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.REST.PropostaRestRepository;

import java.util.List;
import java.util.Optional;

public class PropostaWebRepository {

    PropostaRestRepository propostaRestRepository;

    public PropostaWebRepository() {
        propostaRestRepository = new PropostaRestRepository();
    }

    public Optional<List<PropostaRestDTO>> getAllPropostas() {
        Optional<List<PropostaRestDTO>> lista = propostaRestRepository.findAllPropostas();

        return lista;
    }

    public Optional<List<PropostaRestDTO>> findAllPropostasAceitesByCodEdicao(int codEdicao) {
        Optional<List<PropostaRestDTO>> lista = propostaRestRepository.findAllPropostasAceitesByCodEdicao(codEdicao);

        return lista;
    }

    public Optional<PropostaRestDTO> getPropostaById (int codProposta){
        Optional<PropostaRestDTO> proposta = propostaRestRepository.findById(codProposta);

        return proposta;
    }

    public boolean updateEstadoProposta(PropostaRestDTO propostaParcial) throws Exception {

        boolean alterou = propostaRestRepository.updateEstadoProposta(propostaParcial);
        return  alterou;
    }

    public boolean criarNovaProposta(PropostaRestDTO novo) throws Exception {
        return propostaRestRepository.createProposta(novo);
    }
}
