package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.domain.factories.IProjetoFactory;
import Sprint.WsProjeto.repositories.JuriRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuriService {
    @Autowired
    JuriDomainDTOAssembler juriDomainDTOAssembler;

    @Autowired
    IJuriFactory juriFactory;

    @Autowired
    JuriRepository juriRepository;


    public JuriService() {
    }

    public JuriDTO createAndSaveJuri(NewJuriInfoDTO juriInfoDto){

        Juri juri = juriFactory.createJuri(juriInfoDto.getCodPresidente(),juriInfoDto.getCodOrientador(), juriInfoDto.getCodArguente());

        Juri oJuriSaved = juriRepository.save(juri);

        JuriDTO oJuriDTO = juriDomainDTOAssembler.toDto(oJuriSaved);

        return oJuriDTO;

    }

    public JuriDTO findJuriByCode(int codJuri) {

        Optional<Juri> opJuri = juriRepository.findById(codJuri);

        if (opJuri.isPresent()) {
            Juri oJuri = opJuri.get();
            JuriDTO oJuriDTO = juriDomainDTOAssembler.toDto(oJuri);

            return oJuriDTO;
        } else return null;
    }
}
