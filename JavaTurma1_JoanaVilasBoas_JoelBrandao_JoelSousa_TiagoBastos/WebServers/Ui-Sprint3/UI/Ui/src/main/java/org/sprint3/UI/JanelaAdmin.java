package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.sprint3.controller.AnoLetivoController;
import org.sprint3.controller.EdicaoController;
import org.sprint3.controller.UcController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaAdmin implements Initializable {

    EdicaoController edicaoController;
    AnoLetivoController anoLetivoController;
    UcController ucController;
    JanelaPrincipal janelaPrincipalController;
    @FXML
    private Button btnConfirmEdicao;
    @FXML
    private Button btnCancelEdicao;
    @FXML
    private ComboBox comboBoxAnoLetivo;
    @FXML
    private ComboBox comboBoxUC;

    public void initialize(URL url, ResourceBundle rb) {
        edicaoController = new EdicaoController();
        ucController = new UcController();
        anoLetivoController = new AnoLetivoController();
        janelaPrincipalController = new JanelaPrincipal();

        btnConfirmEdicao.setDisable(true);
        comboBoxUC.setDisable(true);
        //comboBoxUC.getItems().addAll(ucController.getListaUnidadeCurricular());
        comboBoxUC.getItems().addAll("PDS");
        //comboBoxAnoLetivo.getItems().addAll(anoLetivoController.getListaAnos());
        comboBoxAnoLetivo.getItems().addAll("TESTE");
    }

    @FXML
    public void actConfirmar(ActionEvent actionEvent) {

        try {
            int ano = comboBoxAnoLetivo.getSelectionModel().getSelectedIndex() + 1;
            int uc = comboBoxUC.getSelectionModel().getSelectedIndex() + 1;

            boolean criou = edicaoController.criarNovaEdicao(uc, ano);

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar uma nova Edição.",
                    criou ? "Edição criada com sucesso."
                            : "Não foi possível criar a edição.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }catch (Exception e){
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    @FXML
    public void actUc(ActionEvent actionEvent) {
        btnConfirmEdicao.setDisable(false);
    }

    @FXML
    public void actAno(ActionEvent actionEvent) {
        comboBoxUC.setDisable(false);
    }

    @FXML
    public void actCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }


}
