package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JuriJDBCDomainDataAssembler {
    public JuriJDBC toJDBC (Juri juri){

        return new JuriJDBC (juri.getCodJuri(), juri.getCodPresidente(), juri.getCodOrientador(), juri.getCodArguente());
    }

    public Juri toDomain (JuriJDBC juriJDBC){

        return new Juri(juriJDBC.getCodJuri(), juriJDBC.getCodPresidente(), juriJDBC.getCodOrientador(), juriJDBC.getCodArguente());
    }


    public JuriJDBC toJDBC(int codJuri, int codPresidente, int codOrientador, int codArguente) {
        return new JuriJDBC(codJuri,codPresidente,codOrientador,codArguente);
    }

    public List<JuriJDBC> toJDBC(CachedRowSet cachedRowSet) throws SQLException {
        List<JuriJDBC> lJuriJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            JuriJDBC juriJDBC = toJDBC(cachedRowSet.getInt(JuriJDBC.COD_JURI),
                    cachedRowSet.getInt(JuriJDBC.COD_PRESIDENTE),
                    cachedRowSet.getInt(JuriJDBC.COD_ORIENTADOR),
                    cachedRowSet.getInt(JuriJDBC.COD_ARGUENTE));



            lJuriJDBCs.add(juriJDBC);
        }

        return lJuriJDBCs;
    }
}
