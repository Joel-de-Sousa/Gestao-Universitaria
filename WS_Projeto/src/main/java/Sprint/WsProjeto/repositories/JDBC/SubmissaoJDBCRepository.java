package Sprint.WsProjeto.repositories.JDBC;

import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.SubmissaoJDBCDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Submissao;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.IOException;
import java.sql.*;

import java.util.List;
import java.util.Optional;

@Repository
public class SubmissaoJDBCRepository {

    @Value("${jdbc.oracle.upskillbd.url}")
    private String jdbcUrl;
    @Value("${jdbc.oracle.upskillbd.username}")
    private String username;
    @Value("${jdbc.oracle.upskillbd.password}")
    private String password;
    private Connection connection;

    @Autowired
    private SubmissaoJDBCDomainDataAssembler submissaoJDBCDomainDataAssembler;

    public SubmissaoJDBCRepository() throws IOException {
        connection = null;
    }

    private Optional<SubmissaoJDBC> getById(int codSubmissao) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncobtersubmissaoporid(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, codSubmissao);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();

        List<SubmissaoJDBC> lSubmissaoJDBCs=submissaoJDBCDomainDataAssembler.toJDBC(cachedRowSet);
        if(lSubmissaoJDBCs.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(lSubmissaoJDBCs.get(0));
    }

    public List<SubmissaoJDBC> findAll() throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{call prcobtertodassubmissoes(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return submissaoJDBCDomainDataAssembler.toJDBC(cachedRowSet);
    }

    public SubmissaoJDBC save(SubmissaoJDBC submissao) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncadicionarmedico(?,?,?,?,?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.setInt(2, submissao.getCodSubmissao());
        callableStatement.setString(3, submissao.getTitulo());
        callableStatement.setString(4, submissao.getUrlFicheiro());

        callableStatement.setString(5, submissao.getLinguagemFicheiro());
        callableStatement.setInt(6, submissao.getEstado());

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return submissaoJDBCDomainDataAssembler.toJDBC(cachedRowSet).get(0);
    }

    public Optional<SubmissaoJDBC> remove(SubmissaoJDBC submissaoJDBC) throws SQLException {
        Optional<SubmissaoJDBC> optional=getById(submissaoJDBC.getCodSubmissao());

        if(!optional.isPresent()) {
            return Optional.empty();
        }

        abrirLigacao();

        CallableStatement callableStatement=connection.prepareCall("{call prceliminarsubmissao(?)}");

        callableStatement.setInt(1, submissaoJDBC.getCodSubmissao());

        callableStatement.execute();
        fecharLigacao();

        return optional;
    }

    private Connection abrirLigacao() throws SQLException {
        if (connection == null || connection.isClosed()) {
            OracleDataSource ds = new OracleDataSource();
            ds.setURL(jdbcUrl);
            connection = ds.getConnection(username, password);
        }

        return connection;
    }

    private void fecharLigacao() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();

            connection = null;
        }
    }


}
