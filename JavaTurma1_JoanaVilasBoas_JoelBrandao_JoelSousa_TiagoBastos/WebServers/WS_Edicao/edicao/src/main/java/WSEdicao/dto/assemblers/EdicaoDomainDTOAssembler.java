package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoAllArgsDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EdicaoDomainDTOAssembler {

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;


    private EdicaoDomainDTOAssembler() {
    }

    public EdicaoDTO toDTO(Edicao edicao) {
        return new EdicaoDTO(edicao.getCodEdicao(),edicao.getUc(),edicao.getAnoLetivo(), edicao.getCodRUC(), edicao.getEstado().toString());
    }

    public EdicaoAllArgsDTO toDTOAllArgs(Edicao edicao){
        Optional<UcDTO> ucDTO= ucRepository.findBycodUc(edicao.getUc());
        Optional<AnoLetivoDTO> anoLetivoDTO= anoLetivoRepository.findBycodAnoLetivo(edicao.getAnoLetivo());

        return new EdicaoAllArgsDTO(edicao.getCodEdicao(), ucDTO.get().getCodUc(),ucDTO.get().getSigla(),
                ucDTO.get().getDenominacao(), anoLetivoDTO.get().getCodAnoLetivo(),
                anoLetivoDTO.get().getAno(), edicao.getCodRUC());
    }
}
