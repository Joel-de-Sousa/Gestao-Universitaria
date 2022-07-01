package WSEdicao.dto.assemblers;

import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import org.springframework.stereotype.Service;

@Service
public class MomentoAvaliacaoDomainDTOAssembler {

    public MomentoAvaliacaoDomainDTOAssembler() {
    }

    public MomentoAvaliacaoDTO toDTO(MomentoAvaliacao momentoAvaliacao){
        return new MomentoAvaliacaoDTO(momentoAvaliacao.getCodMomentoAvaliacao(),
                momentoAvaliacao.getCodEdicao(),
                momentoAvaliacao.getDenominacao());
    }

    public MomentoAvaliacao toDomain(MomentoAvaliacaoDTO momentoAvaliacaoDTO){
        return new MomentoAvaliacao(momentoAvaliacaoDTO.getCodMomentoAvaliacao(),
                momentoAvaliacaoDTO.getCodEdicao(),
                momentoAvaliacaoDTO.getDenominacao());
    }
}
