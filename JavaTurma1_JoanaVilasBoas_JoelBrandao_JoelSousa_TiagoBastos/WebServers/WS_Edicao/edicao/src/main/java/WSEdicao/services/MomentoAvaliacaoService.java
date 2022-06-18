package WSEdicao.services;

import WSEdicao.datamodel.AnoLetivoJpa;
import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.datamodel.assemblers.MomentoAvaliacaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.domain.factories.IMomentoAvaliacaoFactory;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.MomentoAvaliacaoDomainDTOAssembler;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.MomentoAvaliacaoRepository;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MomentoAvaliacaoService {

    @Autowired
    IMomentoAvaliacaoFactory factory;

    @Autowired
    MomentoAvaliacaoRepository momentoAvaliacaoRepository;

    @Autowired
    MomentoAvaliacaoDomainDTOAssembler momentoAvaliacaoAssembler;

    @Autowired
    EdicaoRepository edicaoRepository;

    @Autowired
    EdicaoJpaRepository edicaoJpaRepository;

    @Autowired
    MomentoAvaliacaoDomainDataAssembler momentoAvaliacaoDomainDTOAssembler;

    @Autowired
    EdicaoDomainDataAssembler edicaoDomainDataAssembler;

    public MomentoAvaliacaoService() {
    }

    public MomentoAvaliacaoDTO createAndSaveMomentoAvaliacao(MomentoAvaliacaoDTO info) throws Exception {
        MomentoAvaliacao momentoAvaliacao = factory.createMomentoAvaliacao(info.getCodEdicao(),info.getDenominacao());
        MomentoAvaliacao momentoAvaliacaoSave = momentoAvaliacaoRepository.save(momentoAvaliacao);


        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(info.getCodEdicao());
        if (opEdicao.isPresent()) {
            Edicao edicao = opEdicao.get();
            //edicao.addMomentoAvaliacao(momentoAvaliacaoSave);
            edicao.getMomentoAvaliacaoList().add(momentoAvaliacaoSave);
            Edicao nova =edicaoRepository.addAndSaveMA(edicao);
        }


        MomentoAvaliacaoDTO momentoAvaliacaoDTO = momentoAvaliacaoAssembler.toDTO(momentoAvaliacaoSave);

        return momentoAvaliacaoDTO;
    }

    public Optional<MomentoAvaliacaoDTO> getMomentoAvaliacaoByCode(int codMomentoAvaliacao) {

        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = momentoAvaliacaoRepository.findBycodMomentoAvaliacao(codMomentoAvaliacao);

        return opMomentoAvaliacao;
    }

    public List<MomentoAvaliacaoDTO> getAllMomentoAvaliacao() {
        List<MomentoAvaliacao> lista = momentoAvaliacaoRepository.findAll();

        List<MomentoAvaliacaoDTO> listaDto = new ArrayList<>();
        for (MomentoAvaliacao momentoAvaliacao : lista) {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = momentoAvaliacaoAssembler.toDTO(momentoAvaliacao);
            listaDto.add(momentoAvaliacaoDTO);
        }
        return listaDto;
    }
}
