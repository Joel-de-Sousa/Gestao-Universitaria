package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import Sprint.WsProjeto.repositories.REST.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PropostaWebRepository {


    @Autowired
    PropostaRestRepository propostaRestRepository;

    public Optional<PropostaRestDTO> findPropostaByCode (int code){

        Optional<PropostaRestDTO> opCode = propostaRestRepository.findPropostaByCode(code);

        if(opCode.isPresent()){
            return opCode;
        }
        else
            return Optional.empty();
    }
}
