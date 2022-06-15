package wsproposta.proposta.repositories;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.repositories.REST.ProjetoRestRepository;

import java.util.Optional;

@Repository
public class ProjetoWebRepository {

    @Autowired
    ProjetoRestRepository projetoRestRepository;


    public Optional<ProjetoRestDto> createAndSaveProjeto( ProjetoRestDto projetoRestDto){

        Optional<ProjetoRestDto> opProjeto = projetoRestRepository.createAndSaveProjeto(projetoRestDto);
        if ( opProjeto.isPresent() ) {
            return opProjeto;
        }
        else
            return Optional.empty();
    }


}



