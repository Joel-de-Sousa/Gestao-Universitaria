package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.SubmissaoDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.ProjetoNotExists;
import Sprint.WsProjeto.datamodel.JDBC.ProjetoJDBC;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.domain.entities.*;
import Sprint.WsProjeto.domain.factories.IAvaliacaoFactory;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.repositories.*;
import Sprint.WsProjeto.repositories.JDBC.ProjetoJDBCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {
    @Autowired
    AvaliacaoDomainDTOAssembler avaliacaoDomainDTOAssembler;

    @Autowired
    IAvaliacaoFactory avaliacaoFactory;

    @Autowired
    AvaliacaoRepository avaliacaoRepository;

    @Autowired
    JuriDomainDTOAssembler juriDomainDTOAssembler;

    @Autowired
    JuriService juriService;

    @Autowired
    SubmissaoDomainDTOAssembler submissaoDomainDTOAssembler;

    @Autowired
    SubmissaoRepository submissaoRepository;
    @Autowired
    ProjetoRepository projetoRepository;
    @Autowired
    EdicaoWebRepository edicaoWebRepository;


    public AvaliacaoService() {
    }

    public Avaliacao createAndSaveAvaliacao(int codMA, int codProjeto) throws Exception {

        Avaliacao avaliacao = avaliacaoFactory.createAvaliacao(codMA, codProjeto);

        Avaliacao oAvaliacaoSaved = avaliacaoRepository.save(avaliacao);

        // AvaliacaoDTO oAvaliacaoDTO = avaliacaoDomainDTOAssembler.toDto(oAvaliacaoSaved);

        return oAvaliacaoSaved;


    }

    public AvaliacaoDTO findAvaliacaoByCode(int codAvaliacao) throws Exception {

        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(codAvaliacao);

        if (opAvaliacao.isPresent()) {
            Avaliacao oAvaliacao = opAvaliacao.get();
            AvaliacaoDTO oAvaliacaoDTO = avaliacaoDomainDTOAssembler.toDto(oAvaliacao);

            return oAvaliacaoDTO;
        } else return null;
    }

    public List<AvaliacaoDTO> findAvaliacoesByCodProjeto(int CodProjeto) throws Exception {
        List<Avaliacao> listAvaliacoes = avaliacaoRepository.findAvaliacoesByCodProjeto(CodProjeto);

        List<AvaliacaoDTO> listAvaliacaoDTO = new ArrayList<>();
        for (Avaliacao avaliacao : listAvaliacoes) {
            AvaliacaoDTO avaliacaoDTO = avaliacaoDomainDTOAssembler.toDto(avaliacao);
            listAvaliacaoDTO.add(avaliacaoDTO);
        }
        return listAvaliacaoDTO;
    }

    //PRESIDENTE Preenche a nota e justificação de avaliação
    public AvaliacaoDTO updateAvaliacao(AvaliacaoPartialDTO avaliacaoUpdate) throws Exception {

        if (avaliacaoUpdate.getNota() >= 0 && avaliacaoUpdate.getNota() <= 20) {

            List<Projeto> listProjetos = projetoRepository.findProjetosByCodPresidente(avaliacaoUpdate.getCodPresidente());

            if (!listProjetos.isEmpty()) {

                Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(avaliacaoUpdate.getCodAvaliacao());

                if (opAvaliacao.isPresent()) {
                    if (opAvaliacao.get().getEstado().equals(Avaliacao.Estado.PENDENTE) || opAvaliacao.get().getEstado().equals(Avaliacao.Estado.REVISAO)) {

                        Date data = new Date(System.currentTimeMillis());
                        opAvaliacao.get().setCodAvaliacao(avaliacaoUpdate.getCodAvaliacao());
                        opAvaliacao.get().setNota(avaliacaoUpdate.getNota());
                        opAvaliacao.get().setJustificacao(avaliacaoUpdate.getJustificacao());
                        opAvaliacao.get().setDate(data);

                        Avaliacao avaliacaoSaved = avaliacaoRepository.update(opAvaliacao.get());
                        AvaliacaoDTO avaliacaoSavedDTO = avaliacaoDomainDTOAssembler.toDto(avaliacaoSaved);


                        return avaliacaoSavedDTO;

                    }
                    throw new Exception("A avaliação está concluída");
                }
                throw new Exception("A avaliação não consta na base de dados");
            } else
                throw new Exception("O Codigo introduzido não pertence a um Presidente de Júri");
        } else
            throw new Exception("O valor da nota introduzido não é válido");
    }


    public AvaliacaoDTO updateEstadoAvaliacao(AvaliacaoPartialDTO avaliacaoUpdate) throws Exception {

        List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(avaliacaoUpdate.getCodRUC());

        if (!edicaoRestDTO.isEmpty()) {
            Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(avaliacaoUpdate.getCodAvaliacao());

            if (opAvaliacao.isPresent()) {
            /*List<ProjetoDTO> listProjetosRUC = projetoRepository.findProjetosPorCodigoRUC(avaliacaoUpdate.getCodRUC());

            for(ProjetoDTO projeto : listProjetosRUC){

                if(projeto.getCodProjeto()== opAvaliacao.get().getCodProjeto()){
*/
                opAvaliacao.get().setCodAvaliacao(avaliacaoUpdate.getCodAvaliacao());
                opAvaliacao.get().setEstado(Avaliacao.Estado.valueOf(avaliacaoUpdate.getEstado()));

                Avaliacao avaliacaoSaved = avaliacaoRepository.updateRuc(opAvaliacao.get());
                AvaliacaoDTO avaliacaoSavedDTO = avaliacaoDomainDTOAssembler.toDto(avaliacaoSaved);

                boolean conclusao = true;
                List<Avaliacao> lisAvaliacao = avaliacaoRepository.findAvaliacoesByCodProjeto(opAvaliacao.get().getCodProjeto());
                for (Avaliacao avaliacao : lisAvaliacao) {
                    if (!avaliacao.getEstado().equals(Avaliacao.Estado.CONCLUIDA)) {
                        conclusao = false;
                        break;
                    }
                }

                if (conclusao) {
                    projetoRepository.update(Projeto.Estado.CONCLUIDO, opAvaliacao.get().getCodProjeto());
                }

                return avaliacaoSavedDTO;
            }
            throw new Exception("A avaliação não consta na base de dados");
        } else
            throw new Exception("O Codigo introduzido não pertence a um RUC");
    }
}
