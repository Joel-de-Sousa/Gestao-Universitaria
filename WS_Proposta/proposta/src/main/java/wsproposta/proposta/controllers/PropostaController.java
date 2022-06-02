package wsproposta.proposta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.*;
import wsproposta.proposta.DTO.assemblers.PropostaDomainDTOAssembler;
import wsproposta.proposta.domain.entities.Proposta;
import wsproposta.proposta.services.PropostaService;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/propostas")
public class PropostaController {

    @Autowired
    private PropostaService service;


    public PropostaController(PropostaService service) {
        this.service = service;
    }

    //MÉTODO FIND ALL PROPOSTAS
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAll() {
        List<PropostaDTO> listPropostasDTO = service.findAll();
        return new ResponseEntity<>(listPropostasDTO, HttpStatus.OK);
    }

    //MÉTODO GET PROPOSTA BY COD PROPOSTA
    @GetMapping("/{codProposta}")
    @ResponseBody
    public ResponseEntity<Object> getPropostaByCode(@PathVariable int codProposta) {

        PropostaDTO propostaDTO = service.getPropostaById(codProposta);

        if (propostaDTO != null) {
            return new ResponseEntity<>(propostaDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O código da proposta não consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO CREATE PROPOSTA
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createProposta(@RequestBody NewPropostaInfoDTO propostaInfoDTO) {

        try {
            PropostaDTO propostaDTO = service.createAndSaveProposta(propostaInfoDTO);
            return new ResponseEntity<>(propostaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR
    @GetMapping("/utilizador/{codUtilizador}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByCodUtilizador(@PathVariable int codUtilizador) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllByCodUtilizador(codUtilizador);
        if (!listFiltradaPropostasDTO.isEmpty()) {
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O Código de Utilizador introduzido não consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF
    @GetMapping("/nif/{nr}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByNifOrganizacao(@PathVariable long nr) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByNifOrganizacao(nr);

        if (!listFiltradaPropostasDTO.isEmpty()) {
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O NIF introduzido não consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS COM ESTE TITULO
    @GetMapping("/titulo/{titulo}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByTitulo(@PathVariable String titulo) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByTitulo(titulo);

        if (!listFiltradaPropostasDTO.isEmpty()) {
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("A String introduzida não consta nos Títulos da Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO PUT ALTERA ESTADO PROPOSTA

    @PatchMapping("/{codProposta}")
    public /*ResponseEntity<?>*/Proposta partialUpdateProposta(
            @RequestBody PropostaDTOParcial propostaUpdate, @PathVariable int codProposta) {

        return service.updateEstadoProposta (propostaUpdate, codProposta);


        /*return service.getPropostaToReplaceEstadoById(codProposta)
                .map(proposta -> {
                    proposta.setCodProposta(propostaUpadate.getCodProposta());
                    proposta.setEstado(Proposta.Estado.valueOf(propostaUpadate.getEstado()));

                    return service.save(proposta);
                })
                .orElseGet(() -> {
                    propostaUpadate.setCodProposta(codProposta);
                    return service.save(propostaDomainDTOAssembler.toDomain(propostaUpadate));
                });*/
       //return ResponseEntity.ok("resource address updated");
    }

  /*  @PutMapping("/{codProposta}")
    Proposta replaceEstadoProposta(@RequestBody NewPropostaDTO newProposta, @PathVariable int codProposta) {

        return service.getPropostaToReplaceEstadoById(codProposta)
                .map(proposta -> {
                    proposta.setCodProposta(newProposta.getCodProposta());
                    proposta.setCodUtilizador(newProposta.getCodUtilizador());
                    proposta.setNifOrganizacao(newProposta.getNifOrganizacao());
                    proposta.setCodEdicao(newProposta.getCodEdicao());
                    proposta.setTitulo(newProposta.getTitulo());
                    proposta.setProblema(newProposta.getProblema());
                    proposta.setObjetivo(newProposta.getObjetivo());
                    proposta.setEstado(Proposta.Estado.valueOf(newProposta.getEstado()));

                    return service.save(proposta);
                })
                .orElseGet(() -> {
                    newProposta.setCodProposta(codProposta);
                    return service.save(propostaDomainDTOAssembler.toDomain(newProposta));
                });
    }*/





    /*@PutMapping("/aceite/{codProposta}")
    public ResponseEntity<Object> updateEstadoProposta (@PathVariable(value = "codProposta") int codProposta,
                                                   @Validated @RequestBody Proposta propostaAlteracao) throws IllegalArgumentException {
        Proposta proposta = service.getPropostaById(codProposta)
               *//* .orElseThrow(() -> new IllegalArgumentException("Proposta not found for this id :: " + employeeId));*//*

        proposta.setEstado(propostaAlteracao.getEstado());

        Proposta updatedProposta = service.save(proposta);
        return ResponseEntity.ok(updatedProposta);
    }*/

}
