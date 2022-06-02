package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.UcDTO;
import org.springframework.stereotype.Service;

@Service
public class UcDomainDTOAssembler {

    private UcDomainDTOAssembler() {
    }

    public UcDTO toDTO(Uc uc ) {
        return new UcDTO(uc.getCodUc(),uc.getSigla(), uc.getDenominacao());
    }

    public Uc toDomain(UcDTO ucDTO){
        return new Uc(ucDTO.getCodUc(), ucDTO.getSigla(), ucDTO.getDenominacao());
    }
}
