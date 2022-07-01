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
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.sprint3.controller.ConviteController;
import org.sprint3.controller.ProjetoController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.ConviteRestDTO;
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

    JanelaPrincipal janelaPrincipalController;
    ConviteRestDTO conviteCompleto;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        utilizadorController = new UtilizadorController();
        conviteController = new ConviteController();
        projetoController = new ProjetoController();
        docente = new UtilizadorRestDTO();
        janelaPrincipalController = new JanelaPrincipal();

        btnCancelar.setVisible(false);
        btnVisualizar.setVisible(false);

        labelSeleccione.setVisible(false);
        labelTitulo.setVisible(false);
        choiceBoxProjetos.setVisible(false);
        listViewSubmissoes.setVisible(false);


    }

    public void associarParentUI(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipalController = janelaPrincipal;
    }

    public void displayObject(UtilizadorRestDTO utilizador) {
        docente = utilizador;

        String nome = utilizador.getNome() + " " + utilizador.getSobrenome();
        nameLabel.setText(nome);
        nameLabel.setVisible(true);
        initFuncoes();
        int codOrientador = utilizador.getCodUtilizador();
        //choiceBoxProjetos.getItems().addAll(projetoController.getListaProjetosByCodOrientador(codOrientador));
        //choiceBoxProjetos.getItems().addAll(projetoController.getAllProjetos());

        btnVisualizar.setDisable(true);
        btnCancelar.setDisable(true);
    }

    public void initFuncoes(){
        ProjetoRestDTO proj = projetoController.getProjetoByCodOrientador (docente.getCodUtilizador());
        int codDoc = 0;
        if (proj!=null){
            codDoc = proj.getCodOrientador();
        }
        if(codDoc==docente.getCodUtilizador()){
            btnConvites.setDisable(true);
        }else{
            btnOrientador.setDisable(false);
        }
    }

    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void handleButtonConvitesAction(ActionEvent actionEvent) {
        try{
        listView.setVisible(true);

        listView.getItems().clear();

        btnVisualizar.setVisible(true);
        btnCancelar.setVisible(true);

        btnVisualizar.setDisable(false);
        btnCancelar.setDisable(false);
        btnVisualizar.setText("Aceitar");
        btnVisualizar.setOnAction(this::handleButtonVisualizarConvitesAction);

        int codDocente = docente.getCodUtilizador();

        String list = conviteController.getAllConvitesByCodDocente(codDocente);

        List<String> list1 = new ArrayList<>();
        list1.add(list);

        listView.getItems().addAll(list1);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                conviteSeleccionado = (String) listView.getSelectionModel().getSelectedItem();
                System.out.println(conviteSeleccionado);
            }
        });}catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "","Não existem convites").show();
        }
    }
    public void handleButtonVisualizarConvitesAction(ActionEvent actionEvent) {

        try {

            String[] convite1 = conviteSeleccionado.split("-");
            int codConvite = Integer.parseInt(convite1[0]);
            conviteCompleto = conviteController.getConviteByCodConvite(codConvite);
            int codConv = conviteCompleto.getCodConvite();
            String estado = "ACEITE";
            boolean alterou = conviteController.alterarEstadoConvite(codConv, estado);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Aceitar Convite",
                        alterou ? "Convite aceite com sucesso."
                                : "Não foi possível alterar estado do convite para Aceite").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Seleccione Convite.",
                    e.getMessage()).show();
        }

    }


    @FXML
    public void handleButtonAreaOrientadorAction(ActionEvent actionEvent) {
        try {
            listView.setVisible(false);

            labelSeleccione.setVisible(true);
            choiceBoxProjetos.setVisible(true);
            listViewSubmissoes.setVisible(true);

            choiceBoxProjetos.setOnAction(this::handleCbProjetoAction);

            btnVisualizar.setVisible(true);
            btnVisualizar.setText("Aceitar");
            btnCancelar.setVisible(true);

            //btnVisualizar.setOnAction(this::handleButtonVisualizarSubmissaoAction);
        }catch (Exception e) {
        AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "","Não existem convites").show();

    }}

    public void handleCbProjetoAction(ActionEvent actionEvent) {

        try{
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
        });}catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "","Não existem projectos").show();

        }}

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
