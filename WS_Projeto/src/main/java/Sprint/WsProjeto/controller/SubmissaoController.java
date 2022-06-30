package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.NewSubmissaoInfoDTO;
import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.service.SubmissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@Controller
@RestController
@RequestMapping(path = "/submissoes")
public class SubmissaoController {

    @Autowired
    private SubmissaoService submissaoService;

    public SubmissaoController(SubmissaoService submissaoService) {
        this.submissaoService = submissaoService;
    }

    @GetMapping("/{codSubmissao}")
    @ResponseBody
    public ResponseEntity<Object> findSubmissaoByCode(@PathVariable int codSubmissao) throws IOException, SQLException {

        SubmissaoDTO submissao = submissaoService.findSubmissaoBycode(codSubmissao);


        if (submissao!=null) {
            return new ResponseEntity<>(submissao, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


    }


    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createAndSaveSubmissao(@RequestBody NewSubmissaoInfoDTO submissaoInfoDto) {

        try {
            SubmissaoDTO submissaoDTO = submissaoService.createAndSaveSubmissao(submissaoInfoDto);
            return new ResponseEntity<>(submissaoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}