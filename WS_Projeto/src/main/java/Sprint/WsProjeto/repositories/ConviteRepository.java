package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.ConviteJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ConviteJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ConviteDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.JDBC.ConviteJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.ConviteJPARepository;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ConviteRepository {

    @Autowired
    ConviteJDBCRepository conviteJDBCRepository;

    @Autowired
    ConviteJDBCDomainDataAssembler conviteJDBCDomainDataAssembler;



    public Convite save(Convite convite) throws SQLException {
        ConviteJDBC conviteJDBC = conviteJDBCDomainDataAssembler.toJDBC(convite);

        ConviteJDBC savedConviteJDBC = conviteJDBCRepository.save(conviteJDBC);

        return conviteJDBCDomainDataAssembler.toDomain(savedConviteJDBC);
    }

    public Optional<Convite> findById(int codConvite) throws SQLException {
        Optional<ConviteJDBC> opConvite = conviteJDBCRepository.getById(codConvite);

        if ( opConvite.isPresent() ) {
            Convite convite = conviteJDBCDomainDataAssembler.toDomain(opConvite.get());
            return Optional.of( convite );
        }
        else
            return Optional.empty();
    }

    public void deleteByCodConvite(int codConvite) throws SQLException {
        conviteJDBCRepository.remove(codConvite);
    }
}
