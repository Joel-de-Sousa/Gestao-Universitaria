package org.sprint.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import org.sprint.controllers.AnoLetivoController;
import org.sprint.controllers.EdicaoController;
import org.sprint.controllers.UcController;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaNovaEdicao implements Initializable {

    EdicaoController edicaoController;

    AnoLetivoController anoLetivoController;

    UcController ucController;
    @FXML
    private Button btnConfirmEdicao;
    @FXML
    private ComboBox<String> comboBoxAnoLetivo;
    @FXML
    private ComboBox<String> comboBoxUC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        edicaoController = new EdicaoController();
        ucController = new UcController();
        anoLetivoController = new AnoLetivoController();
        btnConfirmEdicao.setDisable(true);
        comboBoxUC.setDisable(true);
        comboBoxUC.getItems().addAll(ucController.getListaUnidadeCurricular());
        comboBoxAnoLetivo.getItems().addAll(anoLetivoController.getListaAnos());
    }


    @FXML
    public void actConfirmar(ActionEvent actionEvent) throws Exception {

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
    public void actCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }


    @FXML
    public void actUc(ActionEvent actionEvent) {
        btnConfirmEdicao.setDisable(false);
    }

    @FXML
    public void actAno(ActionEvent actionEvent) {
        comboBoxUC.setDisable(false);
    }
}
