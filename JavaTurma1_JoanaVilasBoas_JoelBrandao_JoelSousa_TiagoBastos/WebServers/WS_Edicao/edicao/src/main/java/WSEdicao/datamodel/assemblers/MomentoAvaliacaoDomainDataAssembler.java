package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.domain.entities.MomentoAvaliacao;
import org.springframework.stereotype.Service;

@Service
public class MomentoAvaliacaoDomainDataAssembler {

    public MomentoAvaliacaoJpa toData(MomentoAvaliacao momentoAvaliacao){
        return new MomentoAvaliacaoJpa(
                momentoAvaliacao.getCodEdicao(),
                momentoAvaliacao.getDenominacao());
    }

    public MomentoAvaliacao toDomain(MomentoAvaliacaoJpa momentoAvaliacaoJpa){
        return new MomentoAvaliacao(
                momentoAvaliacaoJpa.getCodMomentoAvaliacao(),
                momentoAvaliacaoJpa.getCodEdicao()
                ,momentoAvaliacaoJpa.getDenominacao());
    }


}
