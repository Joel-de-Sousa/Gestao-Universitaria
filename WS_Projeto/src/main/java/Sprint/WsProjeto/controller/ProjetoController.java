package Sprint.WsProjeto.controller;


import Sprint.WsProjeto.DTO.NewProjetoInfoDto;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.sql.Date;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;

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
    public ResponseEntity<Object> findProjetoByCode(@PathVariable int codProjeto) {

        ProjetoDTO oProjeto = projetoService.findProjetoByCode(codProjeto);

        if (oProjeto != null) {
            return new ResponseEntity<>(oProjeto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/estudante/{codEstudante}")
    @ResponseBody
    public ResponseEntity<Object> findProjetoByCodeEstudante(@PathVariable int codEstudante) {

        ProjetoDTO oProjeto = projetoService.findProjetoByCodeEstudante(codEstudante);

        if (oProjeto != null) {
            return new ResponseEntity<>(oProjeto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/filtro/ProjetosByCodRUC/{codRUC}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosByCodRUC (@PathVariable int codRUC){
        try {
            List<ProjetoDTO> lProjeto = projetoService.findProjetosPorCodigoRUC(codRUC);

            return new ResponseEntity<>(lProjeto, HttpStatus.OK);}

        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtro/ProjetosByCodDocente/{codDocente}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosByCodDocente (@PathVariable int codDocente){
        try {
            List<ProjetoDTO> lProjeto = projetoService.findProjetosByCodDocente(codDocente);

            return new ResponseEntity<>(lProjeto, HttpStatus.OK);}

        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/filtro/ProjetosComMACompleto/{codDocente}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosComDeterminadoMACompleto (@PathVariable int codMA){
        try {
            List<ProjetoDTO> lProjeto = projetoService.findProjetosComDeterminadoMACompleto(codMA);

            return new ResponseEntity<>(lProjeto, HttpStatus.OK);}

        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createAndSaveProjeto(@RequestBody NewProjetoInfoDto projetoInfoDto) {

        try {
            ProjetoDTO projetoDTO = projetoService.createAndSaveProjeto(projetoInfoDto);
            return new ResponseEntity<>(projetoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/filtro/projetosConcluidos")
    @ResponseBody
    public ResponseEntity<Object> findProjetosConcluidos() throws SQLException {

        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosConcluidos();

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

    @GetMapping("/filtro/projetosDatas/fetch/{one_date}/{two_date}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosBetweenDatasAvaliacao(@PathVariable(value = "one_date") @DateTimeFormat(pattern = "yyyy-mm-dd") Date fromDate, @PathVariable(value = "two_date") @DateTimeFormat(pattern = "yyyy-mm-dd") Date toDate) throws SQLException {

        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosDatasAvaliacao(fromDate, toDate);

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

    @GetMapping("/filtro/projetosNif/{nifOrganizacao}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosByNifOrganizacao (@PathVariable long nifOrganizacao) throws Exception {


        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosByNifOrganizacao(nifOrganizacao);

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

}
