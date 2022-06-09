package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.JPA.CandidaturaJPA;
import wsproposta.proposta.datamodel.JPA.assembler.CandidaturaDomainDataAssembler;
import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.repositories.JPA.ICandidaturaJPARepository;
import wsproposta.proposta.repositories.iRepositories.ICandidaturaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CandidaturaRepository implements ICandidaturaRepository {

    @Autowired
    ICandidaturaJPARepository candidaturaJPARepository;
    @Autowired
    CandidaturaDomainDataAssembler candidaturaAssembler;

    public Candidatura save (Candidatura candidatura){
        CandidaturaJPA candidaturaJPA = candidaturaAssembler.toData(candidatura);

        CandidaturaJPA savedCandidaturaJPA = candidaturaJPARepository.save(candidaturaJPA);

        Candidatura savedCandidatura = candidaturaAssembler.toDomain(savedCandidaturaJPA);
        return savedCandidatura;
    }

    public Optional<Candidatura> findById(int codCandidatura) {
        Optional<CandidaturaJPA> opCandidatura = candidaturaJPARepository.findById(codCandidatura);

        if ( opCandidatura.isPresent() ) {
            Candidatura candidatura = candidaturaAssembler.toDomain(opCandidatura.get());
            return Optional.of( candidatura );
        }
        else
            return Optional.empty();
    }

    public List<Candidatura> findAll(){
        List<CandidaturaJPA> listCandidaturasJPA = candidaturaJPARepository.findAll();
        List<Candidatura> listCandidaturas =new ArrayList<>();
        for (CandidaturaJPA p:listCandidaturasJPA) {
            listCandidaturas.add(candidaturaAssembler.toDomain(p));
        }
        return listCandidaturas;
    }
}
