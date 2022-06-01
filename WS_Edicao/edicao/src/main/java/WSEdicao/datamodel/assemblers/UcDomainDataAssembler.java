package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.UcJpa;
import WSEdicao.domain.entities.Uc;
import org.springframework.stereotype.Service;


@Service
public class UcDomainDataAssembler {

    public UcJpa toData(Uc oUc){
        return new UcJpa(oUc.getSSigla(), oUc.getSDenominacao());
    }

    public Uc toDomain(UcJpa oUcJpa){
        return new Uc( oUcJpa.getSSigla(),oUcJpa.getSDenominacao());
    }
}
