package WSEdicao.repositories;

import WSEdicao.datamodel.UcJpa;
import WSEdicao.datamodel.assemblers.UcDomainDataAssembler;
import WSEdicao.domain.entities.Uc;
import WSEdicao.repositories.jpa.UcJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UcRepository {

    @Autowired
    UcJpaRepository ucJpaRepository;
    @Autowired
    UcDomainDataAssembler ucAssembler;

    public Uc save(Uc uc ) {
        UcJpa ucJpa = ucAssembler.toData(uc);

        UcJpa savedUcJpa = ucJpaRepository.save( ucJpa );

        return ucAssembler.toDomain(savedUcJpa);
    }

    public Optional<Uc> findBycodUc(int codUc) {
        Optional<UcJpa> opUc = ucJpaRepository.findBycodUc(codUc);

        if ( opUc.isPresent() ) {
            Uc uc = ucAssembler.toDomain(opUc.get());
            return Optional.of( uc );
        }
        else
            return Optional.empty();
    }

    public List<Uc> findAll() {
        List<UcJpa> setUcJpa = ucJpaRepository.findAll();

        List<Uc> setUc = new ArrayList<Uc>();
        for( UcJpa ucJpa : setUcJpa ) {
            Uc uc = ucAssembler.toDomain(ucJpa);
            setUc.add(uc);
        }

        return setUc;
    }
}
