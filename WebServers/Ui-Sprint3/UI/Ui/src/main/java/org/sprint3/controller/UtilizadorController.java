package org.sprint3.controller;

import javafx.scene.control.Alert;
import org.sprint3.UI.AlertaUI;
import org.sprint3.UI.MainApp;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.REST.UtilizadorRestRepository;
import org.sprint3.model.service.UtilizadorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UtilizadorController {
    UtilizadorService utilizadorService;
    public UtilizadorController() {
        utilizadorService = new UtilizadorService();
    }


    public UtilizadorRestDTO getUtilizadorById (int codUtilizador){

        UtilizadorRestDTO utilizador = utilizadorService.getUtilizadorById(codUtilizador);

        return utilizador;

    }

    public List<String> getAllDocentes (){
        List<String> listaString = utilizadorService.getAllDocentes();

        return listaString;
    }

}