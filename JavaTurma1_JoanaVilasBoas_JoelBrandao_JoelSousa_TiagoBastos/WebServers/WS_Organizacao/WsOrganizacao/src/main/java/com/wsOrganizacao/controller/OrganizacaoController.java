package com.wsOrganizacao.controller;

import com.wsOrganizacao.DTO.OrganizacaoDTO;
import com.wsOrganizacao.datamodel.REST.NifRestDTO;
import com.wsOrganizacao.datamodel.REST.OrganizacaoRestDTO;
import com.wsOrganizacao.datamodel.REST.assembler.OrganizacaoDomainDataRestAssembler;
import com.wsOrganizacao.services.OrganizacaoService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/organizacoes")
public class OrganizacaoController {

    @Autowired
    private final OrganizacaoService organizacaoService;
    OrganizacaoDomainDataRestAssembler organizacaoDomainDataRestAssembler;
    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }

    @GetMapping("/{nif}")
    @ResponseBody
    public ResponseEntity<Object> findOrganizacaoByNif(@PathVariable int nif) {

       /* OrganizacaoDTO opOrganizacao = organizacaoService.getOrganizacaoByNif(nNif);*/
        Optional<NifRestDTO> oNifRest=organizacaoService.findOrganizacaoByNif(nif);

        if (!oNifRest.isEmpty()) {
            NifRestDTO nifrest=new NifRestDTO(oNifRest.get().getNr(),oNifRest.get().getName()) ;
            return new ResponseEntity<>(nifrest, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    // check!
    ResponseEntity<Object> createOrganizacao(@RequestBody OrganizacaoDTO organizacaoDTO){
        OrganizacaoDTO organizacaoDTO1 = organizacaoService.createAndSaveOrganizacao(organizacaoDTO);
        return new ResponseEntity<>(organizacaoDTO1,HttpStatus.CREATED);
    }

}
