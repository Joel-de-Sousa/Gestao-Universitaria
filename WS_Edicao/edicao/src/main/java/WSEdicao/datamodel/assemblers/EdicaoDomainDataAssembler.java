package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EdicaoDomainDataAssembler {

    @Autowired
    UcDomainDataAssembler ucAssembler;
    @Autowired
    AnoLetivoDomainDataAssembler anoLetivoAssembler;

    public EdicaoJpa toData(Edicao edicao){

        UcJpa ucJpa= ucAssembler.toData(edicao.getUc());
        AnoLetivoJpa anoLetivoJpa = anoLetivoAssembler.toData(edicao.getAnoLetivo());

        return new EdicaoJpa(ucJpa, anoLetivoJpa);

    }

    public Edicao toDomain(EdicaoJpa edicaoJpa){

        Uc uc = ucAssembler.toDomain(edicaoJpa.getCodUc());
        AnoLetivo anoLetivo = anoLetivoAssembler.toDomain(edicaoJpa.getCodAnoLetivo());

        return new Edicao(edicaoJpa.getCodEdicao(),uc, anoLetivo);
    }
}
