package Sprint.WsProjeto.DTO.assembler;


import Sprint.WsProjeto.DTO.ConviteDTO;
import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class ConviteDomainDTOAssembler {

    public ConviteDomainDTOAssembler() {
    }

    public ConviteDTO toDto(Convite convite){

        return new ConviteDTO(convite.getCodConvite(), convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado().toString());
    }

    public Convite toDomain(ConviteDTO convite){

        return new Convite(convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(), Convite.Estado.valueOf(convite.getEstado()));
    }
}
