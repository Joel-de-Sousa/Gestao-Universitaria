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
	public CommandLineRunner demo(UtilizadorService utilizadorService ) {
		return (args) -> {
			// create and save a few Utilizadores
			NewUtilizadorInfoDTO novoUtilizador1=new NewUtilizadorInfoDTO("joel","Brandao","joelsantosbrandao@gmail.com","ESTUDANTE");
			 utilizadorService.createAndSaveUtilizador(novoUtilizador1);
			NewUtilizadorInfoDTO novoUtilizador2=new NewUtilizadorInfoDTO("Ana","Maria","mariaSantos@gmail.com","DOCENTE");
			// create and save a few countries
			utilizadorService.createAndSaveUtilizador(novoUtilizador2);
			NewUtilizadorInfoDTO novoUtilizador3=new NewUtilizadorInfoDTO("Francisco","Reis","indianaJones@hotmail.com","ESTUDANTE");
			// create and save a few countries
			 utilizadorService.createAndSaveUtilizador(novoUtilizador3);
		};
	}
}