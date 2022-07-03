package Sprint.WsProjeto.repositories;


import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JDBC.assembler.ProjetoJDBCDomainDataAssembler;

import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.IRepository.IProjetoRepository;
import Sprint.WsProjeto.repositories.JDBC.ProjetoJDBCRepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjetoRepository implements IProjetoRepository {


    @Autowired
    ProjetoJDBCRepository projetoJDBCRepository;
    @Autowired
    ProjetoJDBCDomainDataAssembler projetoJDBCDomainDataAssembler;

    @Autowired
    EdicaoWebRepository edicaoWebRepository;
    @Autowired
    PropostaWebRepository propostaWebRepository;

    @Autowired
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;


    public Projeto save(Projeto projeto) throws SQLException {
        ProjetoJDBC projetoJDBC = projetoJDBCDomainDataAssembler.toJDBC(projeto);

        ProjetoJDBC savedProjetoJDBC = projetoJDBCRepository.save(projetoJDBC);

        return projetoJDBCDomainDataAssembler.toDomain(savedProjetoJDBC);
    }

    /*public Projeto update(Projeto projeto) throws SQLException {
        ProjetoJDBC projetoJDBC = projetoJDBCDomainDataAssembler.toJDBC(projeto);

        ProjetoJDBC updatedProjetoJDBC = projetoJDBCRepository.update(projetoJDBC);

        return projetoJDBCDomainDataAssembler.toDomain(updatedProjetoJDBC);
    }*/


    public void update(Projeto.Estado estado, int codProjeto) throws SQLException {

        Optional<ProjetoJDBC> opProjeto = projetoJDBCRepository.getById(codProjeto);

        if (opProjeto.isPresent()) {
            if (opProjeto.get().getEstado() != 1) {

                opProjeto.get().setCodProjeto(codProjeto);
                opProjeto.get().setEstado(estado.ordinal());

                //ProjetoJDBC projetoJDBC = projetoJDBCDomainDataAssembler.toJDBC(opProjeto.get());

                ProjetoJDBC updatedProjetoJDBC = projetoJDBCRepository.update(opProjeto.get());

                //return projetoJDBCDomainDataAssembler.toDomain(updatedProjetoJDBC);
            }
        }
    }


    public Optional<Projeto> findById(int codProjeto) throws SQLException {
        Optional<ProjetoJDBC> opProjeto = projetoJDBCRepository.getById(codProjeto);

        if (opProjeto.isPresent()) {
            Projeto projeto = projetoJDBCDomainDataAssembler.toDomain(opProjeto.get());
            return Optional.of(projeto);
        } else
            return Optional.empty();
    }

    public Optional<Projeto> findByCodEstudante(int codEstudante) throws SQLException {
        Optional<ProjetoJDBC> opProjeto = projetoJDBCRepository.findProjetoByCodeEstudante(codEstudante);

        if (opProjeto.isPresent()) {
            Projeto projeto = projetoJDBCDomainDataAssembler.toDomain(opProjeto.get());
            return Optional.of(projeto);
        } else
            return Optional.empty();
    }

    public List<Projeto> findProjetosConcluidos() throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.findProjetosConcluidos();
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }

    public List<Projeto> findProjetosDatasAvaliacao(int codMA, Date fromDate, Date toDate) throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.findProjetosDatasAvaliacao(codMA, fromDate, toDate);
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }


    public List<Projeto> listaFiltrada(String queryFinal) throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.listaQuery(queryFinal);
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }

    public List<Projeto> findProjetosByCodDocente(int codDocente) throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.findProjetosByCodDocente(codDocente);
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }

    public List<Projeto> findProjetosByCodPresidente(int codPresidente) throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.findProjetosByCodPresidente(codPresidente);
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }

    public Projeto findProjetoByCodProposta(int codProposta) throws SQLException {
        Optional<ProjetoJDBC> opProjeto = projetoJDBCRepository.findProjetoByCodProposta(codProposta);

        if (opProjeto.isPresent()) {
            Projeto projeto = projetoJDBCDomainDataAssembler.toDomain(opProjeto.get());
            return projeto;
        } else
            return null;
    }

    public List<Projeto> findProjetosComDeterminadoMACompleto(int codMA) throws SQLException {
        List<ProjetoJDBC> listProjetosJDBC = projetoJDBCRepository.findProjetosComDeterminadoMACompleto(codMA);
        List<Projeto> listProjetos = new ArrayList<>();
        for (ProjetoJDBC p : listProjetosJDBC) {
            listProjetos.add(projetoJDBCDomainDataAssembler.toDomain(p));
        }
        return listProjetos;
    }

    public List<ProjetoDTO> findProjetosPorCodigoRUC(int codRUC) throws Exception {
        List<ProjetoDTO> listProjeto = new ArrayList<>();
        List<EdicaoRestDTO> listEdicoes = edicaoWebRepository.getListaEdicoesByCodRUC(codRUC);

        for (EdicaoRestDTO edicao : listEdicoes) {
            List<PropostaRestDTO> listPropostas = propostaWebRepository.findAllPropostasAceitesByCodEdicao(edicao.getCodEdicao());

            for (PropostaRestDTO proposta : listPropostas) {
                Projeto projeto =findProjetoByCodProposta(proposta.getCodProposta());
                ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                listProjeto.add(projetoDTO);
            }
        }
        return listProjeto;

    }
}
