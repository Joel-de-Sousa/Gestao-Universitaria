package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.SubmissaoDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.ProjetoNotExists;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.domain.entities.*;
import Sprint.WsProjeto.domain.factories.IAvaliacaoFactory;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import Sprint.WsProjeto.repositories.JuriRepository;
import Sprint.WsProjeto.repositories.SubmissaoRepository;
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
    ProjetoService projetoService;

    public AvaliacaoService() {
    }

    public Avaliacao createAndSaveAvaliacao(int codMA , int codProjeto) throws Exception {

        Avaliacao avaliacao = avaliacaoFactory.createAvaliacao(codMA,codProjeto);

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

    public AvaliacaoDTO updateAvaliacao(AvaliacaoPartialDTO avaliacaoUpdate) throws Exception {

        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(avaliacaoUpdate.getCodAvaliacao());

        if (opAvaliacao.isPresent()) {
            if (avaliacaoUpdate.getEstado().equals("PENDENTE") || avaliacaoUpdate.getEstado().equals("REVISAO")) {

                opAvaliacao.get().setCodAvaliacao(avaliacaoUpdate.getCodAvaliacao());
                opAvaliacao.get().setNota(avaliacaoUpdate.getNota());
                opAvaliacao.get().setJustificacao(avaliacaoUpdate.getJustificacao());
                opAvaliacao.get().setDate(Date.valueOf(avaliacaoUpdate.getDate()));

                Avaliacao avaliacaoSaved = avaliacaoRepository.update(opAvaliacao.get());
                AvaliacaoDTO avaliacaoSavedDTO = avaliacaoDomainDTOAssembler.toDto(avaliacaoSaved);


                return avaliacaoSavedDTO;

            }
            throw new Exception("A avaliação está concluída");
        }
        throw new Exception("A avaliação não consta na base de dados");
    }


    public AvaliacaoDTO updateEstadoAvaliacao(AvaliacaoPartialDTO avaliacaoUpdate) throws Exception {


        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(avaliacaoUpdate.getCodAvaliacao());

        if (opAvaliacao.isPresent()) {
            List<ProjetoDTO> listProjetosRUC = projetoService.findProjetosPorCodigoRUC(avaliacaoUpdate.getCodRUC());

            for(ProjetoDTO projeto : listProjetosRUC){

                if(projeto.getCodProjeto()== opAvaliacao.get().getCodProjeto()){

                opAvaliacao.get().setCodAvaliacao(avaliacaoUpdate.getCodAvaliacao());
                opAvaliacao.get().setEstado(Avaliacao.Estado.valueOf(avaliacaoUpdate.getEstado()));

                Avaliacao avaliacaoSaved = avaliacaoRepository.save(opAvaliacao.get());
                AvaliacaoDTO avaliacaoSavedDTO = avaliacaoDomainDTOAssembler.toDto(avaliacaoSaved);

                boolean conclusao = true;
                List<Avaliacao> lisAvaliacao = avaliacaoRepository.findAvaliacoesByCodProjeto(opAvaliacao.get().getCodProjeto());
                    for(Avaliacao avaliacao : lisAvaliacao){
                        if(!avaliacao.getEstado().equals(Avaliacao.Estado.CONCLUIDA)){
                            conclusao=false;
                            break;
                        };
                    }

                    if(conclusao){
                        projetoService.updateEstado(Projeto.Estado.CONCLUIDO, opAvaliacao.get().getCodProjeto());
                    }

                return avaliacaoSavedDTO;
                }
        }

    }throw new Exception("A avaliação não consta na base de dados");
}}
