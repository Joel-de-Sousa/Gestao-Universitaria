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
import org.sprint3.model.DTO.ProjetoRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import org.sprint3.model.DTO.UtilizadorRestDTO;
import org.sprint3.model.repository.ProjetoWebRepository;
import org.sprint3.model.repository.PropostaWebRepository;
import org.sprint3.model.repository.UtilizadorWebRepository;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class JanelaVisualizarConvite implements Initializable {

    private ConviteController conviteController;

    UtilizadorWebRepository utilizadorWebRepository;
    PropostaWebRepository propostaWebRepository;
    ProjetoWebRepository projetoWebRepository;

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
        utilizadorWebRepository = new UtilizadorWebRepository();
        projetoWebRepository = new ProjetoWebRepository();
        propostaWebRepository = new PropostaWebRepository();

    }


    public void displayName(String convite) {


        String[] convite1 = convite.split("-");
        int codConvite = Integer.parseInt(convite1[0]);
        conviteCompleto = conviteController.getConviteByCodConvite(codConvite);

        Optional<ProjetoRestDTO> proj = Optional.empty();
        Optional<PropostaRestDTO> prop = Optional.empty();

        if(conviteCompleto!=null){
        proj = projetoWebRepository.getProjetoByCodProjeto(conviteCompleto.getCodProjeto());}

        if(proj!=null){
        prop = propostaWebRepository.getPropostaById(proj.get().getCodProposta());}

        String tituloProjeto = null;
        String problemaProjeto = null;
        String objectivoProjeto = null;

        if(prop!=null){
        tituloProjeto = prop.get().getTitulo();
        problemaProjeto = prop.get().getProblema();
        objectivoProjeto = prop.get().getObjetivo();}


        Optional<UtilizadorRestDTO> util = utilizadorWebRepository.getUtilizadorById(conviteCompleto.getCodEstudante());
        String nomeEstudante = String.format(util.get().getNome() + " " + util.get().getSobrenome());

        String conviteString = String.format(conviteCompleto.getCodConvite() + " - " + tituloProjeto + "\n" + "Problema: " + problemaProjeto + "\n" + "Objectivo: " + objectivoProjeto +
                "\n" + "Submetida por: " + nomeEstudante + "\n" + "Estado: " + conviteCompleto.getEstado());

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

