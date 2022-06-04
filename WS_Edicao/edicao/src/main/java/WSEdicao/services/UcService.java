package WSEdicao.services;

import WSEdicao.domain.entities.Uc;
import WSEdicao.domain.factories.IUcFactory;
import WSEdicao.dto.UcDTO;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UcService {

    @Autowired
    IUcFactory ucFactory;

    @Autowired
    UcRepository ucRepository;

    @Autowired
    UcDomainDTOAssembler ucAssembler;

    public UcService() {
    }

    public UcDTO createAndSaveUc(String sSigla, String sDenominacao ) {
        Uc uc = ucFactory.createUc( sSigla, sDenominacao);
        Uc ucSave = ucRepository.save(uc);
        UcDTO ucDTO=ucAssembler.toDTO(ucSave);

        return ucDTO;
    }

    public Optional<UcDTO> getUcByCode(int codUc) {

        Optional<UcDTO> opUc = ucRepository.findBycodUc(codUc);

        return opUc;
    }

    public List<UcDTO> getAllUc(){
        List<Uc> lista= ucRepository.findAll();

        List<UcDTO> listaDto=new ArrayList<>();
        for (Uc uc:lista) {
            UcDTO ucDTO = ucAssembler.toDTO(uc);
            listaDto.add(ucDTO);
        }
        return listaDto;
    }
}
