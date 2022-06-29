package Sprint.WsProjeto.repositories.JDBC;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ProjetoJDBCDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
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
public class ProjetoJDBCRepository {

    @Value("${jdbc.oracle.upskillbd.url}")
    private String jdbcUrl;
    @Value("${jdbc.oracle.upskillbd.username}")
    private String username;
    @Value("${jdbc.oracle.upskillbd.password}")
    private String password;
    private Connection connection;

    @Autowired
    private ProjetoJDBCDomainDataAssembler projetoDomainDataAssembler;

    public ProjetoJDBCRepository() throws IOException {
        connection = null;
    }

    private Optional<ProjetoJDBC> getById(int codProjeto) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncobterprojetoporid(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, codProjeto);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();

        List<ProjetoJDBC> lProjetoJDBCs=projetoDomainDataAssembler.toJDBC(cachedRowSet);
        if(lProjetoJDBCs.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(lProjetoJDBCs.get(0));
    }

    public List<ProjetoJDBC> findAll() throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{call prcobtertodosprojetos(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return projetoDomainDataAssembler.toJDBC(cachedRowSet);
    }

    public ProjetoJDBC save(ProjetoJDBC projeto) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncadicionarprojeto(?,?,?,?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.setInt(2, projeto.getCodProjeto());
        callableStatement.setInt(3, projeto.getCodProposta());
        callableStatement.setInt(4, projeto.getCodEstudante());
        callableStatement.setInt(5, projeto.getCodOrientador());

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return projetoDomainDataAssembler.toJDBC(cachedRowSet).get(0);
    }

    public Optional<ProjetoJDBC> remove(ProjetoJDBC projetoJDBC) throws SQLException {
        Optional<ProjetoJDBC> optional=getById(projetoJDBC.getCodProjeto());

        if(!optional.isPresent()) {
            return Optional.empty();
        }

        abrirLigacao();

        CallableStatement callableStatement=connection.prepareCall("{call prceliminarprojeto(?)}");

        callableStatement.setInt(1, projetoJDBC.getCodProjeto());

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
