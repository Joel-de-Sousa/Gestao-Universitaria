package org.sprint.UI;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaNovaProposta implements Initializable {

    private JanelaInicial janelaInicial;
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
    }

    public void associarParentUI(JanelaInicial janelaInicial) {
        this.janelaInicial = janelaInicial;
    }


}
