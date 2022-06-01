package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.datamodel.JPA.assembler.PropostaDomainDataAssembler;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.JPA.PropostaJPARepository;
import wsproposta.proposta.repositories.iRepositories.IPropostaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRepository implements IPropostaRepository {
    @Autowired
    PropostaJPARepository propostaJPARepository;
    @Autowired
    PropostaDomainDataAssembler propostaAssembler;

    public Proposta save (Proposta proposta){
        PropostaJPA propostaJPA = propostaAssembler.toData(proposta);

        PropostaJPA savedPropostaJPA = propostaJPARepository.save(propostaJPA);

        return propostaAssembler.toDomain(savedPropostaJPA);
    }

    public Optional<Proposta> findById(int codProposta) {
        Optional<PropostaJPA> opProposta = propostaJPARepository.findById(codProposta);

        if ( opProposta.isPresent() ) {
            Proposta proposta = propostaAssembler.toDomain(opProposta.get());
            return Optional.of( proposta );
        }
        else
            return Optional.empty();
    }

    public List<Proposta> findAll(){
        List<PropostaJPA> listPropostasJPA = propostaJPARepository.findAll();
        List<Proposta> listPropostas =new ArrayList<>();
        for (PropostaJPA p:listPropostasJPA) {
            listPropostas.add(propostaAssembler.toDomain(p));
        }
        return listPropostas;
    }
}
