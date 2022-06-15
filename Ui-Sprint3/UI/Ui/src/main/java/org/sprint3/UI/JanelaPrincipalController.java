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
import org.sprint3.controller.EdicaoController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;

public class JanelaPrincipalController implements Initializable {

    private UtilizadorController utilizadorController;
    private EdicaoController edicaoController;

    private Stage novaJanelaEstudante;

    private Stage novaJanelaDocente;

    private Stage novaJanelaRUC;

    @FXML
    private TextField codUtilizadorField;
    @FXML
    private Button btnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();
        edicaoController = new EdicaoController();

        btnEntrar.setDisable(true);

        // TODO
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) throws IOException {

        UtilizadorRestDTO utilizador = utilizadorController.getUtilizadorById(Integer.parseInt(codUtilizadorField.getText()));
        EdicaoRestDTO edicao = new EdicaoRestDTO(1,1,2,"ATIVA");/*edicaoController.getEdicaoByCodRUC(Integer.parseInt(codUtilizadorField.getText()));*/


        try {
            if (utilizador.getTipoUtilizador().equals("ESTUDANTE")) {

                //String username = codUtilizadorField.getText();
                novaJanelaEstudante = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaEstudante.fxml"));
                Parent root = loader.load();


                String nome = utilizador.getNome()+" "+utilizador.getSobrenome();
                JanelaEstudanteController janelaController = loader.getController();
                janelaController.displayName(nome);
                janelaController.displayObject(utilizador);



                Scene scene = new Scene(root);
                novaJanelaEstudante.initModality(Modality.APPLICATION_MODAL);
                novaJanelaEstudante.setTitle("Área Estudante");
                novaJanelaEstudante.setResizable(false);
                novaJanelaEstudante.setScene(scene);
                novaJanelaEstudante.show();
            }


            if (utilizador.getTipoUtilizador().equals("DOCENTE")) {

                novaJanelaDocente = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaDocente.fxml"));
                Parent root = loader.load();


                /*String nome = utilizador.getNome();
                JanelaDocenteController janelaController = loader.getController();
                janelaController.displayName(nome);
                janelaController.displayObject(utilizador);*/

                Scene scene = new Scene(root);
                novaJanelaDocente.initModality(Modality.APPLICATION_MODAL);
                novaJanelaDocente.setTitle("Área Docente");
                novaJanelaDocente.setResizable(false);
                novaJanelaDocente.setScene(scene);
                novaJanelaDocente.show();
            }


            if (utilizador.getTipoUtilizador().equals("DOCENTE") && edicao.getCodRUC() == utilizador.getCodUtilizador() ) {

                novaJanelaRUC = new Stage();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaRUCInicial.fxml"));
                Parent root = loader.load();


                //String nome = utilizador.getNome();
                JanelaRUCInicialController janelaController = loader.getController();
                //janelaController.displayName(nome);
                janelaController.displayObject(utilizador);

                Scene scene = new Scene(root);
                novaJanelaRUC.initModality(Modality.APPLICATION_MODAL);
                novaJanelaRUC.setTitle("Área de Responsável por Unidade Curricular");
                novaJanelaRUC.setResizable(false);
                novaJanelaRUC.setScene(scene);
                novaJanelaRUC.show();
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
