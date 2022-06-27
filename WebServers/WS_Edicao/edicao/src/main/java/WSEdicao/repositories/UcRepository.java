package WSEdicao.repositories;

import WSEdicao.datamodel.UcJpa;
import WSEdicao.datamodel.assemblers.UcDomainDataAssembler;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.UcDTO;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
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
    @Autowired
    UcDomainDTOAssembler ucDTOAssembler;

    public Uc save(Uc uc ) throws Exception {
        UcJpa ucJpa1 = ucAssembler.toData(uc);

        if(!ucJpaRepository.existsBySigla(ucJpa1.getSigla())) {
            UcJpa ucJpa = ucAssembler.toData(uc);

            UcJpa savedUcJpa = ucJpaRepository.save(ucJpa);

            return ucAssembler.toDomain(savedUcJpa);
        } else
            throw new Exception("Já se encontra uma UC com esta sigla na base de dados, por favor insira uma nova");
    }

    public Optional<UcDTO> findBycodUc(int codUc) {
        Optional<UcJpa> opUc = ucJpaRepository.findBycodUc(codUc);

        if ( opUc.isPresent() ) {
            Uc uc = ucAssembler.toDomain(opUc.get());
            UcDTO ucDTO = ucDTOAssembler.toDTO(uc);
            return Optional.of( ucDTO );
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
