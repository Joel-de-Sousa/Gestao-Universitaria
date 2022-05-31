package wsproposta.proposta.controllers;


import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wsproposta.proposta.DTO.ErrorDTO;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.DTO.PropostaDTO;
import wsproposta.proposta.services.PropostaService;

import java.util.List;

@Controller
@RestController
@RequestMapping (path = "/propostas")
public class PropostaController {

    @Autowired
    private PropostaService service;

    public PropostaController (PropostaService service){
        this.service = service;
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> getAll(){
        List<PropostaDTO> listPropostasDTO = service.findAll();
        return new ResponseEntity<>(listPropostasDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> getById (@PathVariable int codProposta) {

        PropostaDTO alojamentoDTO = service.getPropostaById (codProposta);

        if(alojamentoDTO != null) {
            return new ResponseEntity<>(alojamentoDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<Object> createProposta (@RequestBody NewPropostaInfoDTO propostaInfoDTO) {
        try{
            PropostaDTO propostaDTO = service.createAndSaveProposta (propostaInfoDTO);

            return new ResponseEntity<>(propostaDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.CONFLICT);

        }
    }

}
