package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IEdicaoFactory;
import WSEdicao.repositories.EdicaoRepository;
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

    public EdicaoService() {
    }

    public Edicao createAndSaveEdicao(int codEdicao, Uc codUc, AnoLetivo codAnoLetivo ) {
        Edicao edicao = edicaoFactory.createEdicao(codEdicao,codUc,codAnoLetivo);
        return edicaoRepository.save(edicao);
    }

    public Optional<Edicao> getEdicaoByCode(int codEdicao ) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);

        return opEdicao;
    }

    public List<Edicao> getAllEdicao() {

        List<Edicao> listEdicao = edicaoRepository.findAll();

        return listEdicao;
    }
}
