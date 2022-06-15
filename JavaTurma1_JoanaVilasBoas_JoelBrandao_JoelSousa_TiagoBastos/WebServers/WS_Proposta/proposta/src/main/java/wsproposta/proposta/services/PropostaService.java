package wsproposta.proposta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.DTO.PropostaDTOParcial;
import wsproposta.proposta.DTO.assemblers.PropostaDomainDTOAssembler;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.domain.factories.IPropostaFactory;
import wsproposta.proposta.repositories.ProjetoWebRepository;
import wsproposta.proposta.repositories.PropostaRepository;
import wsproposta.proposta.repositories.iRepositories.IOrganizacaoWebRepository;
import wsproposta.proposta.repositories.iRepositories.IUtilizadorWebRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    ProjetoWebRepository projetoWebRepository;






    public PropostaService() {
    }

    /**
     * Método para criar e gravar uma proposta com os dados recebidos por parametro, NewPropostaInfoDTO, para tal
     * comunica com utilizadorWebRep eOrganizacaoWebRep, para confirmar a existencia do utilizzador e nif introduzidos,
     * e com a factory para criar objecto aproposta e Repository para guardar Proposta
     * @param propostaInfoDTO contem os dados para criacao da proposta excepto, codigo e estado da proposta.
     * @return propostaDTO com os dados do objecto criado, incluindo estado, e status created,
     *  ou badRequest caso os dados introduzidos nao sejam validos
     * @throws Exception
     */

    public PropostaDTO createAndSaveProposta(NewPropostaInfoDTO propostaInfoDTO) throws Exception {

        Optional<UtilizadorRestDTO> utilizador = utilizadorWebRepository.findUtilizadorByCodUtilizador(propostaInfoDTO.getCodUtilizador());
        Optional<OrganizacaoRestDTO> organizacao = organizacaoWebRepository.findOrganizacaoByNifOrganizacao(propostaInfoDTO.getNifOrganizacao());

        if (utilizador.isPresent() && organizacao.isPresent()) {

            Proposta proposta = propostaFactory.createProposta(propostaInfoDTO.getCodUtilizador(), propostaInfoDTO.getNifOrganizacao(),
                    propostaInfoDTO.getCodEdicao(), propostaInfoDTO.getTitulo(), propostaInfoDTO.getProblema(), propostaInfoDTO.getObjetivo());

            Proposta propostaSaved = propostaRepository.save(proposta);

            PropostaDTO propostaDTO = propostaAssembler.toDTO(propostaSaved);

            return propostaDTO;
        } else
            throw new Exception("Proposta não foi criada porque o valor dos parâmetros Código Utilizador ou NIF organizacao não constam na base de dados");
    }

    /**
     * Metodo retorna uma lista de todas as propostas na BD, para tal comunica com o
     * Repositorio, e de seguida transforma Lista de propostas em lista propostasDTO
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     *  propostas na BD
     */

    public List<PropostaDTO> findAll() {
        List<Proposta> listPropostas = propostaRepository.findAll();

        List<PropostaDTO> listPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            listPropostaDTO.add(propostaDTO);
        }
        return listPropostaDTO;
    }

    /**
     * Metodo retorna uma proposta da BD com codigo Proposta recebido por parametro , para tal comunica com o
     * Repositorio, e de seguida transforma o objecto recebido em propostasDTO
     * @param codProposta é o identificador da proposta na BD, definido como o código da proposta
     * @return uma propostaDTO
     */
    public Optional<PropostaDTO> getPropostaById(int codProposta) {

        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        if (opProposta.isPresent()) {
            Proposta proposta = opProposta.get();
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            Optional<PropostaDTO> opPropostaDTo = Optional.of(propostaDTO);
            return opPropostaDTo;
        } else return Optional.empty();
    }


    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo codigo de utilizador, para tal comunica com o
     * Repositorio, e de seguida transforma os objectos recebidos em propostaDTO
     * @param codUtilizador codUtilizador é o identificador ddo utilizador na BD, definido como o código de utilizador
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR

    public List<PropostaDTO> findAllByCodUtilizador(int codUtilizador) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByCodUtilizador(codUtilizador);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }

    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo NIF da Organizacao,
     * comunica com o repositorio, e com o DataAssembler para transformar em Proposta em PropostaDTO
     * @param nr é o identificador do NIF Organizacao na BD, definido como o NIF da organizacao
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF

    public List<PropostaDTO> findAllPropostasByNifOrganizacao(long nr) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByNifOrganizacao(nr);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }

    /**
     * Método que retorna uma lista com todas as propostas que contenham a String introduzida, e recebida por parametro,
     * no titulo da proposta, comunica com Repositorio e DataAssembler para para transformar em Proposta em PropostaDTO
     * @param titulo é uma string com caracteres que devem estar contidos no titulo da proposta na BD
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE TITULO

    public List<PropostaDTO> findAllPropostasByTitulo(String titulo) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByTitulo(titulo);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }

    /**
     * Método que permite fazer o update do estado da proposta, comunica com o repositorio para fazer
     * um get da proposta por codProposta, faz set aos parametros a modificar, volta ao Repositorio
     * para guardar a proposta, e depois com o Assembler tranforma em PropostaDTO
     * @param propostaUpdate é um DTO que recebe apenas o codigo da proposta que se pretende alterar,
     * e String com o valor do estado a ser introduzido
     * @param codProposta é o identificador da proposta na BD, definido como o código da proposta
     * @return uma propostaDTO com todos os parametros, incluindo estado, e exceptuando codProposta
     */

    //MÉTODOs UODATE ESTADO PROPOSTA

    public PropostaDTO updateEstadoProposta(PropostaDTOParcial propostaUpdate, int codProposta) {

        Optional<Proposta> opProposta = propostaRepository.findById(codProposta);

        opProposta.get().setCodProposta(propostaUpdate.getCodProposta());
        opProposta.get().setEstado(Proposta.Estado.valueOf(propostaUpdate.getEstado()));

        Proposta propostaSaved = propostaRepository.save(opProposta.get());
        PropostaDTO propostaSavedDTO = propostaAssembler.toDTO(propostaSaved);

        ProjetoRestDto projetoParcial = new ProjetoRestDto(opProposta.get().getCodUtilizador(), codProposta);
        Optional<ProjetoRestDto> optionalProjetoRestDto = projetoWebRepository.createAndSaveProjeto(projetoParcial);


        return propostaSavedDTO;
    }

    //MÉTODO GET PROPOSTAS BY CODE EDICAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTA EDICAO

    public List<PropostaDTO> findAllByCodEdicao(int codEdicao) {

        List<Proposta> listFiltradaPropostas = propostaRepository.findAllByCodEdicao(codEdicao);

        List<PropostaDTO> listFiltradaPropostaDTO = new ArrayList<>();
        for (Proposta proposta : listFiltradaPropostas) {
            PropostaDTO propostaDTO = propostaAssembler.toDTO(proposta);
            listFiltradaPropostaDTO.add(propostaDTO);
        }
        return listFiltradaPropostaDTO;
    }
}
