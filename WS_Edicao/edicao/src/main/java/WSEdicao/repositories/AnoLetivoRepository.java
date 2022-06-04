package WSEdicao.repositories;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.datamodel.assemblers.AnoLetivoDomainDataAssembler;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
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
    @Autowired
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    public AnoLetivo save(AnoLetivo anoLetivo ) {
        AnoLetivoJpa AnoLetivoJpa1 = assembler.toData(anoLetivo);
        if(!anoLetivoJpaRepository.existsByAno(AnoLetivoJpa1.getAno())) {
            AnoLetivoJpa anoLetivoJpa = assembler.toData(anoLetivo);

            AnoLetivoJpa savedAnoLetivoJpa = anoLetivoJpaRepository.save(anoLetivoJpa);

            return assembler.toDomain(savedAnoLetivoJpa);
        } else
            throw new IllegalArgumentException("O Ano Letivo já se encontra inserido na base de dados");
    }

    public Optional<AnoLetivoDTO> findBycodAnoLetivo(int codAnoLetivo) {
        Optional<AnoLetivoJpa> opAnoLetivo = anoLetivoJpaRepository.findBycodAnoLetivo(codAnoLetivo);

        if ( opAnoLetivo.isPresent() ) {
            AnoLetivo anoLetivo = assembler.toDomain(opAnoLetivo.get());
            AnoLetivoDTO anoLetivoDTO = anoLetivoDTOAssembler.toDTO(anoLetivo);
            return Optional.of( anoLetivoDTO );
        }
        else
            return Optional.empty();
    }

    /*public AnoLetivoJpa findJPAbyCodAnoLetivo(int codAnoLetivo){
        AnoLetivoJpa anoLetivoJpa = anoLetivoJpaRepository.findBycodAnoLetivoNopOp(codAnoLetivo);

        return anoLetivoJpa;
    }*/

    public List<AnoLetivo> findAll() {
        List<AnoLetivoJpa> setAnoLetivoJpa = anoLetivoJpaRepository.findAll();

        List<AnoLetivo> setAnoLetivo = new ArrayList<AnoLetivo>();
        for( AnoLetivoJpa anoLetivoJpa : setAnoLetivoJpa ) {
            AnoLetivo anoLetivo = assembler.toDomain(anoLetivoJpa);
            setAnoLetivo.add(anoLetivo);
        }

        return setAnoLetivo;
    }
}
