package org.sprint.UI;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.sprint.controllers.PropostaController;
import org.sprint.controllers.UcController;
import org.sprint.model.DTO.UcRestDTO;
import org.sprint.model.repository.REST.UcRestRepository;

public class JanelaInicial implements Initializable {
    private PropostaController appController;

    private UcController ucController;
    private Stage janelaNovaProposta;

    private Stage janelaNovaEdicao;

    private Stage janelaNovaUc;

    private Stage janelaNovoAnoLetivo;

    UcRestRepository ucRestRepository= new UcRestRepository();



    private Stage janelaConsultarUc;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    /*    try {

           iniciar(janelaNovaProposta,"NovaProposta","/fxml/JanelaNovaProposta.fxml");

        } catch (IOException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("ListaTarefas");
            alerta.setHeaderText("Erro");
            alerta.setContentText(ex.getMessage());}

    }*/
    }





    @FXML
    public void mnuCriarUC(ActionEvent actionEvent) throws IOException {
        iniciar(janelaNovaUc,"Nova Unidade Curricular","/fxml/JanelaNovaUC.fxml");



        UcRestDTO ucRestDTO=new UcRestDTO("LOASDCS","TESTE FEITO E CONCLUIDO");
        UcRestDTO novo= ucRestRepository.createUc(ucRestDTO).get();

            System.out.println("----------------------------------------");

            System.out.println("----------------------------------------");
            System.out.println(novo.getSigla());
        System.out.println("----------------------------------------");
        System.out.println(novo.getDenominacao());
        System.out.println("----------------------------------------");

            System.out.println("----------------------------------------");
            System.out.println("----------------------------------------");








        System.out.println("-------------------------------------------------------------------");
        if(ucRestRepository.findById(1).isPresent()){
            System.out.println("Entrou");
        System.out.println(ucRestRepository.findById(1).get().getDenominacao());}
        else {
            System.out.println("Falhou");
        }

    }

    @FXML
    public void mnuCriarAnoLetivo(ActionEvent actionEvent) throws IOException {
        iniciar(janelaNovoAnoLetivo,"Novo Ano Letivo","/fxml/JanelaNovoAnoLetivo.fxml");
    }

    @FXML
    public void mnuCriarProposta(ActionEvent actionEvent) throws IOException {
        iniciar(janelaNovaProposta,"NovaProposta","/fxml/JanelaNovaProposta.fxml");

    }

    @FXML
    public void mnuConsultarUC(ActionEvent actionEvent) throws IOException {
        iniciar(janelaConsultarUc,"Consultar Unidade Curricular","/fxml/JanelaConsultarUc.fxml");
    }

    @FXML
    public void mnuCriarEdicao(ActionEvent actionEvent) throws IOException {
        iniciar(janelaNovaEdicao,"Nova Edição","/fxml/JanelaNovaEdicao.fxml");
    }

    @FXML
    public void mnuAbout(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop d=Desktop.getDesktop();

        // Browse a URL
        d.browse(new URI("https://github.com/Joel-de-Sousa/Sprint2/tree/master/documentation"));
    }




    public void iniciar (Stage novaJanela, String titulo, String url) throws IOException {
        novaJanela=new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        novaJanela.initModality(Modality.APPLICATION_MODAL);
        novaJanela.setTitle(titulo);
        novaJanela.setResizable(false);
        novaJanela.setScene(scene);
        novaJanela.show();

    }
}
