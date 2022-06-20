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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sprint3.controller.ConviteController;
import org.sprint3.controller.ProjetoController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaDocente implements Initializable {

    UtilizadorController utilizadorController;
    ConviteController conviteController;
    ProjetoController projetoController;
    @FXML
    private ListView listView;
    @FXML
    private Label tituloLabel;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label edicaoLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Button btnOrientador;
    @FXML
    private Button btnConvites;
    @FXML
    private Button btnVisualizar;

    String conviteSeleccionado;
    UtilizadorRestDTO docente;
    private Stage novaJanelaVisualizarConvite;
    @FXML
    private Label labelSeleccione;
    @FXML
    private Label labelTitulo;
    @FXML
    private ListView listViewSubmissoes;
    @FXML
    private ChoiceBox<String> choiceBoxProjetos;
    @FXML
    private Button btnSubmeter;
    private Stage novaJanelaSubmeter;
    private Stage novaJanelaSubmissao;

    String submissaoSeleccionada;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();
        conviteController = new ConviteController();
        projetoController = new ProjetoController();

        btnCancelar.setVisible(false);
        btnVisualizar.setVisible(false);

        labelSeleccione.setVisible(false);
        labelTitulo.setVisible(false);
        choiceBoxProjetos.setVisible(false);
        listViewSubmissoes.setVisible(false);


    }

    public void displayObject(UtilizadorRestDTO utilizador) {
        docente = utilizador;

        String nome = utilizador.getNome() + " " + utilizador.getSobrenome();
        nameLabel.setText(nome);
        nameLabel.setVisible(true);
        initFuncoes();
        int codOrientador = docente.getCodUtilizador();
        //choiceBoxProjetos.getItems().addAll(projetoController.getListaProjetosByCodOrientador(codOrientador));
        choiceBoxProjetos.getItems().addAll(projetoController.getAllProjetos());

        btnVisualizar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    public void initFuncoes(){
        ProjetoRestDTO proj = projetoController.getProjetoByCodOrientador (docente.getCodUtilizador());
        if(proj.getCodOrientador()==docente.getCodUtilizador()){
            btnConvites.setDisable(true);
        }else{
            btnOrientador.setDisable(true);
        }
    }

    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void handleButtonConvitesAction(ActionEvent actionEvent) {
        listView.setVisible(true);

        listView.getItems().clear();

        btnVisualizar.setVisible(true);
        btnCancelar.setVisible(true);
        btnVisualizar.setText("Visualizar");
        btnVisualizar.setOnAction(this::handleButtonVisualizarConvitesAction);

        int codDocente = docente.getCodUtilizador();
        List<String> list = conviteController.getAllConvitesByCodDocente(codDocente);


        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                conviteSeleccionado = (String) listView.getSelectionModel().getSelectedItem();
                System.out.println(conviteSeleccionado);
            }
        });
    }
    public void handleButtonVisualizarConvitesAction(ActionEvent actionEvent) {

        novaJanelaVisualizarConvite = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaVisualizarConvite.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JanelaVisualizarConvite janelaController = loader.getController();
        janelaController.displayName(conviteSeleccionado);

        Scene scene = new Scene(root);
        novaJanelaVisualizarConvite.initModality(Modality.APPLICATION_MODAL);
        novaJanelaVisualizarConvite.setTitle("Convite para Orientação de Projeto");
        novaJanelaVisualizarConvite.setResizable(false);
        novaJanelaVisualizarConvite.setScene(scene);
        novaJanelaVisualizarConvite.show();

    }


    @FXML
    public void handleButtonAreaOrientadorAction(ActionEvent actionEvent) {
        listView.setVisible(false);

        labelSeleccione.setVisible(true);
        choiceBoxProjetos.setVisible(true);
        listViewSubmissoes.setVisible(true);

        choiceBoxProjetos.setOnAction(this::handleCbProjetoAction);

        btnVisualizar.setVisible(true);
        btnVisualizar.setText("Aceitar");
        btnCancelar.setVisible(true);

        //btnVisualizar.setOnAction(this::handleButtonVisualizarSubmissaoAction);

    }

    public void handleCbProjetoAction(ActionEvent actionEvent) {

        String projeto = choiceBoxProjetos.getValue();
        labelTitulo.setText(projeto);
        labelTitulo.setVisible(true);

        btnVisualizar.setDisable(false);
        btnCancelar.setDisable(false);
        btnVisualizar.setText("Visualizar");

        String[] proj = projeto.split("-");
        int codProjeto = Integer.parseInt(proj[0]);

        List<String> list = new ArrayList<>();//projetoController.getAllSubmissoesByCodProjeto (codProjeto);
        list.add("1-Submissao");
        list.add("2-Submissao");
        listViewSubmissoes.getItems().addAll(list);


        listViewSubmissoes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                submissaoSeleccionada = (String) listViewSubmissoes.getSelectionModel().getSelectedItem();
                System.out.println(submissaoSeleccionada);
            }
        });

    }

    @FXML
    public void handleButtonSubmeterAction(ActionEvent actionEvent) throws IOException {

        novaJanelaSubmeter = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaCriarProposta.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        novaJanelaSubmeter.initModality(Modality.APPLICATION_MODAL);
        novaJanelaSubmeter.setTitle("Área Submeter Proposta");
        novaJanelaSubmeter.setResizable(false);
        novaJanelaSubmeter.setScene(scene);
        novaJanelaSubmeter.show();
    }

    @FXML
    public void handleButtonVisualizarAction(ActionEvent actionEvent) throws IOException {

        novaJanelaSubmissao = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JanelaVisualizarSubmissao.fxml"));
        Parent root = loader.load();

        JanelaVisualizarSubmissao janelaController = loader.getController();
        janelaController.displayName(submissaoSeleccionada);

        Scene scene = new Scene(root);
        novaJanelaSubmissao.initModality(Modality.APPLICATION_MODAL);
        novaJanelaSubmissao.setTitle("Área Submissão");
        novaJanelaSubmissao.setResizable(false);
        novaJanelaSubmissao.setScene(scene);
        novaJanelaSubmissao.show();
    }
}
