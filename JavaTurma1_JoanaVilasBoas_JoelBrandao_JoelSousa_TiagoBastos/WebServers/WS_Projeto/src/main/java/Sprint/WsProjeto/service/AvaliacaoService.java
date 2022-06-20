package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.SubmissaoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.JPA.assembler.JuriDomainDataAssembler;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.domain.factories.IAvaliacaoFactory;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import Sprint.WsProjeto.repositories.JuriRepository;
import Sprint.WsProjeto.repositories.SubmissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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

    public AvaliacaoService() {
    }

    public AvaliacaoDTO createAndSaveAvaliacao(NewAvaliacaoInfoDTO avaliacaoInfoDto) throws IOException {

        JuriDTO juriDTO=juriService.findJuriByCode(avaliacaoInfoDto.getCodJuri());
        Juri juri=juriDomainDTOAssembler.toDomain(juriDTO);

        Optional<Submissao> opSubmissao=submissaoRepository.findById(avaliacaoInfoDto.getCodSubmissao());

        Avaliacao avaliacao = avaliacaoFactory.createAvaliacao(avaliacaoInfoDto.getCodMA(),juri,opSubmissao.get());

        Avaliacao oAvaliacaoSaved = avaliacaoRepository.save(avaliacao);

        AvaliacaoDTO oAvaliacaoDTO = avaliacaoDomainDTOAssembler.toDto(oAvaliacaoSaved);

        return oAvaliacaoDTO;


    }

    public AvaliacaoDTO findAvaliacaoByCode(int codAvaliacao) {

        Optional<Avaliacao> opAvaliacao = avaliacaoRepository.findById(codAvaliacao);

        if (opAvaliacao.isPresent()) {
            Avaliacao oAvaliacao = opAvaliacao.get();
           AvaliacaoDTO oAvaliacaoDTO = avaliacaoDomainDTOAssembler.toDto(oAvaliacao);

            return oAvaliacaoDTO;
        } else return null;
    }
}
