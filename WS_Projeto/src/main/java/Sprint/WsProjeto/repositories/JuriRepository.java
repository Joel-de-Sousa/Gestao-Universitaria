package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.JuriJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.JDBC.JuriJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JuriRepository {

    @Autowired
    JuriJDBCRepository juriJDBCRepository;

    @Autowired
    JuriJDBCDomainDataAssembler juriJDBCDomainDataAssembler;



   /* public Juri save(Juri juri) {
        JuriJPA juriJPA = juriDomainDataAssembler.toData(juri);

        JuriJPA savedJuriJPA = juriJPARepository.save(juriJPA);

        return juriDomainDataAssembler.toDomain(savedJuriJPA);
    }*/
    public Juri save(Juri juri) throws SQLException {
        JuriJDBC juriJDBC = juriJDBCDomainDataAssembler.toJDBC(juri);

        JuriJDBC savedJuriJDBC = juriJDBCRepository.save(juriJDBC);

        return juriJDBCDomainDataAssembler.toDomain(savedJuriJDBC);
    }

    public Optional<Juri> findById(int codJuri) throws SQLException {
        Optional<JuriJDBC> opJuri = juriJDBCRepository.getById(codJuri);

        if ( opJuri.isPresent() ) {
            Juri juri = juriJDBCDomainDataAssembler.toDomain(opJuri.get());
            return Optional.of( juri );
        }
        else
            return Optional.empty();
    }
}
