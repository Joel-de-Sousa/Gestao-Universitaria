package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.sprint3.controller.ProjetoController;
import org.sprint3.controller.PropostaController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.PropostaRestDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaVisualizarProposta implements Initializable {

    JanelaRUC janelaRUCController;
    private PropostaController propostaController;
    private UtilizadorController utilizadorController;

    private ProjetoController projetoController;

    @FXML
    private Button btnAceitar;
    @FXML
    private Button btnRejeitar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label labelPropostas;

    PropostaRestDTO propostaCompleta;
    @FXML
    private Label labelTitulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        janelaRUCController = new JanelaRUC();
        propostaController = new PropostaController();
        utilizadorController = new UtilizadorController();
        projetoController = new ProjetoController();

    }

    public void displayName(String proposta) {
        String[] proposta1 = proposta.split("-");
        int codProposta = Integer.parseInt(proposta1[0]);
        propostaCompleta = propostaController.getPropostaById(codProposta);

        int codP = propostaCompleta.getCodProposta();
        long nif = propostaCompleta.getNifOrganizacao();
        int codEd = propostaCompleta.getCodEdicao();
        String titulo = propostaCompleta.getTitulo();
        String problema = propostaCompleta.getProblema();
        String objectivo = propostaCompleta.getObjetivo();
        String estado = propostaCompleta.getEstado();
        String nomeUtilizador = propostaCompleta.getNomeProponente() + " " + propostaCompleta.getSobrenomeProponente();

        String propostaString = String.format(codP + "- " + titulo + "\n" + "Problema: " + problema + "\n" + "Objectivo: " + objectivo +
                "\n" + "NIF da Organização: " + nif +"\n"+ "Submetida por: " + nomeUtilizador + "\n" + "Estado: " + estado);

        labelPropostas.setText(propostaString);

    }

    @FXML
    public void handleButtonActionAceitar(ActionEvent actionEvent) {

        try {
            int codProp = propostaCompleta.getCodProposta();
            String estado = "ACEITE";
            boolean alterou = propostaController.alterarEstadoAceite(codProp, estado);
            /*int codEstudante = propostaCompleta.getCodUtilizador();
            //projetoController.criarProjeto(codProp, codEstudante);*/

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Aceitar Proposta Submetida",
                        alterou ? "Proposta aceite com sucesso."
                                : "Não foi possível alterar estado da proposta para Aceite").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }
    }

    @FXML
    public void handleButtonActionRejeitar(ActionEvent actionEvent) {

        try {
            int codProp = propostaCompleta.getCodProposta();
            String estado = "REJEITADA";
            boolean alterou = propostaController.alterarEstadoAceite(codProp, estado);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Rejeitar Proposta Submetida",
                        alterou ? "Proposta rejeitada com sucesso."
                                : "Não foi possível alterar estado da proposta para Rejeitada").show();
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
}
