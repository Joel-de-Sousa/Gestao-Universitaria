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
			NewPropostaInfoDTO novaProposta1 = new NewPropostaInfoDTO(1, 500000025, 1, "Real-time data analytics for Non-Functional Requirements satisfaction",
					"Os sistemas inteligentes, mais do que nunca, exigem processamento de grandes quantidades de dados gerados por fontes de dados heterogéneos e distribuídos.",
					"O foco deste projeto incide no cumprimento das propriedades não funcionais herdadas de sistemas inteligentes, como tempo real (Real Time) e eficiência energética,\n" +
					" para garantir o desempenho da arquitetura de software inicialmente referida");
			propostaService.createAndSaveProposta(novaProposta1);

			NewPropostaInfoDTO novaProposta2 = new NewPropostaInfoDTO(5, 500000025, 2, "Titulo da Proposta2", "O problema da proposta2", "O objectivo da proposta2");
			propostaService.createAndSaveProposta(novaProposta2);



		};
	}
}
