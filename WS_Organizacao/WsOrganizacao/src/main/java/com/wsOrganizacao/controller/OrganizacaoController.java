package com.wsOrganizacao.controller;

import com.wsOrganizacao.DTO.OrganizacaoDTO;
import com.wsOrganizacao.services.OrganizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/organizacoes")
public class OrganizacaoController {

    @Autowired
    private final OrganizacaoService organizacaoService;

    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }

    @GetMapping("/{nif}")
    @ResponseBody
    public ResponseEntity<Object> getByNif(@PathVariable int nNif) {

        OrganizacaoDTO opOrganizacao = organizacaoService.getOrganizacaoByNif(nNif);

        if (opOrganizacao != null) {
            return new ResponseEntity<>(opOrganizacao, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    // check!
    ResponseEntity<Object> createOrganizacao(@RequestBody OrganizacaoDTO organizacaoDTO){
        OrganizacaoDTO organizacaoDTO1 = organizacaoService.createAndSaveOrganizacao(organizacaoDTO);
        return new ResponseEntity<>(organizacaoDTO1,HttpStatus.CREATED);
    }

}
