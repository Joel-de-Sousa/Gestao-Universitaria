package WSEdicao.services;

import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IUcFactory;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UcService {

    @Autowired
    IUcFactory ucFactory;

    @Autowired
    UcRepository ucRepository;

    public UcService() {
    }

    public Uc createAndSaveUc(String sSigla, String sDenominacao ) {
        Uc uc = ucFactory.createUc( sSigla, sDenominacao);
        return ucRepository.save(uc);
    }

    public Optional<Uc> getUcByCode(int codUc) {

        Optional<Uc> opUc = ucRepository.findBycodUc(codUc);

        return opUc;
    }

    public List<Uc> getAllUc() {

        List<Uc> listUc = ucRepository.findAll();

        return listUc;
    }
}
