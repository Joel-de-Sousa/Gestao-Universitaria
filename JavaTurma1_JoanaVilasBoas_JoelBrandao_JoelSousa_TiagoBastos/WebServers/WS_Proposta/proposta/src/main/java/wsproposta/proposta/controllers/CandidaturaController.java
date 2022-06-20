package wsproposta.proposta.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.*;
import wsproposta.proposta.services.CandidaturaService;

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
        List<CandidaturaDTO> listCandidaturasDTO = candidaturaService.findAllCandidaturas();
        return new ResponseEntity<>(listCandidaturasDTO, HttpStatus.OK);
    }

    //MÉTODO GET CANDIDATURAS BY COD CANDIDATURA
    @GetMapping("/{codCandidatura}")
    @ResponseBody
    public ResponseEntity<Object> getCandidaturaByCode(@PathVariable int codCandidatura) {

        Optional<CandidaturaDTO> opCandidaturaDTO = candidaturaService.getCandidaturaByCodCandidatura(codCandidatura);

        if (opCandidaturaDTO.isPresent()) {
            return new ResponseEntity<>(opCandidaturaDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O codigo da candidatura nao consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    //MÉTODO CREATE CANDIDATURA
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createAndSaveCandidatura (@RequestBody NewCandidaturaInfoDTO candidaturaInfoDTO) {

        try {
            CandidaturaDTO candidaturaDTO = candidaturaService.createAndSaveCandidatura (candidaturaInfoDTO);
            return new ResponseEntity<>(candidaturaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //MÉTODO PATCH ALTERA ESTADO CANDIDATURA ESTUDANTE

    @PatchMapping("/estado/{codCandidatura}")
    public ResponseEntity<Object> partialUpdateEstadoCandidaturaEstudante (@RequestBody CandidaturaDTOParcial candidaturaUpdate, @PathVariable int codCandidatura) throws Exception {

        CandidaturaDTO updatedCandidatura = candidaturaService.updateEstadoCandidatura (candidaturaUpdate, codCandidatura);
        return new ResponseEntity<>(updatedCandidatura, HttpStatus.OK);
    }

   /* //MÉTODO PATCH ALTERA ESTADO PEDIDO AO DOCENTE

    @PatchMapping("/orientador/{codCandidatura}")
    public ResponseEntity<Object> partialUpdateEstadoCandidaturaOrientador (@RequestBody CandidaturaDTOParcial candidaturaUpdate, @PathVariable int codCandidatura) {

        CandidaturaDTO updatedCandidatura = candidaturaService.updateEstadoCandidatura (candidaturaUpdate, codCandidatura);
        return new ResponseEntity<>(updatedCandidatura, HttpStatus.OK);
    }*/
}
