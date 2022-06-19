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
import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class JanelaEstudante implements Initializable {
    UtilizadorController utilizadorController;
    PropostaController propostaController;
    ConviteController conviteController;
    CandidaturaController candidaturaController;
    ProjetoController projetoController;
    EdicaoController edicaoController;
    UtilizadorRestDTO utilizadorIntroduzido;
    JanelaPrincipal janelaPrincipalController;
    @FXML
    private Label nameLabel;
    @FXML
    private Button btnCandidatar;
    @FXML
    private Button btnConvidar;
    @FXML
    private ListView<String> listView;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnConvidar2;
    String docenteSeleccionado;
    String propostaSeleccionada;
    String edicao;
    int codEdicao;
    @FXML
    private Label edicaoLabel;
    @FXML
    private Button btnSubmeter;

    private Stage novaJanelaSubmeter;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edicaoController = new EdicaoController();
        utilizadorController = new UtilizadorController();
        propostaController = new PropostaController();
        candidaturaController = new CandidaturaController();
        conviteController = new ConviteController();
        projetoController = new ProjetoController();
        janelaPrincipalController = new JanelaPrincipal();

        btnConvidar2.setVisible(false);
        btnCancelar.setVisible(false);
    }

    public void associarParentUI(JanelaPrincipal janelaPrincipal) {
        this.janelaPrincipalController = janelaPrincipal;
    }

    public void displayName (String username){
        nameLabel.setText(username);
    }
    public void displayObject (UtilizadorRestDTO utilizador){
        utilizadorIntroduzido = utilizador;
        codEdicao = edicaoController.getCodEdicaoEstudante(utilizador.getCodUtilizador());
        edicao = edicaoController.getEdicaoByCodUtilizador(utilizador.getCodUtilizador());
        edicaoLabel.setText(edicao);

        initFuncoes();
    }

    public void initFuncoes(){
        ProjetoRestDTO proj = projetoController.getProjetoByCodEstudante(utilizadorIntroduzido.getCodUtilizador());
        if(proj.getCodEstudante()==utilizadorIntroduzido.getCodUtilizador()){
            btnCandidatar.setDisable(true);
        }else{
            btnConvidar.setDisable(true);
        }
    }

    //BOTAO APRESENTA LISTA DE PROPOSTAS PARA ESTUDANTE SE CANDIDATAR
    @FXML
    public void handleButtonCandidatarAction(ActionEvent actionEvent) {
        listView.getItems().clear();

        btnConvidar2.setVisible(true);
        btnCancelar.setVisible(true);
        btnConvidar2.setText("Candidatar");
        btnConvidar2.setOnAction(this::handleButtonCandidatarAPropostaAction);


        List<String> list = propostaController.getAllPropostasAceitesByCodEdicao(codEdicao);

        listView.getItems().addAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                propostaSeleccionada = listView.getSelectionModel().getSelectedItem();
                System.out.println(propostaSeleccionada);
            }
        });
    }

    //BOTAO APRESENTA LISTA DOCENTES PARA CONVIDAR ORIENTADOR
    @FXML
    public void handleButtonConvidarAction(ActionEvent actionEvent) {

        listView.getItems().clear();

        btnConvidar2.setVisible(true);
        btnConvidar2.setText("Convidar");
        btnCancelar.setVisible(true);

        List<String> list = utilizadorController.getAllDocentes();
        listView.getItems().setAll(list);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                docenteSeleccionado = listView.getSelectionModel().getSelectedItem();
                System.out.println(docenteSeleccionado);
            }
        });
    }

    //BOTAO CANCELAR ACÇÃO CONVIDAR
    @FXML
    public void handleButtonCancelarAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    //BOTAO CONVIDAR DOCENTE PARA ORIENTAR PROJETO
    @FXML
    public void handleButtonConvidarDocenteAction(ActionEvent actionEvent) {

        try {
            int codEstudante = utilizadorIntroduzido.getCodUtilizador();
            String [] docente = docenteSeleccionado.split("-");
            int codDocente = Integer.parseInt(docente[0]);

            ProjetoRestDTO projeto = projetoController.getProjetoByCodEstudante(codEstudante);
            int codProjeto = projeto.getCodProjeto();

            boolean criou = conviteController.criarNovoConvite(codEstudante, codDocente, codProjeto);

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar um novo Convite.",
                    criou ? "Convite enviado com sucesso."
                            : "Não foi possível enviar convite.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }catch (Exception e){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();

        }

    }

    //BOTAO CANDIDATAR A PROPOSTA

    public void handleButtonCandidatarAPropostaAction(ActionEvent actionEvent){

        try {
            int codEstudante = utilizadorIntroduzido.getCodUtilizador();
            System.out.println(codEstudante);
            String [] proposta = propostaSeleccionada.split("-");
            int codProposta = Integer.parseInt(proposta[0]);
            System.out.println(codProposta);

            boolean criou = candidaturaController.criarNovoCandidatura(codProposta, codEstudante);

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar um nova Candidatura.",
                    criou ? "Candidatura criada com sucesso."
                            : "Não foi possível criar candidatura.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }catch (Exception e){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();

        }
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
}
