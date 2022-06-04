package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EdicaoDomainDataAssembler {

    @Autowired
    UcDomainDataAssembler ucAssembler;
    @Autowired
    AnoLetivoDomainDataAssembler anoLetivoAssembler;

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    public EdicaoJpa toData(Edicao edicao){

        //UcJpa ucJpa= ucRepository.findJPAbyCodUC(edicao.getUc());
        //AnoLetivoJpa anoLetivoJpa = anoLetivoRepository.findJPAbyCodAnoLetivo(edicao.getAnoLetivo());

        return new EdicaoJpa(edicao.getUc(), edicao.getAnoLetivo());

    }

    public Edicao toDomain(EdicaoJpa edicaoJpa){

        //Uc uc = ucAssembler.toDomain(edicaoJpa.getCodUc());
        //AnoLetivo anoLetivo = anoLetivoAssembler.toDomain(edicaoJpa.getCodAnoLetivo());

        return new Edicao(edicaoJpa.getCodEdicao(), edicaoJpa.getCodUc(), edicaoJpa.getCodAnoLetivo());
    }
}
