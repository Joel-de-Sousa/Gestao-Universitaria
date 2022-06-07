package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.repositories.REST.UtilizadorRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UtilizadorWebRepository {

    @Autowired
    UtilizadorRestRepository utilizadorRestRepository;

    public Optional<UtilizadorRestDTO> findUtilizadorByCode (int codUtilizador){

        Optional<UtilizadorRestDTO> opCode = utilizadorRestRepository.findUtilizadorByCode(codUtilizador);

        if(opCode.isPresent()){
            return opCode;
        }
        else
            return Optional.empty();
    }



}
