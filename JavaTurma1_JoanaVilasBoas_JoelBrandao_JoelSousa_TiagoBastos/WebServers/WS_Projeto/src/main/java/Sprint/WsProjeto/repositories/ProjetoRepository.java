package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ProjetoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.IRepository.IProjetoRepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjetoRepository implements IProjetoRepository {

    @Autowired
    ProjetoJPARepository projetoJPARepository;

    @Autowired
    ProjetoDomainDataAssembler projetoDomainDataAssembler;



    public Projeto save(Projeto projeto) {
        ProjetoJPA projetoJPA = projetoDomainDataAssembler.toData(projeto);

        ProjetoJPA savedProjetoJPA = projetoJPARepository.save(projetoJPA);

        return projetoDomainDataAssembler.toDomain(savedProjetoJPA);
    }

    public Optional<Projeto> findById(int codProjeto) {
        Optional<ProjetoJPA> opProjeto = projetoJPARepository.findById(codProjeto);

        if ( opProjeto.isPresent() ) {
            Projeto projeto = projetoDomainDataAssembler.toDomain(opProjeto.get());
            return Optional.of( projeto );
        }
        else
            return Optional.empty();
    }

    public Optional<Projeto> findByCodEstudante(int codEstudante) {
        Optional<ProjetoJPA> opProjeto = projetoJPARepository.findByCodEstudante (codEstudante);

        if ( opProjeto.isPresent() ) {
            Projeto projeto = projetoDomainDataAssembler.toDomain(opProjeto.get());
            return Optional.of( projeto );
        }
        else
            return Optional.empty();
    }
}
