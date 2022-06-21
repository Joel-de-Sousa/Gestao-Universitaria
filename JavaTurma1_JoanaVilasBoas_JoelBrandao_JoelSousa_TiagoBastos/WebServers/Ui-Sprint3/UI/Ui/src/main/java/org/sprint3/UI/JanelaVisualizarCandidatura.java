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
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.PropostaWebRepository;
import org.sprint3.model.repository.UtilizadorWebRepository;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class JanelaVisualizarCandidatura implements Initializable {

    JanelaRUC janelaRUCController;
    CandidaturaController candidaturaController;
    UtilizadorController utilizadorController;

    PropostaWebRepository propostaWebRepository;
    UtilizadorWebRepository utilizadorWebRepository;
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

        propostaWebRepository=new PropostaWebRepository();
        utilizadorWebRepository = new UtilizadorWebRepository();
    }

    public void displayName(String candidatura) {
        String[] candidatura1 = candidatura.split("-");
        int codCandidatura = Integer.parseInt(candidatura1[0]);
        candidaturaCompleta = candidaturaController.getCandidaturaById(codCandidatura);

        Optional<PropostaRestDTO> proposta = propostaWebRepository.getPropostaById(candidaturaCompleta.getCodProposta());

        int codC = candidaturaCompleta.getCodCandidatura();
        String titulo = proposta.get().getTitulo();//candidaturaCompleta.getTituloProposta();
        String problema = proposta.get().getProblema();
        String objectivo = proposta.get().getObjetivo();
        String estado = candidaturaCompleta.getEstadoEstudante();

        Optional<UtilizadorRestDTO> utilizador = utilizadorWebRepository.getUtilizadorById(candidaturaCompleta.getCodEstudante());
        String nomeUtilizador = utilizador.get().getNome() + " " +utilizador.get().getSobrenome();

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
