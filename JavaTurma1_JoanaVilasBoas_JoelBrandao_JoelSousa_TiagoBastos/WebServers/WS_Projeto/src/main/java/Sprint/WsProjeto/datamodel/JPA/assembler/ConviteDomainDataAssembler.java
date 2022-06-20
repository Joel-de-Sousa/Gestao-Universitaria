package Sprint.WsProjeto.datamodel.JPA.assembler;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class ConviteDomainDataAssembler {

    public ConviteJPA toData (Convite convite){

        return new ConviteJPA (convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado());
    }

    public Convite toDomain (ConviteJPA conviteJPA){

        return new Convite(conviteJPA.getCodConvite(),conviteJPA.getCodProjeto(),conviteJPA.getCodEstudante(),conviteJPA.getCodDocente(),conviteJPA.getEstado());
    }

}
