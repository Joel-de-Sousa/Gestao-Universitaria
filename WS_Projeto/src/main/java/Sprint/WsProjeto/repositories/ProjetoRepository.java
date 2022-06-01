package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.IRepository.IProjetoRepository;
import Sprint.WsProjeto.repositories.JPA.IProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjetoRepository implements IProjetoRepository {

    @Autowired
    IProjetoJPARepository projetoJpaRepository;

    @Autowired
    ProjetoDomainDataAssembler projetoDomainDataAssembler;


    @Override
    public Projeto save(Projeto projeto) {
        ProjetoJPA projetoJPA = projetoDomainDataAssembler.toData(projeto);

        ProjetoJPA savedProjetoJPA = projetoJpaRepository.save(projetoJPA);

        return projetoDomainDataAssembler.toDomain(savedProjetoJPA);
    }

    public Optional<Projeto> findByCode(int nCode) {
        Optional<ProjetoJPA> opProjeto = projetoJpaRepository.findByCode(nCode);

        if ( opProjeto.isPresent() ) {
            Projeto projeto = projetoDomainDataAssembler.toDomain(opProjeto.get());
            return Optional.of( projeto );
        }
        else
            return Optional.empty();
    }
}
