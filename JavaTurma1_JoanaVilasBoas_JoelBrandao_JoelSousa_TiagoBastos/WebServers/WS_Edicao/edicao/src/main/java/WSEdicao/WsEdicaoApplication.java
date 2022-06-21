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
            /*UcDTO uc1 = ucService.createAndSaveUc("POO", "Programação Orientada Objectos");
            UcDTO uc2 = ucService.createAndSaveUc("PDS", "Princípios de Desenvolvimento de Software");
            UcDTO uc3 = ucService.createAndSaveUc("BDD", "Base de Dados");

            AnoLetivoDTO anoLetivo1 = anoLetivoService.createAndSaveAnoLetivo("2015");
            AnoLetivoDTO anoLetivo2 = anoLetivoService.createAndSaveAnoLetivo("2022");
            AnoLetivoDTO anoLetivo3 = anoLetivoService.createAndSaveAnoLetivo("2021");

            EdicaoDTO edicao1 = edicaoService.createAndSaveEdicao(uc1.getCodUc(), anoLetivo2.getCodAnoLetivo(), 1);
            EdicaoDTO edicao2 = edicaoService.createAndSaveEdicao(uc2.getCodUc(), anoLetivo2.getCodAnoLetivo(), 1);
            EdicaoDTO edicao3 = edicaoService.createAndSaveEdicao(uc3.getCodUc(), anoLetivo2.getCodAnoLetivo(), 1);

            MomentoAvaliacaoDTO info = new MomentoAvaliacaoDTO(1, "MA1");
            MomentoAvaliacaoDTO ma1 = momentoAvaliacaoService.createAndSaveMomentoAvaliacao(info);

            MomentoAvaliacaoDTO info2 = new MomentoAvaliacaoDTO(1, "MA2");
            MomentoAvaliacaoDTO ma2 = momentoAvaliacaoService.createAndSaveMomentoAvaliacao(info2);

			Optional<Edicao > ed1 = repository.findBycodEdicao(edicao1.getCodEdicao());
            EdicaoAllArgsDTO allarg = edicaoAssembler.toDTOAllArgs(ed1.get());

                    System.out.println(allarg);*/
        };


    }

}
