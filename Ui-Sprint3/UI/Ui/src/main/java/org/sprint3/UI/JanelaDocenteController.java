package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.sprint3.controller.PropostaController;
import org.sprint3.controller.UtilizadorController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaDocenteController implements Initializable {

    UtilizadorController utilizadorController;
    @javafx.fxml.FXML
    private Button btnAceitar;
    @javafx.fxml.FXML
    private Button btnRejeitar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();


    }


    @javafx.fxml.FXML
    public void handleButtonActionAceitar(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleButtonActionRejeitar(ActionEvent actionEvent) {
    }
}
