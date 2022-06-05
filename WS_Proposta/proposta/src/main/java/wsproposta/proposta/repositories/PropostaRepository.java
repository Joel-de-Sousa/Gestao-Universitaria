package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.JPA.PropostaJPA;
import wsproposta.proposta.datamodel.JPA.assembler.PropostaDomainDataAssembler;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.repositories.JPA.PropostaJPARepository;
import wsproposta.proposta.repositories.iRepositories.IPropostaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRepository implements IPropostaRepository {
    @Autowired
    PropostaJPARepository propostaJPARepository;
    @Autowired
    PropostaDomainDataAssembler propostaAssembler;


    /**
     * Metodo que permite gravar uma Proposta, comunica com o DataAssembler para transformar Proposta
     * recebida por parametro em PropostaJPA, atraves do JPARepositorio guarda o objecto PropostaJPA,
     * volta a usar o DataAssembler para retornar uma Proposta guardada
     * @param proposta parametro do tipo Proposta
     * @return retorna o objecto que foi guardado na BD, Proposta
     */
    public Proposta save (Proposta proposta){
        PropostaJPA propostaJPA = propostaAssembler.toData(proposta);

        PropostaJPA savedPropostaJPA = propostaJPARepository.save(propostaJPA);

        Proposta savedProposta = propostaAssembler.toDomain(savedPropostaJPA);
        return savedProposta;
    }

    /**
     * Metodo retorna um Optional de proposta da BD com codigo Proposta recebido por parametro,
     * comunica com o JPARepositorio e com o DataAssembler para transformar PropostaJPA em Proposta
     * @param codProposta é o identificador da proposta na BD, definido como o código da proposta
     * @return um Optional de proposta ou empty caso nao exista proposta com esse codigo
     */

    public Optional<Proposta> findById(int codProposta) {
        Optional<PropostaJPA> opProposta = propostaJPARepository.findById(codProposta);

        if ( opProposta.isPresent() ) {
            Proposta proposta = propostaAssembler.toDomain(opProposta.get());
            return Optional.of( proposta );
        }
        else
            return Optional.empty();
    }

    /**
     * Metodo retorna uma lista de todas as propostas da BD, para tal comunica com o
     * RepositorioJPA, e de seguida transforma Lista de propostasJPA em lista propostas
     * @return retorna uma lista de proposta, ou uma lista vazia caso não existam
     * propostas na BD
     */

    public List<Proposta> findAll(){
        List<PropostaJPA> listPropostasJPA = propostaJPARepository.findAll();
        List<Proposta> listPropostas =new ArrayList<>();
        for (PropostaJPA p:listPropostasJPA) {
            listPropostas.add(propostaAssembler.toDomain(p));
        }
        return listPropostas;
    }

    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo codigo de utilizador, para tal comunica com o
     * RepositorioJPA, e de seguida transforma os objectos recebidos em proposta, atraves do DataAssembler
     * @param codUtilizador codUtilizador é o identificador ddo utilizador na BD, definido como o código de utilizador
     * @return etorna uma lista de propostas, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR
    public List<Proposta> findAllByCodUtilizador (int codUtilizador){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findAllByCodUtilizador(codUtilizador);
        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            listFiltradaPropostas.add(propostaAssembler.toDomain(p));
        }
        return listFiltradaPropostas;
    }

    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo NIF da Organizacao,
     * comunica com o repositorioJPA, e com o DataAssembler para transformar em PropostaJPA em Proposta
     * @param nr é o identificador do NIF Organizacao na BD, definido como o NIF da organizacao
     * @return retorna uma lista de propostas, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF
    public List<Proposta> findAllByNifOrganizacao (long nr){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findAllByNifOrganizacao(nr);
        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            listFiltradaPropostas.add(propostaAssembler.toDomain(p));
        }
        return listFiltradaPropostas;
    }

    /**
     * Método que retorna uma lista com todas as propostas que contenham a String introduzida, e recebida por parametro,
     *  no titulo da proposta, comunica com RepositorioJPA e DataAssembler para para transformar em PropostaJPA em Proposta
     * @param titulo é uma string com caracteres que devem estar contidos no titulo da proposta na BD
     * @return retorna uma lista de Proposta, ou uma lista vazia caso não existam
     * propostas na BD
     */

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE TITULO
    public List<Proposta> findAllByTitulo (String titulo){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findByTituloContains(titulo);

        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            Proposta proposta = propostaAssembler.toDomain(p);
            listFiltradaPropostas.add(proposta);
        }
        return listFiltradaPropostas;
    }
}
