package WSEdicao.repositories;

import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.MomentoAvaliacaoDomainDataAssembler;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.MomentoAvaliacaoDomainDTOAssembler;
import WSEdicao.repositories.jpa.MomentoAvaliacaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MomentoAvaliacaoRepository {

    @Autowired
    MomentoAvaliacaoJpaRepository maJpaRepository;
    @Autowired
    MomentoAvaliacaoDomainDataAssembler maAssembler;
    @Autowired
    MomentoAvaliacaoDomainDTOAssembler maDTOAssembler;


    public MomentoAvaliacao save(MomentoAvaliacao momentoAvaliacao) throws Exception {
        MomentoAvaliacaoJpa momentoAvaliacaoJpa1 = maAssembler.toData(momentoAvaliacao);
        if(!maJpaRepository.existsByDenominacao(momentoAvaliacaoJpa1.getDenominacao())) {

            MomentoAvaliacaoJpa momentoAvaliacaoJpa = maAssembler.toData(momentoAvaliacao);

            MomentoAvaliacaoJpa savedMomentoAvaliacaoJpa = maJpaRepository.save(momentoAvaliacaoJpa);

            return maAssembler.toDomain(savedMomentoAvaliacaoJpa);
        }else
            throw new Exception("Já existe um momento de avaliação com essa denominação");
    }

    public Optional<MomentoAvaliacaoDTO> findBycodMomentoAvaliacao(int codMomentoAvaliacao) {
        Optional<MomentoAvaliacaoJpa> opMomentoAvaliacao = maJpaRepository.findBycodMomentoAvaliacao(codMomentoAvaliacao);

        if (opMomentoAvaliacao.isPresent()) {
            MomentoAvaliacao momentoAvaliacao = maAssembler.toDomain(opMomentoAvaliacao.get());
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = maDTOAssembler.toDTO(momentoAvaliacao);
            return Optional.of(momentoAvaliacaoDTO);
        } else
            return Optional.empty();
    }

    public List<MomentoAvaliacao> findAll() {
        List<MomentoAvaliacaoJpa> setMomentoAvaliacaoJpa = maJpaRepository.findAll();

        List<MomentoAvaliacao> setMomentoAvaliacao = new ArrayList<MomentoAvaliacao>();
        for (MomentoAvaliacaoJpa momentoAvaliacaoJpa : setMomentoAvaliacaoJpa) {
            MomentoAvaliacao momentoAvaliacao = maAssembler.toDomain(momentoAvaliacaoJpa);
            setMomentoAvaliacao.add(momentoAvaliacao);
        }

        return setMomentoAvaliacao;
    }

    /*public List<MomentoAvaliacao> findAllByCodEdicao (int codEdicao){
        List<MomentoAvaliacaoJpa> listMAJPA = maJpaRepository.findByCodEdicao(codEdicao);
        List<MomentoAvaliacao> listMA =new ArrayList<>();
        for (MomentoAvaliacaoJpa ma : listMAJPA) {
            listMA.add(maAssembler.toDomain(ma));
        }
        return listMA;
    }*/


}
