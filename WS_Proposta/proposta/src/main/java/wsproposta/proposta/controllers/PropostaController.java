package wsproposta.proposta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.*;
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

        Optional<PropostaDTO> opPropostaDTO = service.getPropostaById(codProposta);

        if (opPropostaDTO.isPresent()) {
            return new ResponseEntity<>(opPropostaDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O codigo da proposta nao consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO CREATE PROPOSTA
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createAndSaveProposta(@RequestBody NewPropostaInfoDTO propostaInfoDTO) {

        try {
            PropostaDTO propostaDTO = service.createAndSaveProposta(propostaInfoDTO);
            return new ResponseEntity<>(propostaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR
    @GetMapping("/utilizador/{codUtilizador}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByCodUtilizador(@PathVariable int codUtilizador) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllByCodUtilizador(codUtilizador);
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);

    }

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF
    @GetMapping("/nif/{nr}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByNifOrganizacao(@PathVariable long nr) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByNifOrganizacao(nr);
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
       }

    /**
     *
     * @param titulo
     * @return
     */
    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS COM ESTE TITULO
    @GetMapping("/titulo/{titulo}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByTitulo(@PathVariable String titulo) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByTitulo(titulo);

            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
        }

    //MÉTODO PATCH ALTERA ESTADO PROPOSTA

    @PatchMapping("/{codProposta}")
    public ResponseEntity<Object> partialUpdateEstadoProposta( @RequestBody PropostaDTOParcial propostaUpdate, @PathVariable int codProposta) {

        PropostaDTO updatedProposta = service.updateEstadoProposta (propostaUpdate, codProposta);
        return new ResponseEntity<>(updatedProposta, HttpStatus.OK);

    }
}
