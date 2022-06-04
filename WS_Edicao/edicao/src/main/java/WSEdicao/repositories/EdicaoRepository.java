package WSEdicao.repositories;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.UcJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
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

    @Autowired
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    public Edicao save(Edicao edicao ) {
        EdicaoJpa edicaoJpa1 = edicaoAssembler.toData(edicao);
        if(!(edicaoJpaRepository.existsByCodUc(edicaoJpa1.getCodUc()) && edicaoJpaRepository.existsByCodAnoLetivo(edicaoJpa1.getCodAnoLetivo()))) {
            EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

            EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save(edicaoJpa);

            return edicaoAssembler.toDomain(savedEdicaoJpa);
        } else
            throw new IllegalArgumentException("Já consta na base de dados a Edicão com a UC edição com a UC e o Ano Letivo, verifique na base de dados");
    }

    public Optional<Edicao> findBycodEdicao(int codEdicao) {
        Optional<EdicaoJpa> opEdicao = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if ( opEdicao.isPresent() ) {
            Edicao edicao = edicaoAssembler.toDomain(opEdicao.get());
            //EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);
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
