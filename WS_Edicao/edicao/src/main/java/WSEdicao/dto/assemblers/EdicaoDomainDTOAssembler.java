package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.EdicaoDTO;
import org.springframework.stereotype.Service;


public class EdicaoDomainDTOAssembler {

    private EdicaoDomainDTOAssembler() {
    }

    public EdicaoDTO toDTO(int codEdicao, Uc codUc, AnoLetivo codAnoLetivo) {
        return new EdicaoDTO(codEdicao,codUc,codAnoLetivo);
    }
}
