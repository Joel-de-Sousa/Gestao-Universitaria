package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.AvaliacaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.SubmissaoJDBC;
import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.SubmissaoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import org.springframework.stereotype.Service;

@Service

public class AvaliacaoJDBCDomainDataAssembler {
    //@Autowired
   // JuriDomainDataAssembler juriDomainDataAssembler;

    //@Autowired
    //SubmissaoDomainDataAssembler submissaoDomainDataAssembler;

    public AvaliacaoJDBC toData(Avaliacao avaliacao) {
        int juri=0;
        int submissao=0;
        if (avaliacao.getJuri() != null) {
            juri = avaliacao.getJuri().getCodJuri();

        }
        if (avaliacao.getSubmissao() != null) {
            submissao = avaliacao.getSubmissao().getCodSubmissao();
        }
        return new AvaliacaoJDBC(avaliacao.getCodAvaliacao(),avaliacao.getCodProjeto(),avaliacao.getCodMA(),juri,submissao, avaliacao.getNota(), avaliacao.getJustificacao(), avaliacao.getDate(),avaliacao.getEstado().ordinal());
    }

    public Avaliacao toDomain(AvaliacaoJDBC avaliacaoJDBC) {
        Juri juri=null;
        Submissao submissao=null;

        if (avaliacaoJDBC.getJuri() != 0) {
            juri = juriJDBCDomainDataAssembler.toDomain(avaliacaoJDBC.getJuri());

        }
        if (avaliacaoJPA.getSubmissao() != null) {
            submissao = submissaoDomainDataAssembler.toDomain(avaliacaoJPA.getSubmissao());
        }

        return new Avaliacao(avaliacaoJPA.getCodAvaliacao(), avaliacaoJPA.getCodMA(), juri, submissao);
    }
}
