package WSEdicao.controllers;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.NewEdicaoInfoDTO;
import WSEdicao.services.EdicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/edicao")
public class EdicaoController {

    @Autowired
    private EdicaoService service;

    public EdicaoController(EdicaoService service){this.service=service;}

    @GetMapping("/{codEdicao}")
    @ResponseBody
    public ResponseEntity<Object> getByCode(@PathVariable int codEdicao){

        Optional<Edicao> opEdicao = service.getEdicaoByCode(codEdicao);

        if(opEdicao.isPresent()){
            Edicao edicao = opEdicao.get();
            return new ResponseEntity<>(edicao, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> findAll(){
        List<Edicao> listEdicao = service.getAllEdicao();

        return new ResponseEntity<>(listEdicao, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createEdicao(@RequestBody NewEdicaoInfoDTO info){

        EdicaoDTO edicao = service.createAndSaveEdicao(info.getCodUc(),info.getCodAnoLetivo());

        return new ResponseEntity<>(edicao, HttpStatus.CREATED);

    }
}
