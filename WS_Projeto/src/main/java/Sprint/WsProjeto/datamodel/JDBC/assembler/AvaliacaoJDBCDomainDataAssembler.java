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
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.repositories.JDBC.JuriJDBCRepository;
import Sprint.WsProjeto.repositories.JDBC.SubmissaoJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoJDBCDomainDataAssembler {
   @Autowired
    JuriJDBCRepository juriJDBCRepository;
   @Autowired
   JuriJDBCDomainDataAssembler juriJDBCDomainDataAssembler;


   @Autowired
    SubmissaoJDBCRepository submissaoJDBCRepository;

   @Autowired
    SubmissaoJDBCDomainDataAssembler submissaoJDBCDomainDataAssembler;
    public AvaliacaoJDBC toJDBC(Avaliacao avaliacao) {
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

    public Avaliacao toDomain(AvaliacaoJDBC avaliacaoJDBC) throws Exception {
        Juri juri=null;
        Submissao submissao=null;

        if (avaliacaoJDBC.getCodJuri() != 0) {
            Optional<JuriJDBC> juriJDBC = juriJDBCRepository.getById(avaliacaoJDBC.getCodJuri());
           if(juriJDBC.isPresent())
            juri=juriJDBCDomainDataAssembler.toDomain(juriJDBC.get());
           else throw new Exception("Juri não consta na base de dados");
        }

        if (avaliacaoJDBC.getCodSubmissao() != 0) {
            Optional<SubmissaoJDBC> submissaoJDBC = submissaoJDBCRepository.getById(avaliacaoJDBC.getCodSubmissao());
            if(submissaoJDBC.isPresent())
                submissao=submissaoJDBCDomainDataAssembler.toDomain(submissaoJDBC.get());
            else throw new Exception("Submissao não consta na base de dados");
        }

       Avaliacao.Estado[] estados= Avaliacao.Estado.values();
        Avaliacao.Estado estado=estados[avaliacaoJDBC.getEstado()];




        return new Avaliacao(avaliacaoJDBC.getCodAvaliacao(),avaliacaoJDBC.getCodProjeto(),avaliacaoJDBC.getCodMA(),juri,submissao,avaliacaoJDBC.getNota(), avaliacaoJDBC.getJustificacao(),avaliacaoJDBC.getDate(),estado);
    }


    public AvaliacaoJDBC toJDBC(int codAvaliacao, int codProjeto, int codMA, int codJuri, int codSubmissao, double nota, String justificacao, Date date, int estado) {
        return new AvaliacaoJDBC(codAvaliacao, codProjeto, codMA, codJuri, codSubmissao, nota, justificacao, date, estado);
    }

    public List<AvaliacaoJDBC> toJDBC(CachedRowSet cachedRowSet) throws SQLException {
        List<AvaliacaoJDBC> lAvaliacaoJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            AvaliacaoJDBC avaliacaoJDBC = toJDBC(cachedRowSet.getInt(AvaliacaoJDBC.COD_AVALIACAO),
                    cachedRowSet.getInt(AvaliacaoJDBC.COD_PROJETO),
                    cachedRowSet.getInt(AvaliacaoJDBC.COD_MA),
                    cachedRowSet.getInt(AvaliacaoJDBC.COD_JURI),
                    cachedRowSet.getInt(AvaliacaoJDBC.COD_SUBMISSAO),
                    cachedRowSet.getDouble(AvaliacaoJDBC.NOTA),
                    cachedRowSet.getString(AvaliacaoJDBC.JUSTIFICACAO),
                    cachedRowSet.getDate(AvaliacaoJDBC.DATE),
                    cachedRowSet.getInt(AvaliacaoJDBC.ESTADO));



            lAvaliacaoJDBCs.add(avaliacaoJDBC);
        }

        return lAvaliacaoJDBCs;
    }


}
