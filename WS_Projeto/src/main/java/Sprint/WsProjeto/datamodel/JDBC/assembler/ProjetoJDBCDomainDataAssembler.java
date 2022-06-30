package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.JuriJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.AvaliacaoDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoJDBCDomainDataAssembler {

    public ProjetoJDBC toJDBC (Projeto projeto){

        return new ProjetoJDBC ( projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador());
    }

    public Projeto toDomain (ProjetoJDBC projetoJDBC){

        Projeto.Estado[] estados= Projeto.Estado.values();
        Projeto.Estado estado=estados[projetoJDBC.getEstado()];

        return new Projeto(projetoJDBC.getCodProjeto(),projetoJDBC.getCodProposta(),projetoJDBC.getCodEstudante(),projetoJDBC.getCodOrientador(),estado);
    }
    public ProjetoJDBC toJDBC(int codProjeto, int codProposta, int codEstudante, int codOrientador, int estado) {
        return new ProjetoJDBC(codProjeto,codProposta,codEstudante,codOrientador,estado);
    }

    public List<ProjetoJDBC> toJDBC(CachedRowSet cachedRowSet) throws SQLException {
        List<ProjetoJDBC> lProjetoJDBCs = new ArrayList<>();

        while (cachedRowSet.next()) {
            ProjetoJDBC projetoJDBC = toJDBC(cachedRowSet.getInt(ProjetoJDBC.COD_PROJETO),
                    cachedRowSet.getInt(ProjetoJDBC.COD_PROPOSTA),
                    cachedRowSet.getInt(ProjetoJDBC.COD_ESTUDANTE),
                    cachedRowSet.getInt(ProjetoJDBC.COD_ORIENTADOR),
                    cachedRowSet.getInt(ProjetoJDBC.ESTADO));


            lProjetoJDBCs.add(projetoJDBC);
        }

        return lProjetoJDBCs;
    }


}
