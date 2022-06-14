package org.sprint3.UI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.UtilizadorRestDTO;

public class JanelaPrincipalController implements Initializable {

    private UtilizadorController utilizadorController;
    private Stage novaJanelaEstudante;
    @FXML
    private TextField codUtilizadorField;
    @FXML
    private Button btnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();

        btnEntrar.setDisable(true);

        // TODO
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) throws IOException {

        UtilizadorRestDTO utilizador = utilizadorController.getUtilizadorById(Integer.parseInt(codUtilizadorField.getText()));

        try {
            if (utilizador.getTipoUtilizador().equals("ESTUDANTE")) {

                //String username = codUtilizadorField.getText();
                novaJanelaEstudante = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaEstudante.fxml"));
                Parent root = loader.load();


                String nome = utilizador.getNome();
                JanelaEstudanteController janelaController = loader.getController();
                janelaController.displayName(nome);

                Scene scene = new Scene(root);
                novaJanelaEstudante.initModality(Modality.APPLICATION_MODAL);
                novaJanelaEstudante.setTitle("Área Estudante");
                novaJanelaEstudante.setResizable(false);
                novaJanelaEstudante.setScene(scene);
                novaJanelaEstudante.show();
            }

        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "","O código de Utilizador introduzido não é válido").show();
           codUtilizadorField.clear();
        }
    }

    @FXML
    public void handleKeyReleased(Event event) {
        String text = codUtilizadorField.getText();
        boolean disableButtons = text.isEmpty() || text.trim().isEmpty() || text.length() < 1;
        btnEntrar.setDisable(disableButtons);
    }
}
