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

    public Proposta save (Proposta proposta){
        PropostaJPA propostaJPA = propostaAssembler.toData(proposta);

        PropostaJPA savedPropostaJPA = propostaJPARepository.save(propostaJPA);

        Proposta savedProposta = propostaAssembler.toDomain(savedPropostaJPA);
        return savedProposta;
    }

    public Optional<Proposta> findById(int codProposta) {
        Optional<PropostaJPA> opProposta = propostaJPARepository.findById(codProposta);

        if ( opProposta.isPresent() ) {
            Proposta proposta = propostaAssembler.toDomain(opProposta.get());
            return Optional.of( proposta );
        }
        else
            return Optional.empty();
    }

    public List<Proposta> findAll(){
        List<PropostaJPA> listPropostasJPA = propostaJPARepository.findAll();
        List<Proposta> listPropostas =new ArrayList<>();
        for (PropostaJPA p:listPropostasJPA) {
            listPropostas.add(propostaAssembler.toDomain(p));
        }
        return listPropostas;
    }

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR
    public List<Proposta> findAllByCodUtilizador (int codUtilizador){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findAllByCodUtilizador(codUtilizador);
        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            listFiltradaPropostas.add(propostaAssembler.toDomain(p));
        }
        return listFiltradaPropostas;
    }

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF
    public List<Proposta> findAllByNifOrganizacao (long nr){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findAllByNifOrganizacao(nr);
        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            listFiltradaPropostas.add(propostaAssembler.toDomain(p));
        }
        return listFiltradaPropostas;
    }

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE TITULO
    public List<Proposta> findAllByTitulo (String titulo){
        List<PropostaJPA> listFiltradaPropostasJPA = propostaJPARepository.findByTituloContains(titulo);

        List<Proposta> listFiltradaPropostas =new ArrayList<>();
        for (PropostaJPA p:listFiltradaPropostasJPA) {
            Proposta proposta = propostaAssembler.toDomain(p);
            //if(proposta.getTitulo().toUpperCase().contains(titulo.toUpperCase()))
            listFiltradaPropostas.add(proposta);
        }
        return listFiltradaPropostas;
    }
}
