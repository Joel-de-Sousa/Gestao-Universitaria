package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IEdicaoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EdicaoService {

    @Autowired
    IEdicaoFactory edicaoFactory;

    @Autowired
    EdicaoRepository edicaoRepository;

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    public EdicaoService() {
    }

    public Edicao createAndSaveEdicao(Uc codUc, AnoLetivo codAnoLetivo ) {
        Edicao edicao = edicaoFactory.createEdicao(codUc,codAnoLetivo);
        return edicaoRepository.save(edicao);
    }

    /*public Optional<Edicao> createAndSaveEdicao1(Uc codUC,AnoLetivo codAnoLetivo) {

        Optional<UcDTO> optionalUc = ucRepository.findBycodUc(codUC);
        Optional<AnoLetivoDTO> optionalAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        if (optionalUc.isPresent() && optionalAnoLetivo.isPresent()) {
            Edicao edicao = edicaoFactory.createEdicao(codUC,codAnoLetivo);
            Edicao savedEdicao = edicaoRepository.save(edicao);

            return Optional.of(savedEdicao);
        } else
            return Optional.empty();
    }*/

    public Optional<Edicao> getEdicaoByCode(int codEdicao) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);

        return opEdicao;
    }

    public List<Edicao> getAllEdicao() {

        List<Edicao> listEdicao = edicaoRepository.findAll();

        return listEdicao;
    }
}
