package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.sprint3.controller.EdicaoController;
import org.sprint3.controller.PropostaController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaCriarProposta implements Initializable {

    PropostaController propostaController;
    EdicaoController edicaoController;
    JanelaPrincipal janelaPrincipalController;
    @FXML
    private Button btnConfirmProposta;
    @FXML
    private TextField txtProblema;
    @FXML
    private TextField txtNif;
    @FXML
    private Button btnCancelProposta;
    @FXML
    private TextField txtObjetivo;
    @FXML
    private TextField txtcodUtilizador;
    @FXML
    private ComboBox cmbxEdicao;
    @FXML
    private TextField txtTitulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        propostaController = new PropostaController();
        edicaoController = new EdicaoController();
        //cmbxEdicao.getItems().addAll(edicaoController.getListaEdicoes());
        reniciarDisable();
    }

    @FXML
    public void actBtnConfirmar(ActionEvent actionEvent) {

        try {
            int edicao = cmbxEdicao.getSelectionModel().getSelectedIndex() + 1;
            int codUtil = Integer.parseInt(txtcodUtilizador.getText());
            int nif = Integer.parseInt(txtNif.getText());
            String titulo = txtTitulo.getText();
            String objetivo = txtObjetivo.getText();
            String problema = txtProblema.getText();
            boolean criou = propostaController.criarNovaProposta(codUtil, nif, edicao, titulo, problema, objetivo);

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar uma nova Proposta.",
                    criou ? "Proposta criada com sucesso."
                            : "Não foi possível criar a Proposta.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
            reniciarDisable();
        }
    }

    @FXML
    public void actTitulo(Event event) {
        if (minCaracteres(txtTitulo.getText(), 10)) {
            txtObjetivo.setDisable(false);
        }
    }

    @FXML
    public void actProblema(Event event) {
        if (minCaracteres(txtProblema.getText(), 10)) {
            cmbxEdicao.setDisable(false);
        }
    }

    @FXML
    public void actNif(Event event) {
        if (minCaracteres(txtNif.getText(), 9)) {
            txtTitulo.setDisable(false);
        }
    }

    @FXML
    public void actUtilizador(Event event) {
        txtNif.setDisable(false);
    }

    @javafx.fxml.FXML
    public void actCmbEdicao(ActionEvent actionEvent) {
        btnConfirmProposta.setDisable(false);
    }

    @javafx.fxml.FXML
    public void actObjetivo(Event event) {
        if (minCaracteres(txtObjetivo.getText(), 10)) {
            txtProblema.setDisable(false);
        }
    }

    @javafx.fxml.FXML
    public void actBtnCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }

    private void reniciarDisable() {
        txtObjetivo.setDisable(true);
        txtObjetivo.clear();
        cmbxEdicao.setDisable(true);
        txtTitulo.clear();
        txtTitulo.setDisable(true);
        txtNif.clear();
        txtNif.setDisable(true);
        txtProblema.clear();
        txtProblema.setDisable(true);
        txtcodUtilizador.clear();
        btnConfirmProposta.setDisable(true);
    }

    private boolean minCaracteres(String texto, int min) {
        if (texto.trim().length() >= min) {
            return true;
        }
        return false;
    }
}
