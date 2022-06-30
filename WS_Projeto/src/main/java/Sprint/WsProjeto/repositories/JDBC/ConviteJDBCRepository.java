package Sprint.WsProjeto.repositories.JDBC;

import Sprint.WsProjeto.datamodel.JDBC.ConviteJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ConviteJDBCDomainDataAssembler;
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
public class ConviteJDBCRepository {


    @Value("${jdbc.oracle.upskillbd.url}")
    private String jdbcUrl;
    @Value("${jdbc.oracle.upskillbd.username}")
    private String username;
    @Value("${jdbc.oracle.upskillbd.password}")
    private String password;
    private Connection connection;

    @Autowired
    private ConviteJDBCDomainDataAssembler conviteJDBCDomainDataAssembler;

    public ConviteJDBCRepository() throws IOException {
        connection = null;
    }

    public Optional<ConviteJDBC> getById(int codConvite) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncobterconviteporid(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);
        callableStatement.setInt(2, codConvite);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();

        List<ConviteJDBC> lConviteJDBCs=conviteJDBCDomainDataAssembler.toJDBC(cachedRowSet);
        if(lConviteJDBCs.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(lConviteJDBCs.get(0));
    }

    public List<ConviteJDBC> findAll() throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{call prcobtertodosconvites(?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return conviteJDBCDomainDataAssembler.toJDBC(cachedRowSet);
    }

    public ConviteJDBC save(ConviteJDBC convite) throws SQLException {
        abrirLigacao();

        CallableStatement callableStatement = connection.prepareCall("{? = call fncadicionarconvite(?,?,?,?,?)}");

        callableStatement.registerOutParameter(1, Types.REF_CURSOR);

        callableStatement.setInt(2, convite.getCodConvite());
        callableStatement.setInt(3, convite.getCodProjeto());
        callableStatement.setInt(4, convite.getCodEstudante());
        callableStatement.setInt(5, convite.getCodDocente());
        callableStatement.setInt(6, convite.getEstado());

        callableStatement.execute();

        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet cachedRowSet = factory.createCachedRowSet();
        cachedRowSet.populate((ResultSet) callableStatement.getObject(1));

        fecharLigacao();
        return conviteJDBCDomainDataAssembler.toJDBC(cachedRowSet).get(0);
    }

    public Optional<ConviteJDBC> remove(int codConvite) throws SQLException {
        Optional<ConviteJDBC> optional=getById(codConvite);

        if(!optional.isPresent()) {
            return Optional.empty();
        }

        abrirLigacao();

        CallableStatement callableStatement=connection.prepareCall("{call prceliminarconvite(?)}");

        callableStatement.setInt(1, codConvite);

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
