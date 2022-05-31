package WSEdicao.repositories;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRepository {

    @Autowired
    EdicaoJpaRepository edicaoJpaRepository;
    @Autowired
    EdicaoDomainDataAssembler edicaoAssembler;

    public Edicao save(Edicao edicao ) {
        EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

        EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save( edicaoJpa );

        return edicaoAssembler.toDomain(savedEdicaoJpa);
    }

    public Optional<Edicao> findBycodEdicao(int codEdicao) {
        Optional<EdicaoJpa> opEdicao = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if ( opEdicao.isPresent() ) {
            Edicao edicao = edicaoAssembler.toDomain(opEdicao.get());
            return Optional.of( edicao );
        }
        else
            return Optional.empty();
    }

    public List<Edicao> findAll() {
        List<EdicaoJpa> setEdicaoJpa = edicaoJpaRepository.findAll();

        List<Edicao> setEdicao = new ArrayList<Edicao>();
        for( EdicaoJpa edicaoJpa : setEdicaoJpa ) {
            Edicao edicao = edicaoAssembler.toDomain(edicaoJpa);
            setEdicao.add(edicao);
        }

        return setEdicao;
    }
}
