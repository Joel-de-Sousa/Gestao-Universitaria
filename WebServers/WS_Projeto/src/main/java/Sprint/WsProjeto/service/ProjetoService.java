package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IProjetoFactory;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @Autowired
    IProjetoFactory projetoFactory;

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    UtilizadorWebRepository utilizadorWebRepository;

    @Autowired
    PropostaWebRepository propostaWebRepository;

    public ProjetoService() {
    }

    public ProjetoDTO createAndSaveProjeto(NewProjetoInfoDto projetoInfoDto){

        Projeto projeto = projetoFactory.createProjeto(projetoInfoDto.getCodProposta(),projetoInfoDto.getCodEstudante());

        Projeto oProjetoSaved = projetoRepository.save(projeto);

        ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjetoSaved);

      return oProjetoDTO;

    }

    public ProjetoDTO findProjetoByCode(int codProjeto) {

        Optional<Projeto> opProjeto = projetoRepository.findById(codProjeto);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }

    public ProjetoDTO findProjetoByCodeEstudante (int codEstudante) {

        Optional<Projeto> opProjeto = projetoRepository.findByCodEstudante(codEstudante);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }



    public Optional<UtilizadorRestDTO> findUtilizadorByCode(int codUtilizador) {

        Optional<UtilizadorRestDTO> oUtilizadorCode = utilizadorWebRepository.findUtilizadorByCode(codUtilizador);

        return oUtilizadorCode;
    }

    public Optional<PropostaRestDTO> findPropostaByCode(int codProposta) {

        Optional<PropostaRestDTO> oPropostaCode = propostaWebRepository.findPropostaByCode(codProposta);

        return oPropostaCode;
    }


}
