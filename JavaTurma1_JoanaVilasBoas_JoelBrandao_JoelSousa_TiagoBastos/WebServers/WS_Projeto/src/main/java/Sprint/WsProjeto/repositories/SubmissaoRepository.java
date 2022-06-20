package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.SubmissaoDomainDataAssembler;
import Sprint.WsProjeto.repositories.IRepository.ISubmissaoRepository;
import Sprint.WsProjeto.repositories.JPA.SubmissaoJPARepository;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SubmissaoRepository implements ISubmissaoRepository {
    @Autowired
    SubmissaoJPARepository submissaoJPARepository;

    @Autowired
    SubmissaoDomainDataAssembler submissaoDomainDataAssembler;

    public Submissao save(Submissao submissao) {
        SubmissaoJPA submissaoJPA = submissaoDomainDataAssembler.toData(submissao);

        SubmissaoJPA savedSubmissaoJPA = submissaoJPARepository.save(submissaoJPA);

        return submissaoDomainDataAssembler.toDomain(savedSubmissaoJPA);
    }



    public Optional<Submissao> findById(int codSubmissao) {
        Optional<SubmissaoJPA> opSubmissao = submissaoJPARepository.findById(codSubmissao);

        if ( opSubmissao.isPresent() ) {
            Submissao submissao = submissaoDomainDataAssembler.toDomain(opSubmissao.get());
            return Optional.of( submissao );
        }
        else
            return Optional.empty();
    }
}
