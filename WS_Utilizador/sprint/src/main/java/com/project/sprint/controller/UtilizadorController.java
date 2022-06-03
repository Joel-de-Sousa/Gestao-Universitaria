package com.project.sprint.controller;

import com.project.sprint.DTO.ErrorDTO;
import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/utilizadores")
public class UtilizadorController {
    @Autowired
    private UtilizadorService service;

    public UtilizadorController(UtilizadorService service) {
        this.service = service;
    }

    @GetMapping("/{codUtilizador}")
    @ResponseBody
    public ResponseEntity<Object> getUtilizadorByID(@PathVariable int codUtilizador) {
        UtilizadorDTO oUtilizadorDTO = service.getUtilizadorByID(codUtilizador);
        if (oUtilizadorDTO != null) {
            return new ResponseEntity<>(oUtilizadorDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createUtilizador(@RequestBody NewUtilizadorInfoDTO info) {
        try {
            UtilizadorDTO oUtilizadorDto = service.createAndSaveUtilizador(info);

            return new ResponseEntity<>(oUtilizadorDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.CONFLICT);
        }
    }
}
