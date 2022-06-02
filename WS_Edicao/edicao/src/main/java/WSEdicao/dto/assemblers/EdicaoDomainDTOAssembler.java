package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.EdicaoDTO;
import org.springframework.stereotype.Service;

@Service
public class EdicaoDomainDTOAssembler {

    private EdicaoDomainDTOAssembler() {
    }
    //alterar metodo no service, savencreate
    /*public EdicaoDTO toDTO(int codUc,int codAnoLetivo){
        return new EdicaoDTO(codUc,codAnoLetivo);
    }*/

    public EdicaoDTO toDTO(Edicao edicao) {
        return new EdicaoDTO(edicao.getCodEdicao(),edicao.getUc().getCodUc(),edicao.getAnoLetivo().getCodAnoLetivo());
    }
}
