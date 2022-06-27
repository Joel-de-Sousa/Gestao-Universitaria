package Sprint.WsProjeto.datamodel.JPA.assembler;

import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoDomainDataAssembler {
    @Autowired
    JuriDomainDataAssembler juriDomainDataAssembler;

    @Autowired
    SubmissaoDomainDataAssembler submissaoDomainDataAssembler;

    public AvaliacaoJPA toData (Avaliacao avaliacao){

        JuriJPA juriJPA=juriDomainDataAssembler.toData(avaliacao.getJuri());
        SubmissaoJPA submissaoJPA=submissaoDomainDataAssembler.toData(avaliacao.getSubmissao());

        return new AvaliacaoJPA (avaliacao.getCodMA(), juriJPA, submissaoJPA);
    }

    public Avaliacao toDomain (AvaliacaoJPA avaliacaoJPA){
        Juri juri=juriDomainDataAssembler.toDomain(avaliacaoJPA.getJuri());
        Submissao submissao=submissaoDomainDataAssembler.toDomain(avaliacaoJPA.getSubmissao());

        return new Avaliacao(avaliacaoJPA.getCodAvaliacao(),avaliacaoJPA.getCodMA(),juri,submissao);
    }

}
