package WSEdicao.services;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.domain.factories.IMomentoAvaliacaoFactory;
import WSEdicao.dto.MomentoAvaliacaoDTO;
import WSEdicao.dto.assemblers.MomentoAvaliacaoDomainDTOAssembler;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.MomentoAvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MomentoAvaliacaoService {

    @Autowired
    IMomentoAvaliacaoFactory maFactory;

    @Autowired
    MomentoAvaliacaoRepository maRepository;

    @Autowired
    MomentoAvaliacaoDomainDTOAssembler maDTOAssembler;

    @Autowired
    EdicaoRepository edicaoRepository;

    public MomentoAvaliacaoService() {
    }

    public MomentoAvaliacaoDTO createAndSaveMomentoAvaliacao(MomentoAvaliacaoDTO info) throws Exception {
        MomentoAvaliacao momentoAvaliacao = maFactory.createMomentoAvaliacao(info.getDenominacao());
        MomentoAvaliacao momentoAvaliacaoSave = maRepository.save(momentoAvaliacao);

        /*Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(info.getCodEdicao());
        if (opEdicao.isPresent()) {
            Edicao edicao = opEdicao.get();
            edicao.getMomentoAvaliacaoList().add(momentoAvaliacaoSave);
            Edicao nova =edicaoRepository.saveWithoutValidation(edicao);
        }*/

        MomentoAvaliacaoDTO momentoAvaliacaoDTO = maDTOAssembler.toDTO(momentoAvaliacaoSave);

        return momentoAvaliacaoDTO;
    }

    public Optional<MomentoAvaliacaoDTO> getMomentoAvaliacaoByCode(int codMomentoAvaliacao) {

        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = maRepository.findBycodMomentoAvaliacao(codMomentoAvaliacao);

        return opMomentoAvaliacao;
    }

    public List<MomentoAvaliacaoDTO> getAllMomentoAvaliacao() {
        List<MomentoAvaliacao> lista = maRepository.findAll();

        List<MomentoAvaliacaoDTO> listaDto = new ArrayList<>();
        for (MomentoAvaliacao momentoAvaliacao : lista) {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = maDTOAssembler.toDTO(momentoAvaliacao);
            listaDto.add(momentoAvaliacaoDTO);
        }
        return listaDto;
    }

    /*public List<MomentoAvaliacaoDTO> getAllMomentoAvaliacaoByCodEdicao(int codEdicao) {

        List<MomentoAvaliacao> lista = maRepository.findAllByCodEdicao(codEdicao);

        List<MomentoAvaliacaoDTO> listaDto = new ArrayList<>();
        for (MomentoAvaliacao momentoAvaliacao : lista) {
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = maDTOAssembler.toDTO(momentoAvaliacao);
            listaDto.add(momentoAvaliacaoDTO);
        }
        return listaDto;
    }*/
}
