package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IEdicaoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.NewEdicaoInfoDTO;
import WSEdicao.dto.UcDTO;
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

    /*public Edicao createAndSaveEdicao(Uc codUc, AnoLetivo codAnoLetivo ) {
        Edicao edicao = edicaoFactory.createEdicao(codUc,codAnoLetivo);
        return edicaoRepository.save(edicao);
    }*/

    public EdicaoDTO createAndSaveEdicao(int codUc, int codAnoLetivo) {

        /*Uc uc =new Uc(info.getCodUc());
        AnoLetivo anoLetivo = new AnoLetivo(info.getCodAnoLetivo());

        Edicao edicao = edicaoFactory.createEdicao(uc,anoLetivo);
        Edicao edicaoSaved = edicaoRepository.save(edicao);
        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicaoSaved.getUc().getCodUc(),edicaoSaved.getAnoLetivo().getCodAnoLetivo());

        return edicaoDTO;*/

        Optional<UcDTO> optionalUc = ucRepository.findBycodUc(codUc);
        Optional<AnoLetivoDTO> optionalAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        if (optionalUc.isPresent() && optionalAnoLetivo.isPresent()) {
            Uc uc = ucDTOAssember.toDomain(optionalUc.get());
            AnoLetivo anoLetivo = anoLetivoDTOAssembler.toDomain(optionalAnoLetivo.get());
            Edicao edicao = edicaoFactory.createEdicao(uc,anoLetivo);
            Edicao savedEdicao = edicaoRepository.save(edicao);
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(savedEdicao);

            return edicaoDTO;
        } else
            return null;
    }

    public Optional<EdicaoDTO> getEdicaoByCode(int codEdicao) {

        Optional<EdicaoDTO> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);

        return opEdicao;
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
}
