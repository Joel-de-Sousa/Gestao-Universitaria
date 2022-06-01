package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import org.springframework.stereotype.Service;


@Service
public class EdicaoDomainDataAssembler {

    UcDomainDataAssembler ucAssembler;
    AnoLetivoDomainDataAssembler anoLetivoAssembler;

    public EdicaoJpa toData(Edicao oEdicao){
        UcJpa ucJpa= ucAssembler.toData(oEdicao.getCodUc());
        AnoLetivoJpa anoLetivoJpa = anoLetivoAssembler.toData(oEdicao.getAnoLetivo());

        return new EdicaoJpa(ucJpa, anoLetivoJpa);
    }

    public Edicao toDomain(EdicaoJpa oEdicaoJpa){
        Uc uc = ucAssembler.toDomain(oEdicaoJpa.getCodUc());
        AnoLetivo anoLetivo = anoLetivoAssembler.toDomain(oEdicaoJpa.getCodAnoLetivo());

        return new Edicao(uc, anoLetivo);
    }
}
