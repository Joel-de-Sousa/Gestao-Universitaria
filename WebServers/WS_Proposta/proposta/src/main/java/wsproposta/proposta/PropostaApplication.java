package wsproposta.proposta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import wsproposta.proposta.DTO.NewCandidaturaInfoDTO;
import wsproposta.proposta.DTO.NewPropostaInfoDTO;
import wsproposta.proposta.services.CandidaturaService;
import wsproposta.proposta.services.PropostaService;

@SpringBootApplication
public class PropostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PropostaService propostaService, CandidaturaService candidaturaService) {
		return (args) -> {

			NewPropostaInfoDTO novaProposta1 = new NewPropostaInfoDTO(3, 500000025, 1, "Real-time data analytics for Non-Functional Requirements satisfaction",
					"Os sistemas inteligentes, mais do que nunca, exigem processamento de grandes quantidades de dados gerados por fontes de dados heterogéneos e distribuídos.",
					"O foco deste projeto incide no cumprimento das propriedades não funcionais herdadas de sistemas inteligentes, como tempo real (Real Time) e eficiência energética,\n" +
					" para garantir o desempenho da arquitetura de software inicialmente referida");
			propostaService.createAndSaveProposta(novaProposta1);

			NewPropostaInfoDTO novaProposta2 = new NewPropostaInfoDTO(5, 500000025, 2, "Novo Canal de Comunicação On-site Messaging", "O problema da Novo Canal de Comunicação On-site Messaging", "O objectivo da proposta Novo Canal de Comunicação On-site Messaging");
			propostaService.createAndSaveProposta(novaProposta2);

			NewPropostaInfoDTO novaProposta3 = new NewPropostaInfoDTO(1, 500000025, 2, "Desenvolvimento Software Têxtil", "O problema da Desenvolvimento Software Têxtil", "O objectivo da proposta Desenvolvimento Software Têxtil");
			propostaService.createAndSaveProposta(novaProposta3);

			NewCandidaturaInfoDTO novaCandidatura1 = new NewCandidaturaInfoDTO(1, 1);
			candidaturaService.createAndSaveCandidatura(novaCandidatura1);


		};
	}
}
