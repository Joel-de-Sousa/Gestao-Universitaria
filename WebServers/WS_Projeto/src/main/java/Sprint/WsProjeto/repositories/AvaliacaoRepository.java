package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.AvaliacaoDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.JPA.AvaliacaoJPARepository;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AvaliacaoRepository {

    @Autowired
    AvaliacaoJPARepository avaliacaoJPARepository;

    @Autowired
    AvaliacaoDomainDataAssembler avaliacaoDomainDataAssembler;



    public Avaliacao save(Avaliacao avaliacao) {
        AvaliacaoJPA avaliacaoJPA = avaliacaoDomainDataAssembler.toData(avaliacao);

        AvaliacaoJPA savedAvaliacaoJPA = avaliacaoJPARepository.save(avaliacaoJPA);

        return avaliacaoDomainDataAssembler.toDomain(savedAvaliacaoJPA);
    }

    public Optional<Avaliacao> findById(int codAvaliacao) {
        Optional<AvaliacaoJPA> opAvaliacao = avaliacaoJPARepository.findById(codAvaliacao);

        if ( opAvaliacao.isPresent() ) {
            Avaliacao avaliacao = avaliacaoDomainDataAssembler.toDomain(opAvaliacao.get());
            return Optional.of( avaliacao );
        }
        else
            return Optional.empty();
    }
}
