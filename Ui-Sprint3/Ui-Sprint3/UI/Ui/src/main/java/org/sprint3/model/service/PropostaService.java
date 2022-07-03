package org.sprint3.model.service;

import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.repository.PropostaWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropostaService {


    PropostaWebRepository propostaWebRepository;

    public PropostaService() {
        propostaWebRepository = new PropostaWebRepository();
    }

    public List<String> getAllPropostasPendentes (){
        Optional<List<PropostaRestDTO>> lista = propostaWebRepository.getAllPropostas();
        List<String> listaString = new ArrayList<>();

        if (lista.isPresent()){
            for (PropostaRestDTO a:lista.get()) {
                if (a.getEstado().equals("PENDENTE")) //ADICIONAR VERIFICACAO SO DE SAO PROPOSTAS DESTA EDICAO
                    listaString.add(String.format(a.getCodProposta()+"-"+a.getTitulo()));
            }
            return listaString;
        }else
            return null;
    }

    public List<String> getAllPropostasAceitesByCodEdicao(int codEdicao){
        Optional<List<PropostaRestDTO>> lista = propostaWebRepository.findAllPropostasAceitesByCodEdicao(codEdicao);
        List<String> listaString = new ArrayList<>();
        if (lista.isPresent()){

            for (PropostaRestDTO a:lista.get()) {
                if (a.getEstado().equals("ACEITE"))
                    listaString.add(String.format(a.getCodProposta()+"-"+a.getTitulo()));
            }
            return listaString;
        }else
            return null;
    }

    public PropostaRestDTO getPropostaById (int codProposta){
        Optional<PropostaRestDTO> proposta = propostaWebRepository.getPropostaById(codProposta);

        PropostaRestDTO proposta2 = proposta.get();

        return proposta2;
    }

    public boolean alterarEstadoAceite (int codProp, String estado) throws Exception {

        PropostaRestDTO propostaParcial = new PropostaRestDTO(codProp, estado);
        boolean alterou = propostaWebRepository.updateEstadoProposta(propostaParcial);
        return  alterou;
    }

    public boolean criarNovaProposta(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) throws Exception {
        PropostaRestDTO novo= new PropostaRestDTO(codUtilizador,nifOrganizacao,codEdicao,titulo,problema,objetivo);
        boolean valid=propostaWebRepository.criarNovaProposta(novo);
        return valid;
    }

}
