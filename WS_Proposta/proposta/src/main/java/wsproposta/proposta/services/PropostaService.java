package wsproposta.proposta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.DTO.assemblers.PropostaDomainDTOAssembler;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.domain.factories.IPropostaFactory;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.iRepositories.IOrganizacaoWebRepository;
import wsproposta.proposta.repositories.iRepositories.IUtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.String.valueOf;

@Service
public class PropostaService {

    @Autowired
    IPropostaFactory propostaFactory;
    @Autowired
    PropostaRepository propostaRepository;
    @Autowired
    PropostaDomainDTOAssembler propostaAssembler;
    @Autowired
    IUtilizadorWebRepository utilizadorWebRepository;
    @Autowired
    IOrganizacaoWebRepository organizacaoWebRepository;


    public PropostaService(){}

    public PropostaDTO createAndSaveProposta(NewPropostaInfoDTO propostaInfoDTO) {

       /* Optional<UtilizadorRestDTO> utilizador = utilizadorWebRepository.findUtilizadorByCodUtilizador(propostaInfoDTO.getCodUtilizador());
        Optional<OrganizacaoRestDTO> organizacao = organizacaoWebRepository.findOrganizacaoByNifOrganizacao(propostaInfoDTO.getNifOrganizacao());

        if (utilizador.isPresent() && organizacao.isPresent()) {
*/
            Proposta proposta = propostaFactory.createProposta(propostaInfoDTO.getCodUtilizador(), propostaInfoDTO.getNifOrganizacao(),
                    propostaInfoDTO.getCodEdicao(), propostaInfoDTO.getTitulo(), propostaInfoDTO.getProblema(), propostaInfoDTO.getObjetivo()/*, Proposta.Estado.valueOf(propostaInfoDTO.getEstado())*/);

            Proposta propostaSaved = propostaRepository.save(proposta);

            PropostaDTO propostaDTO = propostaAssembler.toDTO(propostaSaved.getCodUtilizador(), propostaSaved.getNifOrganizacao(),
                    propostaSaved.getCodEdicao(), propostaSaved.getTitulo(), propostaSaved.getProblema(), propostaSaved.getObjetivo(), valueOf(propostaSaved.getEstado()));

            return propostaDTO;
       /* }
        return null;*/
    }

    public List<PropostaDTO> findAll() {
        List<Proposta> listPropostas = propostaRepository.findAll();

        List<PropostaDTO> listPropostaDTO = new ArrayList<>();
        for(Proposta proposta : listPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            listPropostaDTO.add(propostaDTO);
        }
        return listPropostaDTO;
    }

    public PropostaDTO getPropostaById (int codProposta) {

        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        if (opProposta.isPresent()){
            Proposta proposta = opProposta.get();
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            return propostaDTO;
        }else return  null;
    }
}
