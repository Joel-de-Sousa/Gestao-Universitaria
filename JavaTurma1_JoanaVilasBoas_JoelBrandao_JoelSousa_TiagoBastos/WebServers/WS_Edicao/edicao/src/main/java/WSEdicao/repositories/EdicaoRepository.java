package WSEdicao.repositories;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import WSEdicao.repositories.jpa.MomentoAvaliacaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


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

    @Autowired
    MomentoAvaliacaoJpaRepository momentoAvaliacaoJpaRepository;

    public Edicao save(Edicao edicao ) throws Exception {
        EdicaoJpa edicaoJpa1 = edicaoAssembler.toData(edicao);
        if(!(edicaoJpaRepository.existsByCodUc(edicaoJpa1.getCodUc()) && edicaoJpaRepository.existsByCodAnoLetivo(edicaoJpa1.getCodAnoLetivo()))) {
            EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

            EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save(edicaoJpa);
            Edicao savedEdicao = edicaoAssembler.toDomain(savedEdicaoJpa);

            return savedEdicao;
        } else
            throw new Exception("Ja existe uma edição com a unidade curricular e ano letivo selecionados!");
    }

    public Edicao addAndSaveMA(Edicao edicao ) throws Exception {

            EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

            EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save(edicaoJpa);

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

    public EdicaoJpa findBycodEdicaoJpa(int codEdicao) {
        Optional<EdicaoJpa> opEdicao = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if ( opEdicao.isPresent() ) {
            return opEdicao.get();
        }
        else
            return null;
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

    /*public List<MomentoAvaliacaoDTO> findMomentoAvaliacaoByCod( Edicao codEdicao) {

        List<MomentoAvaliacaoJpa> momentoAvaliacaoJpas = momentoAvaliacaoJpaRepository.findAllByPersonId(id);

        List<AddressDTO> addressesDTO = new ArrayList<AddressDTO>();
        for( AddressJpa addressJpa : addressesJpa ) {
            AddressDTO addressDTO = new AddressDTO( addressJpa.getStreet(), addressJpa.getCity(), addressJpa.getPostalCode(), addressJpa.getCountryCode());

            addressesDTO.add(addressDTO);
        }

        return addressesDTO;
    }*/
}
