package org.sprint3.UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.sprint3.controller.PropostaController;
import org.sprint3.controller.UtilizadorController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaEstudanteController implements Initializable {

    private UtilizadorController utilizadorController;

    private PropostaController propostaController;

    JanelaPrincipalController janelaPrincipalUI;
    @FXML
    private Label nameLabel;
    @FXML
    private Button btnCandidatar;
    @FXML
    private Button btnConvidar;
    @FXML
    private ListView<String> listView;

    String docenteSeleccionado;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConvidar2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();
        propostaController = new PropostaController();

        btnConvidar2.setVisible(false);
        btnCancelar.setVisible(false);


    }

    public void associarParentUI(JanelaPrincipalController janelaPrincipalUI) {
        this.janelaPrincipalUI = janelaPrincipalUI;
    }

    public void displayName (String username){
        nameLabel.setText("Olá "+username);

    }

    @FXML
    public void handleButtonCandidatarAction(ActionEvent actionEvent) {
        listView.getItems().clear();
        List<String> list = propostaController.getAllPropostas();
        System.out.println(list);
        listView.getItems().addAll(list);
        /*listView.getItems().setAll(utilizadorController.getUtilizadorById(1).getNome(),
                utilizadorController.getUtilizadorById(2).getNome()+" "+
                        utilizadorController.getUtilizadorById(2).getSobrenome());*/

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                docenteSeleccionado = listView.getSelectionModel().getSelectedItem();
                System.out.println(docenteSeleccionado);
            }
        });

    }

    @FXML
    public void handleButtonConvidarAction(ActionEvent actionEvent) {
        listView.getItems().clear();

        btnConvidar2.setVisible(true);
        btnCancelar.setVisible(true);

        List<String> list = utilizadorController.getAllUtilizadores();
        listView.getItems().setAll(list);
        /*listView.getItems().setAll(utilizadorController.getUtilizadorById(1).getNome(),
                utilizadorController.getUtilizadorById(2).getNome()+" "+
                utilizadorController.getUtilizadorById(2).getSobrenome());*/

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                docenteSeleccionado = listView.getSelectionModel().getSelectedItem();
                System.out.println(docenteSeleccionado);
            }
        });
    }

    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();


    }

    @FXML
    public void handleButtonConvidarDocenteAction(ActionEvent actionEvent) {


    }
}
