package WSEdicao.services;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.factories.IAnoLetivoFactory;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.AnoLetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AnoLetivoService {

    @Autowired
    IAnoLetivoFactory factory;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    @Autowired
    AnoLetivoDomainDTOAssembler anoLetivoAssembler;

    public AnoLetivoService() {
    }

    public AnoLetivoDTO createAndSaveAnoLetivo(String ano) throws Exception {
        AnoLetivo anoLetivo = factory.createAnoLetivo(ano);
        AnoLetivo anoLetivoSave = anoLetivoRepository.save(anoLetivo);
        AnoLetivoDTO anoLetivoDTO = anoLetivoAssembler.toDTO(anoLetivoSave);

        return anoLetivoDTO;
    }

    public Optional<AnoLetivoDTO> getAnoLetivoByCode(int codAnoLetivo ) {

        Optional<AnoLetivoDTO> opAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        return opAnoLetivo;
    }

    public List<AnoLetivoDTO> getAllAnoLetivo(){
        List<AnoLetivo> lista= anoLetivoRepository.findAll();

        List<AnoLetivoDTO> listaDto=new ArrayList<>();
        for (AnoLetivo anoLetivo:lista) {
            AnoLetivoDTO anoLetivoDTO = anoLetivoAssembler.toDTO(anoLetivo);
            listaDto.add(anoLetivoDTO);
        }
        return listaDto;
    }
}
