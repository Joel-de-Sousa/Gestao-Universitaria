package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.repositories.REST.EdicaoRestRepository;
import Sprint.WsProjeto.repositories.REST.PropostaRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoWebRepository {


    @Autowired
    EdicaoRestRepository edicaoRestRepository;



    public Optional<EdicaoRestDTO> findEdicaoByCode (int codEdicao){

        Optional<EdicaoRestDTO> opCode = edicaoRestRepository.findEdicaoByCode(codEdicao);

        if(opCode.isPresent()){
            return opCode;
        }
        else
            return Optional.empty();
    }

    public List<EdicaoRestDTO> getListaEdicoesByCodRUC(int codRuc) throws Exception {

        Optional<List<EdicaoRestDTO>> lista =edicaoRestRepository.getAllEdicoesByCodRUC(codRuc);
        if (lista.isPresent()){
            List<EdicaoRestDTO> listaDTO=lista.get();
            return listaDTO;
        }else
            throw new Exception("Não foram encontradas Edições");
    }
}
