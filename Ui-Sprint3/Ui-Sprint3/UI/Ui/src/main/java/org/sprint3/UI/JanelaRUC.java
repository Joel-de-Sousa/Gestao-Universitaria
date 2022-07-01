package org.sprint3.UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sprint3.controller.*;
import org.sprint3.model.DTO.UtilizadorRestDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaRUC implements Initializable {

    JanelaRUCInicial janelaRUCInicialController;
    private PropostaController propostaController;
    private CandidaturaController candidaturaController;

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
    @FXML
    private Button btnAreaDocente;
    String propostaSeleccionada;
    String candidaturaSeleccionada;

    private Stage novaJanelaVisualizarProposta;
    private Stage novaJanelaVisualizarCandidatura;
    private Stage novaJanelaJuri;
    private Stage novaJanelaDocente;
    UtilizadorRestDTO utilizadorRUC;





    @Override
    public void initialize(URL url, ResourceBundle rb) {
        janelaRUCInicialController = new JanelaRUCInicial();
        propostaController = new PropostaController();
        candidaturaController = new CandidaturaController();

        btnConvidar2.setVisible(false);
        btnCancelar.setVisible(false);
    }

    public void associarParentUI(JanelaRUCInicial janelaInicial2) {
        this.janelaRUCInicialController = janelaInicial2;
    }

    public void displayName(String edicao1) {
        edicaoLabel.setText(edicao1);
        edicaoLabel.setVisible(true);
    }

    public void displayObject(UtilizadorRestDTO utilizador) {
        utilizadorRUC = utilizador;
        String nome = utilizador.getNome() + " " + utilizador.getSobrenome();
        nameLabel.setText(nome);
        nameLabel.setVisible(true);
    }


    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void handleButtonCandidaturasAction(ActionEvent actionEvent) {
        listView.getItems().clear();

        btnConvidar2.setVisible(true);
        btnCancelar.setVisible(true);
        btnConvidar2.setText("Visualizar");
        btnConvidar2.setOnAction(this::handleButtonVisualizarCandidaturasAction);

        List<String> list = candidaturaController.getAllCandidaturas();
        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                candidaturaSeleccionada = (String) listView.getSelectionModel().getSelectedItem();
                System.out.println(candidaturaSeleccionada);
            }
        });
}
    public void handleButtonVisualizarCandidaturasAction(ActionEvent actionEvent) {

        novaJanelaVisualizarCandidatura = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaVisualizarCandidatura.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JanelaVisualizarCandidatura janelaController = loader.getController();
        janelaController.displayName(candidaturaSeleccionada);

        Scene scene = new Scene(root);
        novaJanelaVisualizarCandidatura.initModality(Modality.APPLICATION_MODAL);
        novaJanelaVisualizarCandidatura.setTitle("Candidatura Submetida");
        novaJanelaVisualizarCandidatura.setResizable(false);
        novaJanelaVisualizarCandidatura.setScene(scene);
        novaJanelaVisualizarCandidatura.show();

    }

    @FXML
    public void handleButtonJuriAction(ActionEvent actionEvent) throws IOException {
        listView.getItems().clear();

        novaJanelaJuri = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaJuri.fxml"));
        Parent root = loader.load();

        JanelaJuri janelaController = loader.getController();
        janelaController.displayName(edicao);


        Scene scene = new Scene(root);
        novaJanelaJuri.initModality(Modality.APPLICATION_MODAL);
        novaJanelaJuri.setTitle("Área Definir Júri");
        novaJanelaJuri.setResizable(false);
        novaJanelaJuri.setScene(scene);
        novaJanelaJuri.show();
    }

    @FXML
    public void handleButtonPropostasAction(ActionEvent actionEvent) {
        try{
        listView.getItems().clear();

        btnConvidar2.setVisible(true);
        btnCancelar.setVisible(true);
        btnConvidar2.setText("Visualizar");
        btnConvidar2.setOnAction(this::handleButtonVisualizarPropostaAction);

        List<String> list = propostaController.getAllPropostasPendentes();
        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                propostaSeleccionada = (String) listView.getSelectionModel().getSelectedItem();
                System.out.println(propostaSeleccionada);
            }
        });
        }catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "","Não existem propostas").show();

        }}

    public void handleButtonVisualizarPropostaAction(ActionEvent actionEvent) {

        novaJanelaVisualizarProposta = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaVisualizarProposta.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JanelaVisualizarProposta janelaController = loader.getController();
        janelaController.displayName(propostaSeleccionada);

        Scene scene = new Scene(root);
        novaJanelaVisualizarProposta.initModality(Modality.APPLICATION_MODAL);
        novaJanelaVisualizarProposta.setTitle("Proposta Submetida");
        novaJanelaVisualizarProposta.setResizable(false);
        novaJanelaVisualizarProposta.setScene(scene);
        novaJanelaVisualizarProposta.show();

    }

    @FXML
    public void handleButtonAreaDocenteAction(ActionEvent actionEvent) throws IOException {

        novaJanelaDocente = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaDocente.fxml"));
        Parent root = loader.load();

        JanelaDocente janelaController = loader.getController();
        janelaController.displayObject(utilizadorRUC);

        Scene scene = new Scene(root);
        novaJanelaDocente.initModality(Modality.APPLICATION_MODAL);
        novaJanelaDocente.setTitle("Área Docente");
        novaJanelaDocente.setResizable(false);
        novaJanelaDocente.setScene(scene);
        novaJanelaDocente.show();
    }


    @FXML
    public void handleButtonConvidarDocenteAction(ActionEvent actionEvent) throws IOException {

    }
}
