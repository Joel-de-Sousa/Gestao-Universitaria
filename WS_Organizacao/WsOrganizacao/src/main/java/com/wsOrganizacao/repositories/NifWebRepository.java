package com.wsOrganizacao.repositories;

import com.wsOrganizacao.datamodel.REST.NifRestDTO;
import com.wsOrganizacao.repositories.REST.NifRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class NifWebRepository  {

    @Autowired
    NifRestRepository nifRestRepository;

    public Optional<NifRestDTO> findByNif (int nNif) {
        Optional<NifRestDTO> opNif = nifRestRepository.findOrganizacaoByNif(nNif);

        if ( opNif.isPresent() ) {
            return opNif;
        }
        else
            return Optional.empty();
    }



}
