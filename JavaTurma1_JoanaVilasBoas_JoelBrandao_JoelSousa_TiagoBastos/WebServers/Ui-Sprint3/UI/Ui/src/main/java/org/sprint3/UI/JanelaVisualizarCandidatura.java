package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.sprint3.controller.CandidaturaController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.CandidaturaRestDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaVisualizarCandidatura implements Initializable {

    JanelaRUC janelaRUCController;
    CandidaturaController candidaturaController;
    UtilizadorController utilizadorController;
    @FXML
    private Label labelTitulo;
    @FXML
    private Button btnAceitar;
    @FXML
    private Button btnRejeitar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label labelCandidatura;

    CandidaturaRestDTO candidaturaCompleta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        janelaRUCController = new JanelaRUC();
        candidaturaController = new CandidaturaController();
        utilizadorController = new UtilizadorController();


    }

    public void displayName(String candidatura) {
        String[] candidatura1 = candidatura.split("-");
        int codCandidatura = Integer.parseInt(candidatura1[0]);
        candidaturaCompleta = candidaturaController.getCandidaturaById(codCandidatura);

        int codC = candidaturaCompleta.getCodCandidatura();
        String titulo = candidaturaCompleta.getTituloProposta();
        String problema = candidaturaCompleta.getProblemaProposta();
        String objectivo = candidaturaCompleta.getObjectivoProposta();
        String estado = candidaturaCompleta.getEstado();
        String nomeUtilizador = candidaturaCompleta.getNomeEstudante() + " " +candidaturaCompleta.getSobrenomeEstudante();

        String propostaString = String.format(codC + "- " + titulo + "\n" + "Problema: " + problema + "\n" + "Objectivo: " + objectivo +
                "\n" + "Submetida por: " + nomeUtilizador + "\n" + "Estado: " + estado);

        labelCandidatura.setText(propostaString);

    }

    @FXML
    public void handleButtonActionAceitar(ActionEvent actionEvent) {

        try {
            int codCand = candidaturaCompleta.getCodCandidatura();
            String estado = "ACEITE";
            boolean alterou = candidaturaController.alterarEstadoCandidatura(codCand, estado);
            //int codEstudante = candidaturaCompleta.getCodUtilizador();
            //projetoController.criarProjeto(codProp, codEstudante);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Aceitar Candidatura Submetida",
                        alterou ? "Candidatura aceite com sucesso."
                                : "Não foi possível alterar estado da candidatura para Aceite").show();
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
            int codCand = candidaturaCompleta.getCodCandidatura();
            String estado = "REJEITADA";
            boolean alterou = candidaturaController.alterarEstadoCandidatura(codCand, estado);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Rejeitar Candidatura Submetida",
                        alterou ? "Candidatura rejeitada."
                                : "Não foi possível alterar estado da candidatura para Rejeitada").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }
}
