package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import Sprint.WsProjeto.repositories.REST.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PropostaWebRepository {


    @Autowired
    PropostaRestRepository propostaRestRepository;



    public Optional<PropostaRestDTO> findPropostaByCode (int codProposta){

        Optional<PropostaRestDTO> opCode = propostaRestRepository.findPropostaByCode(codProposta);

        return opCode;
    }

    public List<PropostaRestDTO> findAllPropostasAceitesByCodEdicao(int codEdicao) throws Exception {

        Optional<List<PropostaRestDTO>> lista = propostaRestRepository.findAllPropostasAceitesByCodEdicao(codEdicao);

        if(lista.isPresent()){
            return lista.get();
        } else
        throw new Exception("Não foram encontradas Propostas");
    }

    public List<PropostaRestDTO> findAllPropostasAceitesByNif (long nifOrganizacao) throws Exception {

        Optional<List<PropostaRestDTO>> lista = propostaRestRepository.findAllPropostasAceitesByNif (nifOrganizacao);

        if(lista.isPresent()){
            return lista.get();
        } else
            throw new Exception("Não foram encontradas Propostas desta Organizacao");
    }

}
