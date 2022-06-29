package Sprint.WsProjeto.repositories.JDBC;

import Sprint.WsProjeto.datamodel.JDBC.AvaliacaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.AvaliacaoJDBCDomainDataAssembler;
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
public class AvaliacaoJDBCRepository {


    @Value("${jdbc.oracle.upskillbd.url}")
    private String jdbcUrl;
    @Value("${jdbc.oracle.upskillbd.username}")
    private String username;
    @Value("${jdbc.oracle.upskillbd.password}")
    private String password;
    private Connection connection;

    @Autowired
    private AvaliacaoJDBCDomainDataAssembler avaliacaoJDBCDomainDataAssembler;

    public AvaliacaoJDBCRepository() throws IOException {
        connection = null;
    }

    public Optional<AvaliacaoJDBC> getById(int codAvaliacao) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncobteravaliacaoporid(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, codAvaliacao);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();

        List<AvaliacaoJDBC> lAvaliacaoJDBCs=avaliacaoJDBCDomainDataAssembler.toJDBC(cachedRowSet);
        if(lAvaliacaoJDBCs.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(lAvaliacaoJDBCs.get(0));
    }

    public List<AvaliacaoJDBC> findAll() throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{call prcobtertodasavaliacoes(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return avaliacaoJDBCDomainDataAssembler.toJDBC(cachedRowSet);
    }

    public AvaliacaoJDBC save(AvaliacaoJDBC avaliacao) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncadicionaravaliacao(?,?,?,?,?,?,?,?,?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.setInt(2, avaliacao.getCodAvaliacao());
        callableStatement.setInt(3, avaliacao.getCodProjeto());
        callableStatement.setInt(4, avaliacao.getCodMA());

        callableStatement.setInt(5, avaliacao.getCodJuri());
        callableStatement.setInt(6, avaliacao.getCodSubmissao());

        callableStatement.setDouble(7, avaliacao.getNota());
        if (avaliacao.getNota() == AvaliacaoJDBC.NOTA_INDEFINIDA) {
            callableStatement.setNull(7, Types.DOUBLE);
        }
        callableStatement.setString(8,avaliacao.getJustificacao());
        callableStatement.setDate(9,avaliacao.getDate());
        callableStatement.setInt(10,avaliacao.getEstado());


        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return avaliacaoJDBCDomainDataAssembler.toJDBC(cachedRowSet).get(0);
    }

    public Optional<AvaliacaoJDBC> remove(AvaliacaoJDBC avaliacaoJDBC) throws SQLException {
        Optional<AvaliacaoJDBC> optional=getById(avaliacaoJDBC.getCodAvaliacao());

        if(!optional.isPresent()) {
            return Optional.empty();
        }

        abrirLigacao();

        CallableStatement callableStatement=connection.prepareCall("{call prceliminaravaliacao(?)}");

        callableStatement.setInt(1, avaliacaoJDBC.getCodAvaliacao());

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
