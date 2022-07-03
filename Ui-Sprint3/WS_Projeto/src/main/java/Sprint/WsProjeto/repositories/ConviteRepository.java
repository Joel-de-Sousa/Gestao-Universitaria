package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.ConviteDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.repositories.JPA.ConviteJPARepository;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ConviteRepository {

    @Autowired
    ConviteJPARepository conviteJPARepository;

    @Autowired
    ConviteDomainDataAssembler conviteDomainDataAssembler;



    public Convite save(Convite convite) {
        ConviteJPA conviteJPA = conviteDomainDataAssembler.toData(convite);

        ConviteJPA savedConviteJPA = conviteJPARepository.save(conviteJPA);

        return conviteDomainDataAssembler.toDomain(savedConviteJPA);
    }

    public Optional<Convite> findByCodDocente(int codDocente) {
        Optional<ConviteJPA> opConvite = conviteJPARepository.findByCodDocente(codDocente);

        if ( opConvite.isPresent() ) {
            Convite convite = conviteDomainDataAssembler.toDomain(opConvite.get());
            return Optional.of( convite );
        }
        else
            return Optional.empty();
    }



    public Optional<Convite> findByCodConvite(int CodConvite) {
        Optional<ConviteJPA> opConvite = conviteJPARepository.findByCodConvite(CodConvite);

        if ( opConvite.isPresent() ) {
            Convite convite = conviteDomainDataAssembler.toDomain(opConvite.get());
            return Optional.of( convite );
        }
        else
            return Optional.empty();
    }

    public Optional<Convite> findById(int CodConvite) {
        Optional<ConviteJPA> opConvite = conviteJPARepository.findByCodConvite(CodConvite);

        if ( opConvite.isPresent() ) {
            Convite convite = conviteDomainDataAssembler.toDomain(opConvite.get());
            return Optional.of( convite );
        }
        else
            return Optional.empty();
    }

    public void deleteByCodConvite(int codConvite){
        conviteJPARepository.deleteById(codConvite);
    }
}
