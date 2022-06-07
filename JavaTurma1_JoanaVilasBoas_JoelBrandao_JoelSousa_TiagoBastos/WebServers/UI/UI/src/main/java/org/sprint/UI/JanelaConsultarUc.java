package org.sprint.UI;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.sprint.controllers.UcController;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class JanelaConsultarUc implements Initializable {

    UcController ucController;

    JanelaInicial janelaPrincipalUI;

    UcRestRepository ucRestRepository;
    @FXML
    private Button btnOk;
    @FXML
    private ComboBox<String> cmbSigla;
    @FXML
    private Label lblResDenominacao;
    @FXML
    private Label LblDenominacao;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ucRestRepository=new UcRestRepository();
        ucController= new UcController();
        LblDenominacao.setVisible(false);
        lblResDenominacao.setVisible(false);
        cmbSigla.setPromptText("Selecione Sigla!");
        cmbSigla.getItems().addAll(ucController.getListaSiglas());

    }



    public void associarParentUI(JanelaInicial janelaPrincipalUI) {
        this.janelaPrincipalUI = janelaPrincipalUI;
    }



    @FXML
    public void cmbAction(ActionEvent actionEvent) {
        LblDenominacao.setVisible(true);
        LblDenominacao.setText("Denominação:");
        String valor =cmbSigla.getValue();
        lblResDenominacao.setVisible(true);
        lblResDenominacao.setText(ucController.getDenominacao(valor));
    }

    @FXML
    public void btnSairOK(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }
}
