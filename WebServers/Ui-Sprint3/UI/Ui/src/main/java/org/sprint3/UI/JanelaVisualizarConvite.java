package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.sprint3.controller.ConviteController;
import org.sprint3.model.DTO.ConviteRestDTO;


import java.net.URL;
import java.util.ResourceBundle;

public class JanelaVisualizarConvite implements Initializable {

    private ConviteController conviteController;

    @FXML
    private Label labelPropostas;
    @FXML
    private Button btnAceitar;
    @FXML
    private Button btnRejeitar;
    @FXML
    private Button btnCancelar;

    ConviteRestDTO conviteCompleto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conviteController = new ConviteController();

    }


    public void displayName(String convite) {
        String[] convite1 = convite.split("-");
        int codConvite = Integer.parseInt(convite1[0]);
        conviteCompleto = conviteController.getConviteByCodConvite(codConvite);

        String conviteString = String.format(conviteCompleto.getCodConvite() + " - " + conviteCompleto.getTituloProjeto() + "\n" + "Problema: " + conviteCompleto.getProblemaProjeto() + "\n" + "Objectivo: " + conviteCompleto.getObjectivoProjeto() +
                "\n" + "Submetida por: " + conviteCompleto.getNomeEstudante()+" "+conviteCompleto.getSobrenomeEstudante() + "\n" + "Estado: " + conviteCompleto.getEstado());

        labelPropostas.setText(conviteString);
    }

    @FXML
    public void handleButtonActionAceitar(ActionEvent actionEvent) {
        try {
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
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    @FXML
    public void handleButtonActionCancelar(ActionEvent actionEvent) {

        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void handleButtonActionRejeitar(ActionEvent actionEvent) {
        try {
            int codConv = conviteCompleto.getCodConvite();
            String estado = "REJEITADO";
            boolean alterou = conviteController.alterarEstadoConvite(codConv, estado);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Rejeitar Convite",
                        alterou ? "Convite rejeitado."
                                : "Não foi possível alterar estado do convite para Rejeitado").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }
}

