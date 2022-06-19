package org.sprint3.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.sprint3.controller.ProjetoController;
import org.sprint3.controller.UtilizadorController;
import org.sprint3.model.DTO.ProjetoRestDTO;

import java.net.URL;
import java.util.ResourceBundle;

public class JanelaJuri implements Initializable {

    UtilizadorController utilizadorController;
    ProjetoController projetoController;
    @FXML
    private Label labelPropostas;
    @FXML
    private ComboBox<String> comboBoxProjetos;
    @FXML
    private Label labelOrientador;
    @FXML
    private Label labelSelecioneDocente;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label labelPresidente;
    @FXML
    private Label labelArguente;
    @FXML
    private ComboBox<String> comboBoxDocentes;
    @FXML
    private Button btnConfirmar;

    String edicao;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        projetoController = new ProjetoController();

        utilizadorController = new UtilizadorController();
        comboBoxDocentes.getItems().addAll(utilizadorController.getAllDocentes());

        /*projetoController = new ProjetoController();

        comboBoxProjetos.getItems().addAll(projetoController.getAllProjetos());*/

        //btnConfirmar.setDisable(true);

        labelSelecioneDocente.setText("Seleccione Arguente:");
        labelSelecioneDocente.setDisable(true);
        comboBoxDocentes.setDisable(true);

        labelOrientador.setText("Orientador:");
        labelArguente.setText("Arguente:");
        labelPresidente.setText("Presidente:");

        // TODO
    }

    public void displayName(String edicao1) {
        edicao = edicao1;
        initProjetos ();
    }

    public void initProjetos () {
        comboBoxProjetos.getItems().addAll(projetoController.getAllProjetos());
    }

    @FXML
    public void handleDocenteAction(ActionEvent actionEvent) {
        String docente = comboBoxDocentes.getValue();

        String[] doc = docente.split("-");
        String nomeDoc = doc[1];

        labelArguente.setText("Arguente: " + nomeDoc);

        labelSelecioneDocente.setText("Seleccione Presidente:");

        comboBoxDocentes.setOnAction(this::handleSeleccionarPresidenteAction);
    }

    public void handleSeleccionarPresidenteAction(ActionEvent actionEvent) {

        String docente2 = comboBoxDocentes.getValue();

        String[] doc2 = docente2.split("-");
        String nomeDoc2 = doc2[1];

        labelPresidente.setText("Presidente: " + nomeDoc2);

        comboBoxDocentes.setDisable(true);

        /*btnConfirmar.setDisable(false);
        btnConfirmar.setOnAction(this::handleButtonActionConfirmar);*/
    }


    @FXML
    public void handleButtonActionCancelar(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();


    }

    @FXML
    public void handleProjetosAction(ActionEvent actionEvent) {

        String proj = comboBoxProjetos.getValue();

        String[] projeto = proj.split("-");
        int codProj = Integer.parseInt(projeto[0]);

        ProjetoRestDTO projDto = projetoController.getProjetoByCodProjeto(codProj);
        String nomeOrientador = String.format(projDto.getNomeOrientador() + " " + projDto.getSobrenomeOrientador());

        labelOrientador.setText("Orientador: " + nomeOrientador);

        labelPropostas.setText(comboBoxProjetos.getValue());

        labelSelecioneDocente.setDisable(false);
        comboBoxDocentes.setDisable(false);

        comboBoxProjetos.setDisable(true);
    }

    @FXML
    public void handleButtonActionConfirmar(ActionEvent actionEvent) {
        try {
            String orientador = labelOrientador.getText();
            String arguente = labelArguente.getText();
            String presidente = labelPresidente.getText();

            boolean criou = projetoController.criarNovoJuri(orientador, arguente, presidente);


            AlertaUI.criarAlerta(Alert.AlertType.INFORMATION, MainApp.TITULO_APLICACAO, "Definir Júri de Momento de Avaliação.",
                    criou ? "Júri definido com sucesso."
                            : "Não foi possível definir Júri.").show();
            ((Node) actionEvent.getSource()).getScene().getWindow().hide();
        } catch (Exception e) {
            AlertaUI.criarAlerta(Alert.AlertType.ERROR, MainApp.TITULO_APLICACAO, "Erro nos dados.",
                    e.getMessage()).show();

        }
    }

}
