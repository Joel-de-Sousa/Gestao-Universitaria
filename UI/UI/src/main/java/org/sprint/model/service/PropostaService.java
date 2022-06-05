package org.sprint.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.PropostaWebRepository;

import java.util.ArrayList;
import java.util.List;

public class PropostaService {

    PropostaWebRepository propostaWebRepository;


    public PropostaService() {
        propostaWebRepository=new PropostaWebRepository();
    }





    public boolean criarNovaProposta(int codUtilizador, int nifOrganizacao, int codEdicao, String titulo, String problema, String objetivo) throws Exception {
        PropostaRestDTO novo= new PropostaRestDTO(codUtilizador,nifOrganizacao,codEdicao,titulo,problema,objetivo);
        boolean valid=propostaWebRepository.criarNovaProposta(novo);
        return valid;
    }
}
