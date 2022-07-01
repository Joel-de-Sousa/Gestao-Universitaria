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
    public ResponseEntity<Object> findProjetoByCode(@PathVariable int codProjeto) throws SQLException {

        ProjetoDTO oProjeto = projetoService.findProjetoByCode(codProjeto);

        if (oProjeto != null) {
            return new ResponseEntity<>(oProjeto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/estudante/{codEstudante}")
    @ResponseBody
    public ResponseEntity<Object> findProjetoByCodeEstudante(@PathVariable int codEstudante) throws SQLException {

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

    @GetMapping("/{codRUC}/filtro/ProjetosByCodDocente/{codDocente}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosByCodDocente (@PathVariable int codRUC ,@PathVariable int codDocente){
        try {
            List<ProjetoDTO> lProjeto = projetoService.findProjetosByCodDocente(codRUC,codDocente);

            return new ResponseEntity<>(lProjeto, HttpStatus.OK);}

        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{codRUC}/filtro/ProjetosComMACompleto/{codMA}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosComDeterminadoMACompleto (@PathVariable int codRUC ,@PathVariable int codMA){
        try {
            List<ProjetoDTO> lProjeto = projetoService.findProjetosComDeterminadoMACompleto(codRUC,codMA);

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

    @GetMapping("/{codRUC}/filtro/projetosConcluidos")
    @ResponseBody
    public ResponseEntity<Object> findProjetosConcluidos(@PathVariable int codRUC ) throws Exception {

        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosConcluidos(codRUC);

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

    @GetMapping("/{codRUC}/filtro/projetosDatas/fetch/{codMA}/{fromDate}/{toDate}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosBetweenDatasAvaliacao(@PathVariable int codRUC ,@PathVariable int codMA, @PathVariable Date fromDate, @PathVariable Date toDate) throws Exception {

        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosDatasAvaliacao(codRUC, codMA,fromDate, toDate);

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

    @GetMapping("/{codRUC}/filtro/projetosNif/{nifOrganizacao}")
    @ResponseBody
    public ResponseEntity<Object> findProjetosByNifOrganizacao (@PathVariable int codRUC, @PathVariable long nifOrganizacao) throws Exception {


        List<ProjetoDTO> listProjetoDTO = projetoService.findProjetosByNifOrganizacao(codRUC, nifOrganizacao);

        return new ResponseEntity<>(listProjetoDTO, HttpStatus.OK);

    }

}
