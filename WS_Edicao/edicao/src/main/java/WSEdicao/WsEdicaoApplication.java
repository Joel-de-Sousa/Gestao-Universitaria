package WSEdicao;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import WSEdicao.dto.AnoLetivoDTO;
import WSEdicao.dto.EdicaoDTO;
import WSEdicao.dto.NewEdicaoInfoDTO;
import WSEdicao.dto.UcDTO;
import WSEdicao.services.AnoLetivoService;
import WSEdicao.services.EdicaoService;
import WSEdicao.services.UcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class WsEdicaoApplication {
	private static final Logger log = LoggerFactory.getLogger(WsEdicaoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(WsEdicaoApplication.class);


	}

		@Bean
		public CommandLineRunner demo(UcService ucService, AnoLetivoService anoLetivoService, EdicaoService edicaoService) {
			return (args) -> {


				//Create a few Uc, AnoLetivos and Edicões
				UcDTO uc1 = ucService.createAndSaveUc("PTE", "Portugal telecomunicações eletrica");
				UcDTO uc2 = ucService.createAndSaveUc("ASDE", "Associao de Ramalde");
				UcDTO uc3 = ucService.createAndSaveUc("OLA", "olha la arroz");
				UcDTO uc4 = ucService.createAndSaveUc("FOGO", "Fontes operarias gordas obesas");
				UcDTO uc5 = ucService.createAndSaveUc("TOP", "Tudo olha preto");
				UcDTO uc6 = ucService.createAndSaveUc("XUXA", "mamamadeira");
				UcDTO uc7 = ucService.createAndSaveUc("RITA", "INacio multipla verde");

				/*AnoLetivoDTO anoLetivo1 = anoLetivoService.createAndSaveAnoLetivo("2015-2016");
				EdicaoDTO edicao1 = edicaoService.createAndSaveEdicao(uc1.getCodUc(), anoLetivo1.getCodAnoLetivo());*/
			};


	}

}
