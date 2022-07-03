package Sprint.WsProjeto.DTO.assembler;


import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.domain.entities.Juri;
import org.springframework.stereotype.Service;

@Service
public class JuriDomainDTOAssembler {

    public JuriDomainDTOAssembler() {
    }

    public JuriDTO toDto(Juri juri){

        return new JuriDTO(juri.getCodJuri(),juri.getCodPresidente(),juri.getCodOrientador(),juri.getCodArguente());
    }

    public Juri toDomain(JuriDTO juri){

        return new Juri(juri.getCodJuri(),juri.getCodPresidente(),juri.getCodOrientador(),juri.getCodArguente());
    }
}
