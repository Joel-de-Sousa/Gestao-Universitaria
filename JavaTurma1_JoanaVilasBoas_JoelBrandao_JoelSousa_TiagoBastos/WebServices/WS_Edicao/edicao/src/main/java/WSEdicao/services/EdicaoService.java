package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IEdicaoFactory;
import WSEdicao.dto.*;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class EdicaoService {

    @Autowired
    IEdicaoFactory edicaoFactory;

    @Autowired
    EdicaoRepository edicaoRepository;

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    @Autowired
    UcDomainDTOAssembler ucDTOAssember;

    @Autowired
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @Autowired
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    public EdicaoService() {
    }

    public EdicaoDTO createAndSaveEdicao(int codUc, int codAnoLetivo) throws Exception {

        Optional<UcDTO> optionalUc = ucRepository.findBycodUc(codUc);
        Optional<AnoLetivoDTO> optionalAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        if (optionalUc.isPresent() && optionalAnoLetivo.isPresent()) {

            Edicao edicao = edicaoFactory.createEdicao(codUc,codAnoLetivo);
            Edicao savedEdicao = edicaoRepository.save(edicao);
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(savedEdicao);

            return edicaoDTO;
        } else
            throw new IllegalArgumentException();
    }

    public Optional<EdicaoDTO> getEdicaoByCode(int codEdicao) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);
        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(opEdicao.get());

        return Optional.of(edicaoDTO);
    }

    public List<EdicaoDTO> getAllEdicao() {
        List<Edicao> listEdicao = edicaoRepository.findAll();

        List<EdicaoDTO> listaDto=new ArrayList<>();
        for (Edicao edicao:listEdicao) {
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);
            listaDto.add(edicaoDTO);
        }
        return listaDto;
    }

    public List<EdicaoAllArgsDTO> getEdicaoAllArgs(){
        List<Edicao> listEdicao = edicaoRepository.findAll();

        List<EdicaoAllArgsDTO> listaDto=new ArrayList<>();
        for (Edicao edicao:listEdicao) {

            EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(edicao);
            listaDto.add(edicaoAllArgsDTO);
        }
        return listaDto;
    }

    public Optional<EdicaoAllArgsDTO> getEdicaoAllArgsByCode(int codEdicao) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);
        EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(opEdicao.get());

        return Optional.of(edicaoAllArgsDTO);
    }


}
