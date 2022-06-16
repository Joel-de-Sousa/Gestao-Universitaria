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


    public boolean createAndSaveProjeto(ProjetoRestDto projetoRestDto) throws Exception {

        boolean criado = projetoRestRepository.createAndSaveProjeto(projetoRestDto);

        return criado;
    }
}



