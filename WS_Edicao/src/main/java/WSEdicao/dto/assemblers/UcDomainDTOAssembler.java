package WSEdicao.dto.assemblers;

import WSEdicao.dto.UcDTO;
import org.springframework.stereotype.Service;

@Service
public class UcDomainDTOAssembler {

    private UcDomainDTOAssembler() {
    }

    public UcDTO toDTO(int codUc, String sSigla, String sDenominacao ) {
        return new UcDTO(codUc,sSigla,sDenominacao);
    }
}
