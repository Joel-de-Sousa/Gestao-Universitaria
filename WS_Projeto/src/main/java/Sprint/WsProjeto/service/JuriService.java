package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.JuriIDException;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.domain.factories.IProjetoFactory;
import Sprint.WsProjeto.repositories.*;
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

    @Autowired
    AvaliacaoRepository avaliacaoRepository;




    public JuriService() {
    }

    public JuriDTO createAndSaveJuri(NewJuriInfoDTO juriInfoDto) throws JuriIDException {

        if(juriInfoDto.getCodArguente()!=juriInfoDto.getCodOrientador()&&juriInfoDto.getCodArguente()!=juriInfoDto.getCodPresidente()&& juriInfoDto.getCodPresidente()!=juriInfoDto.getCodOrientador()) {
            Juri juri = juriFactory.createJuri(juriInfoDto.getCodPresidente(), juriInfoDto.getCodOrientador(), juriInfoDto.getCodArguente());

            Juri oJuriSaved = juriRepository.save(juri);

            Optional<Avaliacao> opAvaliacao=avaliacaoRepository.findById(juriInfoDto.getCodAvaliacao());
            Avaliacao avaliacao=opAvaliacao.get();
            opAvaliacao.get().setJuri(oJuriSaved);
            Avaliacao avaliacaoSave=avaliacaoRepository.save(avaliacao);

            JuriDTO oJuriDTO = juriDomainDTOAssembler.toDto(oJuriSaved);

            return oJuriDTO;
        }else
            throw new JuriIDException("O Docente não pode desempenhar 2 papeis no Juri");
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