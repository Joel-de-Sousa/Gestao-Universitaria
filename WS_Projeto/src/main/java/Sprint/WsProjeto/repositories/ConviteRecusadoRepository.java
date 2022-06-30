package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.ConviteRecusadoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ConviteJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.ConviteRecusadoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ConviteDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.repositories.JDBC.ConviteRecusadoJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.ConviteJPARepository;
import Sprint.WsProjeto.repositories.JPA.ConviteRecusadoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public class ConviteRecusadoRepository {

    @Autowired
    ConviteRecusadoJDBCRepository conviteJDBCRepository;

    @Autowired
    ConviteJDBCDomainDataAssembler conviteJDBCDomainDataAssembler;



  /*  public Convite save(Convite convite) {
        ConviteRecusadoJPA conviteJPA = conviteDomainDataAssembler.toDataRecusado(convite);

        ConviteRecusadoJPA savedConviteJPA = conviteJPARepository.save(conviteJPA);

        return conviteDomainDataAssembler.toDomainRecusado(savedConviteJPA);
    }*/

    public Convite save(Convite convite) throws SQLException {
        ConviteRecusadoJDBC conviteJDBC = conviteJDBCDomainDataAssembler.toJDBCRecusado(convite);

        ConviteRecusadoJDBC savedConviteJDBC = conviteJDBCRepository.save(conviteJDBC);

        return conviteJDBCDomainDataAssembler.toDomainRecusado(savedConviteJDBC);
    }


    public Optional<Convite> findById(int codConvite) throws SQLException {
        Optional<ConviteRecusadoJDBC> opConvite = conviteJDBCRepository.getById(codConvite);

        if ( opConvite.isPresent() ) {
            Convite convite = conviteJDBCDomainDataAssembler.toDomainRecusado(opConvite.get());
            return Optional.of( convite );
        }
        else
            return Optional.empty();
    }
}
