package Sprint.WsProjeto.DTO.assembler;


import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.service.JuriService;
import Sprint.WsProjeto.service.SubmissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@Service
public class AvaliacaoDomainDTOAssembler {

    @Autowired
    JuriDomainDTOAssembler juriDomainDTOAssembler;
    @Autowired
    JuriService juriService;

    @Autowired
    SubmissaoDomainDTOAssembler submissaoDomainDTOAssembler;

    @Autowired
    SubmissaoService submissaoService;


    public AvaliacaoDomainDTOAssembler() {
    }

    public AvaliacaoDTO toDto(Avaliacao avaliacao) {



        int juri = 0;
        int submissao=0;

        if (avaliacao.getJuri() != null) {
            juri = avaliacao.getJuri().getCodJuri();

        }
        if (avaliacao.getSubmissao() != null) {
          submissao=avaliacao.getSubmissao().getCodSubmissao();
        }

        return new AvaliacaoDTO(avaliacao.getCodAvaliacao(),
                avaliacao.getCodProjeto(),avaliacao.getCodMA(),
                juri,submissao,avaliacao.getNota(),avaliacao.getJustificacao(),
                avaliacao.getDate().toString(),avaliacao.getEstado().name());
    }

    public Avaliacao toDomain(AvaliacaoDTO avaliacao) throws IOException, SQLException {

        JuriDTO juriDTO=juriService.findJuriByCode(avaliacao.getCodJuri());
        Juri juri=juriDomainDTOAssembler.toDomain(juriDTO);

        SubmissaoDTO submissaoDTO=submissaoService.findSubmissaoBycode(avaliacao.getCodSubmissao());
        Submissao submissao=submissaoDomainDTOAssembler.toDomain(submissaoDTO);

        Date date=Date.valueOf(avaliacao.getDate());


        return new Avaliacao(avaliacao.getCodAvaliacao(),avaliacao.getCodProjeto(),
                avaliacao.getCodMA(),juri,submissao,avaliacao.getNota(), avaliacao.getJustificacao(),
                date,Avaliacao.Estado.valueOf(avaliacao.getEstado()));
    }
}
