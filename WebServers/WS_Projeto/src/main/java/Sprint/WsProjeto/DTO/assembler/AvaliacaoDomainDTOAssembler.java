package Sprint.WsProjeto.DTO.assembler;


import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AvaliacaoDomainDTOAssembler {



    public AvaliacaoDomainDTOAssembler() {
    }

    public AvaliacaoDTO toDto(Avaliacao avaliacao) {


        return new AvaliacaoDTO(avaliacao.getCodAvaliacao(), avaliacao.getCodMA(),avaliacao.getJuri().getCodJuri(),avaliacao.getSubmissao().getCodSubmissao());
    }
}
