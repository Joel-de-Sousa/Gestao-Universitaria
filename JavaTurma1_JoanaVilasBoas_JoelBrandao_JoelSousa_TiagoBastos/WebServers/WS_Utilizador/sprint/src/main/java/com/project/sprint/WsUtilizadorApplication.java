package com.project.sprint;

import com.project.sprint.DTO.NewUtilizadorInfoDTO;
import com.project.sprint.DTO.UtilizadorDTO;
import com.project.sprint.domain.entities.Utilizador;
import com.project.sprint.service.UtilizadorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WsUtilizadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsUtilizadorApplication.class);
    }


    @Bean
    public CommandLineRunner demo(UtilizadorService utilizadorService) {
        return (args) -> {
            NewUtilizadorInfoDTO novoUtilizador1 = new NewUtilizadorInfoDTO("Joel", "Brandão", "joelsantosbrandao@gmail.com", "ESTUDANTE");
            utilizadorService.createAndSaveUtilizador(novoUtilizador1);

            NewUtilizadorInfoDTO novoUtilizador2 = new NewUtilizadorInfoDTO("Jorge", "Duarte", "jorgeDuarte@gmail.com", "DOCENTE");
            utilizadorService.createAndSaveUtilizador(novoUtilizador2);

            NewUtilizadorInfoDTO novoUtilizador3 = new NewUtilizadorInfoDTO("Francisco", "Reis", "indianaJones@hotmail.com", "ESTUDANTE");
            utilizadorService.createAndSaveUtilizador(novoUtilizador3);

            NewUtilizadorInfoDTO novoUtilizador4 = new NewUtilizadorInfoDTO("Nuno", "Morgado", "nunoMorgado@gmail.com", "DOCENTE");
            utilizadorService.createAndSaveUtilizador(novoUtilizador4);

            NewUtilizadorInfoDTO novoUtilizador5 = new NewUtilizadorInfoDTO("Paulo", "Baltarejo", "pauloBaltarejo@gmail.com", "DOCENTE");
            utilizadorService.createAndSaveUtilizador(novoUtilizador5);

            NewUtilizadorInfoDTO novoUtilizador6 = new NewUtilizadorInfoDTO("Joana", "Silva", "joanaSilva@gmail.com", "ADMINISTRADOR");
            utilizadorService.createAndSaveUtilizador(novoUtilizador6);
        };
    }
}