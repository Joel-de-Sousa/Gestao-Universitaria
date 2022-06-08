package wsproposta.proposta.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.services.CandidaturaService;
import wsproposta.proposta.services.PropostaService;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;


    public CandidaturaController (CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    /**
     * Método que retorna uma lista com todas as propostas presentes na BD
     * @return retorna uma lista de propostasDTO, ou uma lista vazia caso não existam
     * propostas na BD, status OK
     */

    //MÉTODO FIND ALL CANDIDATURAS
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAllCandidaturas() {
        List<CandidaturaDTO> listCandidaturasDTO = candidaturaService.findAll();
        return new ResponseEntity<>(listPropostasDTO, HttpStatus.OK);
    }

    //MÉTODO GET CANDIDATURAS BY COD CANDIDATURA
    @GetMapping("/{codCandidatura}")
    @ResponseBody
    public ResponseEntity<Object> getCandidaturaByCode(@PathVariable int codCandidatura) {

        Optional<PropostaDTO> opPropostaDTO = service.getPropostaById(codProposta);

        if (opPropostaDTO.isPresent()) {
            return new ResponseEntity<>(opPropostaDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O codigo da proposta nao consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

}
