package WSEdicao.repositories;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.assemblers.AnoLetivoDomainDataAssembler;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.repositories.jpa.AnoLetivoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AnoLetivoRepository {

    @Autowired
    AnoLetivoJpaRepository anoLetivoJpaRepository;
    @Autowired
    AnoLetivoDomainDataAssembler assembler;

    public AnoLetivo save(AnoLetivo anoLetivo ) {
        AnoLetivoJpa anoLetivoJpa = assembler.toData(anoLetivo);

        AnoLetivoJpa savedAnoLetivoJpa = anoLetivoJpaRepository.save( anoLetivoJpa );

        return assembler.toDomain(savedAnoLetivoJpa);
    }

    public Optional<AnoLetivo> findByCode(int codAnoLetivo) {
        Optional<AnoLetivoJpa> opAnoLetivo = anoLetivoJpaRepository.findBycodAnoLetivo(codAnoLetivo);

        if ( opAnoLetivo.isPresent() ) {
            AnoLetivo anoLetivo = assembler.toDomain(opAnoLetivo.get());
            return Optional.of( anoLetivo );
        }
        else
            return Optional.empty();
    }

   /* public List<AnoLetivo> findAll() {
        List<AnoLetivoJpa> setAnoLetivoJpa = anoLetivoJpaRepository.findAll();

        List<AnoLetivo> setAnoLetivo = new ArrayList<AnoLetivo>();
        for( AnoLetivoJpa anoLetivoJpa : setAnoLetivoJpa ) {
            AnoLetivo anoLetivo = assembler.toDomain(anoLetivoJpa);
            setAnoLetivo.add(anoLetivo);
        }

        return setAnoLetivo;
    }*/
}
