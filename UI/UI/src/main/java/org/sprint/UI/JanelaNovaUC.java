package org.sprint.UI;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.sprint.controllers.UcController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaNovaUC implements Initializable {
    UcController ucController;
    JanelaInicial janelaPrincipalUI;
    @FXML
    private TextField textFielSigla;
    @FXML
    private TextField textFieldDenominacao;
    @FXML
    private Button idBtconfirm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ucController = new UcController();
        idBtconfirm.setDisable(true);
        textFieldDenominacao.setDisable(true);

    }

    public void associarParentUI(JanelaInicial janelaPrincipalUI) {
        this.janelaPrincipalUI = janelaPrincipalUI;
    }


    @FXML
    public void btnCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void btnConfirmarAction(ActionEvent actionEvent) {
       boolean criou= ucController.criarNovaUC(textFielSigla.getText(), textFieldDenominacao.getText());
       if(criou){
           AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar uma nova Unidade Curricular.",
                   criou ? "Unidade Curricular criada com sucesso."
                           : "Não foi possível criar a Unidade Curricular.").show();
       }
    }


    @FXML
    public void actDenominacao(Event event) {

        if (textFieldDenominacao.getText().length() > 10 && textFielSigla.getText().length() > 3) {
            idBtconfirm.setDisable(false);
        }
    }

    @FXML
    public void actSigla(Event event) {

        if (textFielSigla.getText().length() > 3)
            textFieldDenominacao.setDisable(false);

        if (textFieldDenominacao.getText().length() > 10 && textFielSigla.getText().length() > 3)
            idBtconfirm.setDisable(false);

    }


}
