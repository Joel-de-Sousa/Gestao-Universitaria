package WSEdicao.dto.assemblers;

import WSEdicao.dto.AnoLetivoDTO;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AnoLetivoDomainDTOAssembler {

    private AnoLetivoDomainDTOAssembler() {
    }

    public AnoLetivoDTO toDTO(int codAnoLetivo, int ano ) {
        return new AnoLetivoDTO(codAnoLetivo,ano);
    }
}
