package wsproposta.proposta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.services.PropostaService;

@SpringBootApplication
public class PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PropostaService propostaService) {
		return (args) -> {
			NewPropostaInfoDTO novaProposta1 = new NewPropostaInfoDTO(1, 500000025, 1, "Titulo da Proposta", "O problema da proposta", "O objectivo da proposta");
			propostaService.createAndSaveProposta(novaProposta1);
			NewPropostaInfoDTO novaProposta2 = new NewPropostaInfoDTO(1, 500000025, 1, "Titulo da Proposta2", "O problema da proposta2", "O objectivo da proposta2");
			propostaService.createAndSaveProposta(novaProposta1);



		};
	}
}
