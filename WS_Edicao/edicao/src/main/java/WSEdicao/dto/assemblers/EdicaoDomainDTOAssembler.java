package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.EdicaoAllArgsDTO;
import WSEdicao.dto.EdicaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EdicaoDomainDTOAssembler {


    private EdicaoDomainDTOAssembler() {
    }

    public EdicaoDTO toDTO(Edicao edicao) {
        return new EdicaoDTO(edicao.getCodEdicao(),edicao.getUc(),edicao.getAnoLetivo());
    }

    /*public EdicaoAllArgsDTO toDTOAllArgs(Edicao edicao){
        return new EdicaoAllArgsDTO(edicao.getCodEdicao(), edicao.getUc(), edicao.getAnoLetivo());
    }*/
}
