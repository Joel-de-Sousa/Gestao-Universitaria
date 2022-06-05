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

    /**
     * Método para retornar um Utilizador quando pesquisado pelo seu ID (codUtilizador)
     *
     * @param codUtilizador
     *
     * @return retorna um 200 e um DTO com os seus dados caso seja encontrado com sucesso
     * e um 404 NOT FOUND caso esse codUtilizador nao se encontre na BD.
     */
    @GetMapping("/{codUtilizador}")
    @ResponseBody
    public ResponseEntity<Object> getUtilizadorByID(@PathVariable int codUtilizador) {
        UtilizadorDTO oUtilizadorDTO = service.getUtilizadorByID(codUtilizador);
        if (oUtilizadorDTO != null) {
            return new ResponseEntity<>(oUtilizadorDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Método de criação de uma Utilizador, através da introdução de dados.
     *
     * @param info DTO de entrada para a criação de um Utilizador.
     *
     * @return um 201 CREATED e um DTO com todos os dados caso o Utilizador seja gravado com sucesso
     * ou um 409 CONFLICT caso tal não se verifique.
     */
    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Object> createUtilizador(@RequestBody NewUtilizadorInfoDTO info) {
        try {
            UtilizadorDTO oUtilizadorDto = service.createAndSaveUtilizador(info);

            return new ResponseEntity<>(oUtilizadorDto, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
