package org.sprint.UI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.sprint.controllers.UcController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaNovaUC implements Initializable {
    UcController ucController;
    JanelaInicial janelaPrincipalUI;
    @FXML
    private Button btnConfirmUc;
    @FXML
    private TextField textFielSigla;
    @FXML
    private Button btnCancelUc;
    @FXML
    private TextField textFieldDenominacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ucController=new UcController();

    }
    public void associarParentUI(JanelaInicial janelaPrincipalUI) {
        this.janelaPrincipalUI = janelaPrincipalUI;
    }

    @FXML
    public void btnConfirmar(ActionEvent actionEvent) {
        ucController.criarNovaUC(textFielSigla.getText(),textFieldDenominacao.getText());
    }

    @FXML
    public void btnCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
