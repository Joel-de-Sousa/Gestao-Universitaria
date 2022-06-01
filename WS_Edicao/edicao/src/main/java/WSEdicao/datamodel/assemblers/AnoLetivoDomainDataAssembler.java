package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.domain.entities.AnoLetivo;
import org.springframework.stereotype.Service;

@Service
public class AnoLetivoDomainDataAssembler {

    public AnoLetivoJpa toData(AnoLetivo oAnoLetivo){
        return new AnoLetivoJpa(oAnoLetivo.getAno());
    }

    public AnoLetivo toDomain(AnoLetivoJpa oAnoLetivoJpa){
        return new AnoLetivo(oAnoLetivoJpa.getAno());
    }
}
