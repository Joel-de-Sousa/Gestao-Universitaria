package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.MomentoAvaliacaoDTO;
import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IProjetoFactory;
import Sprint.WsProjeto.repositories.EdicaoWebRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProtocolException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @Autowired
    IProjetoFactory projetoFactory;

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    UtilizadorWebRepository utilizadorWebRepository;

    @Autowired
    PropostaWebRepository propostaWebRepository;

    @Autowired
    EdicaoWebRepository edicaoWebRepository;

    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    AvaliacaoDomainDTOAssembler avaliacaoDomainDTOAssembler;


    public ProjetoService() {
    }

    public ProjetoDTO createAndSaveProjeto(NewProjetoInfoDto projetoInfoDto) throws IOException {


        Optional<PropostaRestDTO> propostaRestDTO = propostaWebRepository.findPropostaByCode(projetoInfoDto.getCodProposta());
        if (propostaRestDTO.isPresent()) {
            Projeto projeto = projetoFactory.createProjeto( projetoInfoDto.getCodProposta(),projetoInfoDto.getCodEstudante());

            Projeto oProjetoSaved = projetoRepository.save(projeto);


            Optional<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.findEdicaoByCode(propostaRestDTO.get().getCodEdicao());
            List<MomentoAvaliacaoDTO> momentoAvaliacaoList = edicaoRestDTO.get().getMomentoAvaliacaoList();
            List<Avaliacao> list = new ArrayList<>();
            for (MomentoAvaliacaoDTO momentoAvaliacaoDTO : momentoAvaliacaoList) {
                Avaliacao avaliacao = avaliacaoService.createAndSaveAvaliacao(momentoAvaliacaoDTO.getCodMomentoAvaliacao());
                list = oProjetoSaved.getListaAvaliacoes();
                    list.add(avaliacao);
            }
            oProjetoSaved.setListaAvaliacoes(list);
            Projeto projetoSave = projetoRepository.save(oProjetoSaved);
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(projetoSave);

            return oProjetoDTO;
        } else
            throw new ProtocolException("A Proposta não existe");


    }

    public ProjetoDTO findProjetoByCode(int codProjeto) {

        Optional<Projeto> opProjeto = projetoRepository.findById(codProjeto);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }

    public ProjetoDTO findProjetoByCodeEstudante(int codEstudante) {

        Optional<Projeto> opProjeto = projetoRepository.findByCodEstudante(codEstudante);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }


    public Optional<UtilizadorRestDTO> findUtilizadorByCode(int codUtilizador) {

        Optional<UtilizadorRestDTO> oUtilizadorCode = utilizadorWebRepository.findUtilizadorByCode(codUtilizador);

        return oUtilizadorCode;
    }

    public Optional<PropostaRestDTO> findPropostaByCode(int codProposta) {

        Optional<PropostaRestDTO> oPropostaCode = propostaWebRepository.findPropostaByCode(codProposta);

        return oPropostaCode;
    }

    public List<ProjetoDTO> findProjetosPorCodigoRUC(int codRUC) throws Exception {
        List<ProjetoDTO> listProjeto = new ArrayList<>();
        List<EdicaoRestDTO> listEdicoes = edicaoWebRepository.getListaEdicoesByCodRUC(codRUC);
        for (EdicaoRestDTO edicao : listEdicoes) {
            List<PropostaRestDTO> listPropostas = propostaWebRepository.findAllPropostasAceitesByCodEdicao(edicao.getCodEdicao());
            for (PropostaRestDTO proposta : listPropostas) {
                Projeto projeto = projetoRepository.findByCodProposta(proposta.getCodProposta());
                ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                listProjeto.add(projetoDTO);
            }
        }
        return listProjeto;

    }



    public List<ProjetoDTO> findProjetosConcluidos() throws SQLException {
        List<Projeto> listProjetos = projetoRepository.findProjetosConcluidos();

        List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
        for (Projeto projeto : listProjetos) {
            ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
            listProjetoDTO.add(projetoDTO);
        }
        return listProjetoDTO;
    }

}
