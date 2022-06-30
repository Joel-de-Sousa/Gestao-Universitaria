package Sprint.WsProjeto.repositories;

import Sprint.WsProjeto.datamodel.JDBC.AvaliacaoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.assembler.AvaliacaoDomainDataAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.JDBC.AvaliacaoJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.AvaliacaoJPARepository;
import Sprint.WsProjeto.repositories.JPA.JuriJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AvaliacaoRepository {

    @Autowired
    AvaliacaoJDBCRepository avaliacaoJDBCRepository;

    @Autowired
    AvaliacaoDomainDataAssembler avaliacaoDomainDataAssembler;



    public Avaliacao save(Avaliacao avaliacao) {
        AvaliacaoJPA avaliacaoJPA = avaliacaoDomainDataAssembler.toData(avaliacao);

        AvaliacaoJPA savedAvaliacaoJPA = avaliacaoJPARepository.save(avaliacaoJPA);

        return avaliacaoDomainDataAssembler.toDomain(savedAvaliacaoJPA);
    }

    public Optional<Avaliacao> findById(int codAvaliacao) {
        Optional<AvaliacaoJPA> opAvaliacao = avaliacaoJPARepository.findById(codAvaliacao);

        if ( opAvaliacao.isPresent() ) {
            Avaliacao avaliacao = avaliacaoDomainDataAssembler.toDomain(opAvaliacao.get());
            return Optional.of( avaliacao );
        }
        else
            return Optional.empty();
    }


    public List<Avaliacao> findAvaliacoesByCodProjeto(int CodProjeto) throws SQLException {

        List<AvaliacaoJDBC> listAvaliacoesJDBC = avaliacaoJDBCRepository.findAvaliacoesByCodProjeto(CodProjeto);
        List<Avaliacao> listAvaliacoes =new ArrayList<>();
        for (AvaliacaoJDBC p:listAvaliacoesJDBC) {
            listAvaliacoes.add(avaliacaoJDBCDataAssembler.toDomain(p));
        }
        return listAvaliacoes;
    }}
