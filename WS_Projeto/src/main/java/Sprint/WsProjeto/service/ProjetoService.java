package Sprint.WsProjeto.service;

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
// duvida
   /* public ProjetoDTO createAndSaveProjeto(ProjetoDTO projetoDTO){
        Optional<UtilizadorRestDTO> oUtilizador = utilizadorWebRepository.findUtilizadorByCode(projetoDTO.getCodEstudante());
        Optional<PropostaRestDTO> oProposta = propostaWebRepository.findPropostaByCode(projetoDTO.getCodProposta());


    }*/

    public ProjetoDTO findProjetoByCode(int code) {

        Optional<Projeto> opProjeto = projetoRepository.findByCode(code);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }

    public Projeto save(Projeto newProjeto) {
        return projetoRepository.save(newProjeto);
    }

    public Optional<UtilizadorRestDTO> findUtilizadorByCode(int code) {

        Optional<UtilizadorRestDTO> oUtilizadorCode = utilizadorWebRepository.findUtilizadorByCode(code);

        return oUtilizadorCode;
    }

    public Optional<PropostaRestDTO> findPropostaByCode(int code) {

        Optional<PropostaRestDTO> oPropostaCode = propostaWebRepository.findPropostaByCode(code);

        return oPropostaCode;
    }


}
