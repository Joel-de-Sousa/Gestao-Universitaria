package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.factories.IAnoLetivoFactory;
import WSEdicao.repositories.AnoLetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnoLetivoService {

    @Autowired
    IAnoLetivoFactory factory;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    public AnoLetivoService() {
    }

    public AnoLetivo createAndSaveAnoLetivo(String ano) {
        AnoLetivo anoLetivo = factory.createAnoLetivo(ano);
        return anoLetivoRepository.save(anoLetivo);
    }

    public Optional<AnoLetivo> getAnoLetivoByCode(int codAnoLetivo ) {

        Optional<AnoLetivo> opAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        return opAnoLetivo;
    }

    public List<AnoLetivo> getAllAnoLetivo() {

        List<AnoLetivo> listAnoLetivo = anoLetivoRepository.findAll();

        return listAnoLetivo;
    }
}
