package Sprint.WsProjeto.datamodel.JPA.assembler;

import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class JuriDomainDataAssembler {

    public JuriJPA toData (Juri juri){

        return new JuriJPA (juri.getCodJuri(), juri.getCodPresidente(), juri.getCodOrientador(), juri.getCodArguente());
    }

    public Juri toDomain (JuriJPA juriJPA){

        return new Juri(juriJPA.getCodJuri(), juriJPA.getCodPresidente(), juriJPA.getCodOrientador(), juriJPA.getCodArguente());
    }

}
