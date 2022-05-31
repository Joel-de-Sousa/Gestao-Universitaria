package WSEdicao.controllers;

import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.UcDTO;
import WSEdicao.services.UcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = ("/uc"))
public class UcController {

    @Autowired
    private UcService service;

    public UcController(UcService service){this.service=service;}

    @GetMapping("/{codigo}")
    @ResponseBody
    public ResponseEntity<Object> getByCode(@PathVariable int codUc){

        Optional<Uc> opUc = service.getUcByCode(codUc);

        if(opUc.isPresent()){
            Uc uc = opUc.get();
            return new ResponseEntity<>(uc, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /*@GetMapping("")
    @ResponseBody
    public ResponseEntity<Object> findAll(){
        List<Uc> listUc = service.getAllUc();

        return new ResponseEntity<>(listUc, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> createUc(@RequestBody UcDTO info){

        Uc uc = service.createAndSaveUc(info.getCodUc(), info.getSSigla(),info.getSDenominacao());

        return new ResponseEntity<>(uc, HttpStatus.CREATED);

    }*/
}
