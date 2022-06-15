package org.sprint3.UI;

<<<<<<< HEAD
public class JanelaRUCController {
=======
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.sprint3.controller.*;
import org.sprint3.model.DTO.UtilizadorRestDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaRUCController implements Initializable {

    JanelaRUCInicialController janelaRUCInicialController;

    @FXML
    private ListView listView;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConvidar2;
    @FXML
    private Label nameLabel;
    @FXML
    private Label tituloLabel;
    @FXML
    private Label edicaoLabel;
    String edicao;
    @FXML
    private Button btnJuri;
    @FXML
    private Button btnCandidaturas;
    @FXML
    private Button btnPropostas;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        janelaRUCInicialController = new JanelaRUCInicialController();

        /*edicaoLabel.setText("POO-2022");
        edicaoLabel.setVisible(true);*/

        btnConvidar2.setVisible(false);
        btnCancelar.setVisible(false);


    }

    public void associarParentUI(JanelaRUCInicialController janelaInicial2) {
        this.janelaRUCInicialController = janelaInicial2;
    }
    public void displayName (String edicao1){
        edicaoLabel.setText(edicao1);
        edicaoLabel.setVisible(true);
    }

    public void displayObject (UtilizadorRestDTO utilizador){
        String nome = utilizador.getNome()+" "+utilizador.getSobrenome();
        nameLabel.setText(nome);
        nameLabel.setVisible(true);
    }

    @Deprecated
    public void handleButtonCandidatarAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleButtonConvidarDocenteAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void handleButtonConvidarAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }

    @FXML
    public void handleButtonCandidaturasAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleButtonJuriAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleButtonPropostasAction(ActionEvent actionEvent) {
    }
>>>>>>> 5331ab3afb599fd712540df7ec951ab27fb815e5
}
