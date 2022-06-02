package wsproposta.proposta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.ErrorDTO;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.DTO.PropostaDTOParcial;
import wsproposta.proposta.DTO.assemblers.PropostaDomainDTOAssembler;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
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

    @Autowired
    PropostaDomainDTOAssembler propostaDTOAssembler;


    public PropostaService() {
    }

    public PropostaDTO createAndSaveProposta(NewPropostaInfoDTO propostaInfoDTO) throws Exception {

       /* Optional<UtilizadorRestDTO> utilizador = utilizadorWebRepository.findUtilizadorByCodUtilizador(propostaInfoDTO.getCodUtilizador());
        Optional<OrganizacaoRestDTO> organizacao = organizacaoWebRepository.findOrganizacaoByNifOrganizacao(propostaInfoDTO.getNifOrganizacao());

        if (utilizador.isPresent() && organizacao.isPresent()) {
*/
        Optional<OrganizacaoRestDTO> organizacao = organizacaoWebRepository.findOrganizacaoByNifOrganizacao(propostaInfoDTO.getNifOrganizacao());

        if (organizacao.isPresent()) {
            Proposta proposta = propostaFactory.createProposta(propostaInfoDTO.getCodUtilizador(), propostaInfoDTO.getNifOrganizacao(),
                    propostaInfoDTO.getCodEdicao(), propostaInfoDTO.getTitulo(), propostaInfoDTO.getProblema(), propostaInfoDTO.getObjetivo()/*, Proposta.Estado.valueOf(propostaInfoDTO.getEstado())*/);

            Proposta propostaSaved = propostaRepository.save(proposta);

            PropostaDTO propostaDTO = propostaAssembler.toDTO(propostaSaved.getCodUtilizador(), propostaSaved.getNifOrganizacao(),
                    propostaSaved.getCodEdicao(), propostaSaved.getTitulo(), propostaSaved.getProblema(), propostaSaved.getObjetivo(), valueOf(propostaSaved.getEstado()));

            return propostaDTO;
        } else
            throw new Exception("Proposta não foi criada porque o valor dos parâmetros Código Utilizador ou NIF organizacao não constam na base de dados");
    }

    public List<PropostaDTO> findAll() {
        List<Proposta> listPropostas = propostaRepository.findAll();

        List<PropostaDTO> listPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            listPropostaDTO.add(propostaDTO);
        }
        return listPropostaDTO;
    }

    public PropostaDTO getPropostaById(int codProposta) {

        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        if (opProposta.isPresent()) {
            Proposta proposta = opProposta.get();
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            return propostaDTO;
        } else return null;
    }

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR

    public List<PropostaDTO> findAllByCodUtilizador(int codUtilizador) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByCodUtilizador(codUtilizador);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF

    public List<PropostaDTO> findAllPropostasByNifOrganizacao(long nr) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByNifOrganizacao(nr);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE TITULO

    public List<PropostaDTO> findAllPropostasByTitulo(String titulo) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByTitulo(titulo);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta.getCodUtilizador(), proposta.getNifOrganizacao(), proposta.getCodEdicao(),
                    proposta.getTitulo(), proposta.getProblema(), proposta.getObjetivo(), proposta.getEstado().toString());
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }


    //MÉTODOs SAVE PARA USAR PUT

/*    public Optional<Proposta> getPropostaToReplaceEstadoById (int codProposta) {
        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);
        return opProposta;
    }

    public Proposta save(Proposta novo) {
        return propostaRepository.save(novo);
    }*/

    public Proposta updateEstadoProposta(PropostaDTOParcial propostaUpdate, int codProposta) {

        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        opProposta.get().setCodProposta(propostaUpdate.getCodProposta());
        opProposta.get().setEstado(Proposta.Estado.valueOf(propostaUpdate.getEstado()));

        return propostaRepository.save(opProposta.get());



       /* Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        return opProposta
                .map(proposta -> {
                    proposta.setCodProposta(propostaUpdate.getCodProposta());
                    proposta.setEstado(Proposta.Estado.valueOf(propostaUpdate.getEstado()));

                    return propostaRepository.save(proposta);
                })
                .orElseGet(() -> {
                    propostaUpdate.setCodProposta(codProposta);
                    return propostaRepository.save(propostaDTOAssembler.toDomain(propostaUpdate));
                });*/
}

    /*//METODOS PARA USAR PATCH

    public Proposta save(PropostaDTOParcial propPartial, int codProposta) {
        return propostaRepository.save(propPartial, codProposta);
    }*/

}
