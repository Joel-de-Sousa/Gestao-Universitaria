package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.dto.AnoLetivoDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnoLetivoDomainDTOAssembler {

    private AnoLetivoDomainDTOAssembler() {
    }

    public AnoLetivoDTO toDTO(AnoLetivo anoLetivo) {
        return new AnoLetivoDTO(anoLetivo.getCodAnoLetivo(), anoLetivo.getAno());
    }

    public AnoLetivo toDomain(AnoLetivoDTO anoLetivoDTO){
        return new AnoLetivo(anoLetivoDTO.getCodAnoLetivo(), anoLetivoDTO.getAno());
    }
}
