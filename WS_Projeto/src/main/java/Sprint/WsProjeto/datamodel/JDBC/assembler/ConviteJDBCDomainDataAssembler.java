package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.ConviteJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ConviteRecusadoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.domain.entities.Convite;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConviteJDBCDomainDataAssembler {
    public ConviteJDBC toJDBC (Convite convite){

        return new ConviteJDBC (convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado().ordinal());
    }

    public Convite toDomain (ConviteJDBC conviteJDBC){
       Convite.Estado[] estados= Convite.Estado.values();
       Convite.Estado estado=estados[conviteJDBC.getEstado()];

        return new Convite(conviteJDBC.getCodConvite(),conviteJDBC.getCodProjeto(),conviteJDBC.getCodEstudante(),conviteJDBC.getCodDocente(),estado);
    }

    public ConviteRecusadoJDBC toJDBCRecusado (Convite convite){

        return new ConviteRecusadoJDBC (convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado().ordinal());
    }

    public Convite toDomainRecusado (ConviteRecusadoJDBC conviteJDBC){

        Convite.Estado[] estados= Convite.Estado.values();
        Convite.Estado estado=estados[conviteJDBC.getEstado()];
        return new Convite(conviteJDBC.getCodConvite(),conviteJDBC.getCodProjeto(),conviteJDBC.getCodEstudante(),conviteJDBC.getCodDocente(),estado);
    }


    public ConviteJDBC toJDBC(int codConvite, int codProjeto,int codEstudante,int codDocente,int estado) {
        return new ConviteJDBC(codConvite, codProjeto, codEstudante, codDocente, estado);
    }

    public List<ConviteJDBC> toJDBC(CachedRowSet cachedRowSet) throws SQLException {
        List<ConviteJDBC> lConviteJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            ConviteJDBC conviteJDBC = toJDBC(cachedRowSet.getInt(ConviteJDBC.COD_CONVITE),
                    cachedRowSet.getInt(ConviteJDBC.COD_PROJETO),
                    cachedRowSet.getInt(ConviteJDBC.COD_ESTUDANTE),
                    cachedRowSet.getInt(ConviteJDBC.COD_DOCENTE),
                    cachedRowSet.getInt(ConviteJDBC.ESTADO));


            lConviteJDBCs.add(conviteJDBC);
        }

        return lConviteJDBCs;
    }


    public ConviteRecusadoJDBC toJDBCRecusado (int codConvite, int codProjeto,int codEstudante,int codDocente,int estado) {
        return new ConviteRecusadoJDBC(codConvite, codProjeto, codEstudante, codDocente, estado);
    }

    public List<ConviteRecusadoJDBC> toJDBCRecusado (CachedRowSet cachedRowSet) throws SQLException {
        List<ConviteRecusadoJDBC> lConviteJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            ConviteRecusadoJDBC conviteJDBC = toJDBCRecusado(cachedRowSet.getInt(ConviteRecusadoJDBC.COD_CONVITE),
                    cachedRowSet.getInt(ConviteRecusadoJDBC.COD_PROJETO),
                    cachedRowSet.getInt(ConviteRecusadoJDBC.COD_ESTUDANTE),
                    cachedRowSet.getInt(ConviteRecusadoJDBC.COD_DOCENTE),
                    cachedRowSet.getInt(ConviteRecusadoJDBC.ESTADO));


            lConviteJDBCs.add(conviteJDBC);
        }

        return lConviteJDBCs;
    }

}

