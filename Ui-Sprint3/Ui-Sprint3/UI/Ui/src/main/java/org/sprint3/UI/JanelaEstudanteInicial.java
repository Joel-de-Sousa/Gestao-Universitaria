package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sprint3.controller.EdicaoController;
import org.sprint3.model.DTO.UtilizadorRestDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class JanelaEstudanteInicial implements Initializable {

    EdicaoController edicaoController;

    private Stage novaJanelaEstudante;

    @FXML
    private ComboBox<String> cmbEdicao;
    @FXML
    private Button btnEntrar;

    UtilizadorRestDTO utilizadorIntroduzido;
    @FXML
    private Button btnCancelar;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edicaoController = new EdicaoController();

        btnEntrar.setDisable(true);

        // TODO
    }
    public void displayObject (UtilizadorRestDTO utilizador){
        utilizadorIntroduzido = utilizador;

        int codEstudante = utilizadorIntroduzido.getCodUtilizador();
        cmbEdicao.getItems().addAll(edicaoController.getEdicaoByCodEstudante(codEstudante));
    }

    @FXML
    public void cmbAction(ActionEvent actionEvent) {

        btnEntrar.setDisable(false);
    }

    @FXML
    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        String edicao = cmbEdicao.getValue();

        novaJanelaEstudante = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaEstudante.fxml"));
        Parent root = loader.load();

        String nome = utilizadorIntroduzido.getNome()+" "+utilizadorIntroduzido.getSobrenome();
        JanelaEstudante janelaController = loader.getController();
        janelaController.displayName(nome);
        janelaController.displayObject(utilizadorIntroduzido);


        Scene scene = new Scene(root);
        novaJanelaEstudante.initModality(Modality.APPLICATION_MODAL);
        novaJanelaEstudante.setTitle("Área Estudante");
        novaJanelaEstudante.setResizable(false);
        novaJanelaEstudante.setScene(scene);
        novaJanelaEstudante.show();


    }

    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }
}
