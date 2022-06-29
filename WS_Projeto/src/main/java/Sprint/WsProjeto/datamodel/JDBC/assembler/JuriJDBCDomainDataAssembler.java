package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class JuriJDBCDomainDataAssembler {
    public JuriJDBC toJDBC (Juri juri){

        return new JuriJDBC (juri.getCodJuri(), juri.getCodPresidente(), juri.getCodOrientador(), juri.getCodArguente());
    }

    public Juri toDomain (JuriJDBC juriJDBC){

        return new Juri(juriJDBC.getCodJuri(), juriJDBC.getCodPresidente(), juriJDBC.getCodOrientador(), juriJDBC.getCodArguente());
    }

}
