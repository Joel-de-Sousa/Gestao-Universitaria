package org.sprint3.controller;

import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.service.PropostaService;

import java.util.List;

public class PropostaController {

    PropostaService propostaService;

    public PropostaController() {
        propostaService = new PropostaService();
    }

    public List<String> getAllPropostasPendentes() {
        List<String> lista = propostaService.getAllPropostasPendentes();
        return lista;
    }

    public List<String> getAllPropostasAceitesByCodEdicao(int codEdicao) {
        List<String> lista = propostaService.getAllPropostasAceitesByCodEdicao(codEdicao);
        return lista;
    }

    public PropostaRestDTO getPropostaById(int codProposta) {
        PropostaRestDTO proposta = propostaService.getPropostaById(codProposta);

        return proposta;
    }

    public boolean alterarEstadoAceite(int codProp, String estado) throws Exception {

        boolean alterou = propostaService.alterarEstadoAceite(codProp, estado);
        return alterou;
    }

    public boolean criarNovaProposta(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) throws Exception {
        boolean create = propostaService.criarNovaProposta(codUtilizador, nifOrganizacao, codEdicao, titulo, problema, objetivo);
        return create;
    }
}
