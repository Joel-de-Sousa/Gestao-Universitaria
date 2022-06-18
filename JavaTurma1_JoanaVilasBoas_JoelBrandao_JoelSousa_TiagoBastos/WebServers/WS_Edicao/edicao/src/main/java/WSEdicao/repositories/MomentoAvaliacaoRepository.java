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
    MomentoAvaliacaoJpaRepository momentoAvaliacaoJpaRepository;
    @Autowired
    MomentoAvaliacaoDomainDataAssembler momentoAvaliacaoAssembler;
    @Autowired
    MomentoAvaliacaoDomainDTOAssembler momentoAvaliacaoDTOAssembler;

    public MomentoAvaliacao save(MomentoAvaliacao momentoAvaliacao) throws Exception {

        MomentoAvaliacaoJpa momentoAvaliacaoJpa = momentoAvaliacaoAssembler.toData(momentoAvaliacao);

        MomentoAvaliacaoJpa savedMomentoAvaliacaoJpa = momentoAvaliacaoJpaRepository.save(momentoAvaliacaoJpa);

        return momentoAvaliacaoAssembler.toDomain(savedMomentoAvaliacaoJpa);
    }

    public Optional<MomentoAvaliacaoDTO> findBycodMomentoAvaliacao(int codMomentoAvaliacao) {
        Optional<MomentoAvaliacaoJpa> opMomentoAvaliacao = momentoAvaliacaoJpaRepository.findBycodMomentoAvaliacao(codMomentoAvaliacao);

        if (opMomentoAvaliacao.isPresent()) {
            MomentoAvaliacao momentoAvaliacao = momentoAvaliacaoAssembler.toDomain(opMomentoAvaliacao.get());
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = momentoAvaliacaoDTOAssembler.toDTO(momentoAvaliacao);
            return Optional.of(momentoAvaliacaoDTO);
        } else
            return Optional.empty();
    }

    public List<MomentoAvaliacao> findAll() {
        List<MomentoAvaliacaoJpa> setMomentoAvaliacaoJpa = momentoAvaliacaoJpaRepository.findAll();

        List<MomentoAvaliacao> setMomentoAvaliacao = new ArrayList<MomentoAvaliacao>();
        for (MomentoAvaliacaoJpa momentoAvaliacaoJpa : setMomentoAvaliacaoJpa) {
            MomentoAvaliacao momentoAvaliacao = momentoAvaliacaoAssembler.toDomain(momentoAvaliacaoJpa);
            setMomentoAvaliacao.add(momentoAvaliacao);
        }

        return setMomentoAvaliacao;
    }


}
