package com.wsOrganizacao.services;


import com.wsOrganizacao.datamodel.REST.NifRestDTO;
import com.wsOrganizacao.DTO.OrganizacaoDTO;
import com.wsOrganizacao.DTO.assemblers.OrganizacaoDomainDTOAssembler;
import com.wsOrganizacao.domain.entities.Organizacao;
import com.wsOrganizacao.domain.factories.IOrganizacaoFactory;
import com.wsOrganizacao.repositories.OrganizacaoRepository;
import com.wsOrganizacao.repositories.NifWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrganizacaoService {

    @Autowired
    OrganizacaoDomainDTOAssembler organizacaoDomainDTOAssembler;

    @Autowired
    IOrganizacaoFactory organizacaoFactory;

    @Autowired
    OrganizacaoRepository organizacaoRepository;

    @Autowired
    NifWebRepository nifWebRepository;


    public OrganizacaoService() {
    }

    public OrganizacaoDTO createAndSaveOrganizacao(OrganizacaoDTO organizacaoDTO) {

        Optional<NifRestDTO> nNif = nifWebRepository.findByNif(organizacaoDTO.getNNif());

        if (nNif.isPresent()) {


            Organizacao organizacao = organizacaoFactory.createOrganizacao(organizacaoDTO.getNNif(),organizacaoDTO.getSDenominacao());
            Organizacao organizacaoSaved = organizacaoRepository.save(organizacao);

            OrganizacaoDTO organizacaoDTO1 = organizacaoDomainDTOAssembler.toDTO(organizacaoSaved.getNNif(),organizacaoSaved.getSDenominacao());

            return organizacaoDTO1;

        }return null;
        

    }

    public Organizacao save(Organizacao newOrganizacao) {
        return organizacaoRepository.save(newOrganizacao);
    }


    public OrganizacaoDTO getOrganizacaoByNif(int nNif){
        Optional<Organizacao> optionalOrganizacao = organizacaoRepository.findByNif(nNif);
        if (optionalOrganizacao.isPresent()) {
            Organizacao organizacao = optionalOrganizacao.get();
            OrganizacaoDTO oDTO = organizacaoDomainDTOAssembler.toDTO(organizacao.getNNif(),organizacao.getSDenominacao());

            return oDTO;
        } else return null;

    }


    public Optional<NifRestDTO> findOrganizacaoByNif(int nNif) {

        Optional<NifRestDTO> opNif = nifWebRepository.findByNif(nNif);

        return opNif;
    }


}
