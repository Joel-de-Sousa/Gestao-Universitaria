package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JuriRepository {

    @Autowired
    JuriJPARepository juriJPARepository;

    @Autowired
    JuriDomainDataAssembler juriDomainDataAssembler;



    public Juri save(Juri juri) {
        JuriJPA juriJPA = juriDomainDataAssembler.toData(juri);

        JuriJPA savedJuriJPA = juriJPARepository.save(juriJPA);

        return juriDomainDataAssembler.toDomain(savedJuriJPA);
    }

    public Optional<Juri> findById(int codJuri) {
        Optional<JuriJPA> opJuri = juriJPARepository.findById(codJuri);

        if ( opJuri.isPresent() ) {
            Juri juri = juriDomainDataAssembler.toDomain(opJuri.get());
            return Optional.of( juri );
        }
        else
            return Optional.empty();
    }
}
