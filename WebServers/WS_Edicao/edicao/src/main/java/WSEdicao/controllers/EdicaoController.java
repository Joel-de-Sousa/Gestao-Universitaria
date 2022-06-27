package WSEdicao.controllers;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.*;
import WSEdicao.services.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/edicao")
public class EdicaoController {

    @Autowired
    private EdicaoService service;

    public EdicaoController(EdicaoService service) {
        this.service = service;
    }

    @GetMapping("/{codEdicao}")
    @ResponseBody
    public ResponseEntity<Object> getByCode(@PathVariable int codEdicao) {

        Optional<EdicaoDTO> opEdicao = service.getEdicaoByCode(codEdicao);

        if (opEdicao.isPresent()) {
            EdicaoDTO edicao = opEdicao.get();
            return new ResponseEntity<>(edicao, HttpStatus.OK);
        } else
            return new ResponseEntity<>("O codigo da Edição não consta na Base de Dados", HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> findAll() {
        List<EdicaoDTO> listEdicao = service.getAllEdicao();

        return new ResponseEntity<>(listEdicao, HttpStatus.OK);
    }

    @GetMapping("/allargs")
    @ResponseBody
    public ResponseEntity<Object> findAllArgs() {
        List<EdicaoAllArgsDTO> listEdicao = service.getEdicaoAllArgs();

        return new ResponseEntity<>(listEdicao, HttpStatus.OK);
    }

    @GetMapping("/allargs/{codEdicao}")
    @ResponseBody
    public ResponseEntity<Object> getAllArgsByCode(@PathVariable int codEdicao) {

        Optional<EdicaoAllArgsDTO> opEdicao = service.getEdicaoAllArgsByCode(codEdicao);

        if (opEdicao.isPresent()) {
            EdicaoAllArgsDTO edicao = opEdicao.get();
            return new ResponseEntity<>(edicao, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allEstudantes/{codEdicao}")
    @ResponseBody
    public ResponseEntity<Object> getAllEstudantesByCodEdicao(@PathVariable int codEdicao) {

        List<AddStudentDTO> listEstudantesByCodEdicao = service.getAllEstudantesByCodEdicao(codEdicao);
        return new ResponseEntity<>(listEstudantesByCodEdicao, HttpStatus.OK);

    }

    @PostMapping("")
    //@ResponseBody
    public ResponseEntity<Object> createEdicao(@RequestBody NewEdicaoInfoDTO info) {
        try {
            EdicaoDTO edicao = service.createAndSaveEdicao(info.getCodUc(), info.getCodAnoLetivo(), info.getCodRUC());

            return new ResponseEntity<>(edicao, HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //MÉTODO PATCH ALTERA ESTADO EDICAO
    @PatchMapping("/{codEdicao}")
    public ResponseEntity<Object> partialUpdateEstadoProposta(@RequestBody EdicaoDTOParcial edicaoUpdate) throws Exception {
        try {
            EdicaoDTO updatedProposta = service.updateEstadoEdicao(edicaoUpdate);
            return new ResponseEntity<>(updatedProposta, HttpStatus.OK);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/addEstudante/{codEdicao}")
    public ResponseEntity<Object> addEstudante(@RequestBody AddStudentDTO addStudent) throws Exception {
        try {
            EdicaoDTO addEstudante = service.addEstudantes(addStudent);

            return new ResponseEntity<>(addEstudante, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/addMA/{codEdicao}")
    public ResponseEntity<Object> addMA(@RequestBody MomentoAvaliacaoDTO momentoAvaliacaoDTO, @PathVariable int codEdicao) throws Exception {
        try {
            EdicaoDTO addMA = service.addMA(momentoAvaliacaoDTO,codEdicao);

            return new ResponseEntity<>(addMA, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/listEdicaoByCodRUC/{codRUC}")
    @ResponseBody
    public ResponseEntity<Object> getAllEdicaoByCodRUC(@PathVariable int codRUC) {

        List<EdicaoAllArgsDTO> listEdicao = service.getAllEdicaoByCodRUC(codRUC);

        return new ResponseEntity<>(listEdicao, HttpStatus.OK);
    }

    @GetMapping("/estudante/{codEstudante}")
    @ResponseBody
    public ResponseEntity<Object> getEdicaoByCodEstudante(@PathVariable int codEstudante) throws Exception {
        try {
            EdicaoAllArgsDTO listEdicao = service.getEdicaoByCodEstudante(codEstudante);

            return new ResponseEntity<>(listEdicao, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/ruc/{codRUC}")
    @ResponseBody
    public ResponseEntity<Object> getEdicaoByCodRUC(@PathVariable int codRUC) throws Exception {
        try {
            EdicaoAllArgsDTO edicao = service.getEdicaoByCodRUC(codRUC);

            return new ResponseEntity<>(edicao, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
