package Sprint.WsProjeto.controller;


import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

   @GetMapping("/{codProjeto}")
    @ResponseBody
    public ResponseEntity<Object> findProjetoByCode(@PathVariable int codProjeto){

       ProjetoDTO oProjeto = projetoService.findProjetoByCode(codProjeto);


       if (oProjeto != null) {
           return new ResponseEntity<>(oProjeto, HttpStatus.OK);
       } else
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


    }
}
