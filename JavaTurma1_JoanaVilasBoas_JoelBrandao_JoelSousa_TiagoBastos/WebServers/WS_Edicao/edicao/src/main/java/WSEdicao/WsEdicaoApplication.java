package WSEdicao;

import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.*;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.repositories.EdicaoRepository;
import WSEdicao.services.AnoLetivoService;
import WSEdicao.services.EdicaoService;
import WSEdicao.services.MomentoAvaliacaoService;
import WSEdicao.services.UcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class WsEdicaoApplication {
    private static final Logger log = LoggerFactory.getLogger(WsEdicaoApplication.class);

	@Autowired
	EdicaoDomainDTOAssembler edicaoAssembler;

    @Autowired
    EdicaoRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(WsEdicaoApplication.class);



    }

    @Bean
    public CommandLineRunner demo(UcService ucService, AnoLetivoService anoLetivoService, EdicaoService edicaoService, MomentoAvaliacaoService momentoAvaliacaoService) {
        return (args) -> {


            //Create a few Uc, AnoLetivos and Edicões
            UcDTO uc1 = ucService.createAndSaveUc("PTRW", "Portugadfsdsfl");
            AnoLetivoDTO anoLetivo1 = anoLetivoService.createAndSaveAnoLetivo("2015");
            EdicaoDTO edicao1 = edicaoService.createAndSaveEdicao(uc1.getCodUc(), anoLetivo1.getCodAnoLetivo(), 1);

            MomentoAvaliacaoDTO info = new MomentoAvaliacaoDTO(1, "Sprint1");
            MomentoAvaliacaoDTO ma1 = momentoAvaliacaoService.createAndSaveMomentoAvaliacao(info);
			Optional<Edicao > ed1 = repository.findBycodEdicao(edicao1.getCodEdicao());
            EdicaoAllArgsDTO allarg = edicaoAssembler.toDTOAllArgs(ed1.get());

                    System.out.println(allarg);
        };


    }

}
