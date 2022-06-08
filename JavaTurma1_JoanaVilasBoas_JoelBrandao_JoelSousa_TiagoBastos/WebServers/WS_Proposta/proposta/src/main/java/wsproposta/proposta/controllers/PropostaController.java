package wsproposta.proposta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.*;
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

    /**
     * Método que retorna uma lista com todas as propostas presentes na BD
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD, status OK
     */

    //MÉTODO FIND ALL PROPOSTAS
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAll() {
        List<PropostaDTO> listPropostasDTO = service.findAll();
        return new ResponseEntity<>(listPropostasDTO, HttpStatus.OK);
    }

    /**
     * Método que retorna uma propostaDTO, através da introdução do codProposta como parametro
     * @param codProposta é o identificador da proposta na BD, definido como o código da proposta
     * @return uma propostaDTO
     */

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

    /**
     * Método cria e grava uma proposta com os dados recebidos por parametro, NewPropostaInfoDTO,
     * para tal comunica com o service
     * @param propostaInfoDTO contem os dados para criacao da proposta excepto, codigo e estado da proposta.
     * @return propostaDTO com os dados do objecto criado, incluindo estado, e status created,
     * ou badRequest caso os dados introduzidos nao sejam validos
     */

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

    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo codigo de utilizador, para tal
     * comunica com o service
     * @param codUtilizador é o identificador ddo utilizador na BD, definido como o código de utilizador
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD, e status OK
     */

    //MÉTODO GET PROPOSTAS BY CODE UTILIZADOR - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE UTILIZADOR
    @GetMapping("/utilizador/{codUtilizador}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByCodUtilizador(@PathVariable int codUtilizador) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllByCodUtilizador(codUtilizador);
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);

    }

    /**
     * Método que retorna uma lista com todas as propostas que têm o mesmo nif da Organizacao
     * para tal comunica com o service
     * @param nr é o identificador do NIF Organizacao na BD, definido como o NIF da organizacao
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD, e status OK
     */

    //MÉTODO GET PROPOSTAS BY NIF ORGANIZACAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTE NIF
    @GetMapping("/nif/{nr}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByNifOrganizacao(@PathVariable long nr) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByNifOrganizacao(nr);
            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
       }

    /**
     * Método que retorna uma lista com todas as propostas que contenham a String introduzida
     * no titulo da proposta para tal comunica com o service
     * @param titulo é uma string com caracteres que devem estar contidos no titulo da proposta na BD
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD, e status OK
     */

    //MÉTODO GET PROPOSTAS BY TITULO - RECEBE LISTA DE TODAS AS PROPOSTAS COM ESTE TITULO
    @GetMapping("/titulo/{titulo}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByTitulo(@PathVariable String titulo) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllPropostasByTitulo(titulo);

            return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);
        }


    /**
     * Método que permite fazer o update do estado da proposta
     * @param propostaUpdate é um DTO que recebe apenas o codigo da proposta que se pretende alterar,
     * e String com o valor do estado a ser introduzido
     * @param codProposta é o identificador da proposta na BD, definido como o código da proposta
     * @return uma propostaDTO com todos os parametros, incluindo estado, e exceptuando codProposta
     * e status ok
     */

    //MÉTODO PATCH ALTERA ESTADO PROPOSTA

    @PatchMapping("/{codProposta}")
    public ResponseEntity<Object> partialUpdateEstadoProposta( @RequestBody PropostaDTOParcial propostaUpdate, @PathVariable int codProposta) {

        PropostaDTO updatedProposta = service.updateEstadoProposta (propostaUpdate, codProposta);
        return new ResponseEntity<>(updatedProposta, HttpStatus.OK);

    }

    //MÉTODO GET PROPOSTAS BY CODE EDICAO - RECEBE LISTA DE TODAS AS PROPOSTAS DESTA EDICAO
    @GetMapping("/utilizador/{codEdicao}")
    @ResponseBody
    public ResponseEntity<Object> getAllPropostasByCodEdicao(@PathVariable int codEdicao) {

        List<PropostaDTO> listFiltradaPropostasDTO = service.findAllByCodEdicao(codEdicao);
        return new ResponseEntity<>(listFiltradaPropostasDTO, HttpStatus.OK);

    }
}
