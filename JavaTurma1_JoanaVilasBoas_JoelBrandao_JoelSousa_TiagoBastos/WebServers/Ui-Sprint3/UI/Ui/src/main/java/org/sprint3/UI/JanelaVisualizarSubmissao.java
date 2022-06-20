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
import org.sprint3.model.DTO.SubmissaoRestDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaVisualizarSubmissao implements Initializable {

    JanelaDocente janelaDocente;
    private PropostaController propostaController;
    private UtilizadorController utilizadorController;

    private ProjetoController projetoController;

    @FXML
    private Label labelPropostas;
    @FXML
    private Button btnAceitar;
    @FXML
    private Button btnRejeitar;
    @FXML
    private Button btnCancelar;

    SubmissaoRestDTO submissaoCompleta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        janelaDocente = new JanelaDocente();
        propostaController = new PropostaController();
        utilizadorController = new UtilizadorController();
        projetoController = new ProjetoController();

    }

    public void displayName(String submissaoSeleccionada) {
        String[] submissao1 = submissaoSeleccionada.split("-");
        int codSubmissao = Integer.parseInt(submissao1[0]);
        submissaoCompleta = new SubmissaoRestDTO(1, "Titulo teste","PT");//projetoController.getSubmissaoById(codSubmissao);

        int codS = submissaoCompleta.getCodSubmissao();
        String titulo = submissaoCompleta.getTitulo();
        byte[] ficheiro = submissaoCompleta.getFicheiro();
        String lingua = submissaoCompleta.getLinguagemFicheiro();

        String submissaoString = String.format(codS + "- " + titulo + "\n" + "Ficheiro: " + ficheiro + "\n" + "Lingua do ficheiro: " + lingua);

        labelPropostas.setText(submissaoString);

    }

    @FXML
    public void handleButtonActionAceitar(ActionEvent actionEvent) {

       /* try {
            int codSubmissao = submissaoCompleta.getCodSubmissao();
            String estado = "ACEITE";
            boolean alterou = projetoController.alterarEstadoAceite(codSubmissao, estado);
            *//*int codEstudante = propostaCompleta.getCodUtilizador();
            //projetoController.criarProjeto(codProp, codEstudante);*//*

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Aceitar Conteúdo Submetido",
                        alterou ? "Conteúdo aceite com sucesso."
                                : "Não foi possível alterar estado da submissão para Aceite").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }*/
    }

    @javafx.fxml.FXML
    public void handleButtonActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

    }

    @javafx.fxml.FXML
    public void handleButtonActionRejeitar(ActionEvent actionEvent) {

       /* try {
            int codSubmissao = submissaoCompleta.getCodSubmissao();
            String estado = "REJEITADA";
            boolean alterou = projetoController.alterarEstadoAceite(codSubmissao, estado);

            if (alterou) {
                AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Rejeitar Conteúdo Submetido",
                        alterou ? "Submissão rejeitada."
                                : "Não foi possível alterar estado da submissão para Rejeitada").show();
                ((Node) actionEvent.getSource()).getScene().getWindow().hide();
            }
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();
        }*/
    }
}
