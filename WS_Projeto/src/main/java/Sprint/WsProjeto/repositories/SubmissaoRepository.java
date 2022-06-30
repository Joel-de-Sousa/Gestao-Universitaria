package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.SubmissaoJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.SubmissaoDomainDataAssembler;
import Sprint.WsProjeto.repositories.IRepository.ISubmissaoRepository;
import Sprint.WsProjeto.repositories.JDBC.SubmissaoJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.SubmissaoJPARepository;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Optional;

@Repository
public class SubmissaoRepository implements ISubmissaoRepository {
    @Autowired
    SubmissaoJDBCRepository submissaoJDBCRepository;

    @Autowired
    SubmissaoJDBCDomainDataAssembler submissaoJDBCDomainDataAssembler;

    public Submissao save(Submissao submissao) throws SQLException {
        SubmissaoJDBC submissaoJDBC = submissaoJDBCDomainDataAssembler.toJDBC(submissao);

        SubmissaoJDBC savedSubmissaoJDBC = submissaoJDBCRepository.save(submissaoJDBC);

        return submissaoJDBCDomainDataAssembler.toDomain(savedSubmissaoJDBC);
    }



    public Optional<Submissao> findById(int codSubmissao) throws SQLException {
        Optional<SubmissaoJDBC> opSubmissao = submissaoJDBCRepository.getById(codSubmissao);

        if ( opSubmissao.isPresent() ) {
            Submissao submissao = submissaoJDBCDomainDataAssembler.toDomain(opSubmissao.get());
            return Optional.of( submissao );
        }
        else
            return Optional.empty();
    }
}
