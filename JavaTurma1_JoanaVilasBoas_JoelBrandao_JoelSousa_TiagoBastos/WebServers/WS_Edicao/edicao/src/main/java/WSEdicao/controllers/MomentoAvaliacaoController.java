package WSEdicao.controllers;

import WSEdicao.dto.MomentoAvaliacaoDTO;

import WSEdicao.services.EdicaoService;
import WSEdicao.services.MomentoAvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/momentoavaliacao")
public class MomentoAvaliacaoController {

    @Autowired
    private MomentoAvaliacaoService service;

    public MomentoAvaliacaoController(MomentoAvaliacaoService service) {
        this.service = service;
    }

    @GetMapping("/{codMomentoAvaliacao}")
    @ResponseBody
    public ResponseEntity<Object> getByCode(@PathVariable int codMomentoAvaliacao) {

        Optional<MomentoAvaliacaoDTO> opMomentoAvaliacao = service.getMomentoAvaliacaoByCode(codMomentoAvaliacao);

        if (opMomentoAvaliacao.isPresent()) {
            MomentoAvaliacaoDTO momentoAvaliacao = opMomentoAvaliacao.get();
            return new ResponseEntity<>(momentoAvaliacao, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> findAll() {

        List<MomentoAvaliacaoDTO> listMomentoAvaliacao = service.getAllMomentoAvaliacao();

        return new ResponseEntity<>(listMomentoAvaliacao, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createMomentoAvaliacao(@RequestBody MomentoAvaliacaoDTO info) {
        try {
            MomentoAvaliacaoDTO momentoAvaliacao = service.createAndSaveMomentoAvaliacao(info);

            return new ResponseEntity<>(momentoAvaliacao, HttpStatus.CREATED);

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
