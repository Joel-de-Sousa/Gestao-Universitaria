package org.sprint.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.sprint.controllers.AnoLetivoController;


import java.net.URL;
import java.util.ResourceBundle;

public class JanelaNovoAnoLetivo implements Initializable {

    AnoLetivoController anoLetivoController;
    @FXML
    private Label labelAnoLetivoResultado;
    @FXML
    private Button btnConfirmAnoLetivo;

    @FXML
    private Label lblAno;
    @FXML
    private ComboBox<String> cmbIdAno;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        anoLetivoController = new AnoLetivoController();
        lblAno.setVisible(false);
        labelAnoLetivoResultado.setVisible(false);
        btnConfirmAnoLetivo.setDisable(true);
        cmbIdAno.getItems().addAll(anoLetivoController.listaAnosGerada());

    }


    @FXML
    public void btnConfirmar(ActionEvent actionEvent) {
        boolean criou= anoLetivoController.criarNovoAnoLetivo(cmbIdAno.getValue());
        if(criou){
            String ano = anoLetivoController.formatarAno(cmbIdAno.getValue());

            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Criar um novo Ano Letivo.",
                    criou ? "Ano letivo "+ano+" criado com sucesso."
                            : "Não foi possível criar o ano letivo.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        }
    }

    @FXML
    public void btnCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }



    @FXML
    public void astCmbAno(ActionEvent actionEvent) {
        btnConfirmAnoLetivo.setDisable(false);
        labelAnoLetivoResultado.setVisible(true);
        labelAnoLetivoResultado.setDisable(false);
        lblAno.setDisable(false);
        lblAno.setVisible(true);
        labelAnoLetivoResultado.setText(anoLetivoController.formatarAno(cmbIdAno.getValue()));
    }
}
