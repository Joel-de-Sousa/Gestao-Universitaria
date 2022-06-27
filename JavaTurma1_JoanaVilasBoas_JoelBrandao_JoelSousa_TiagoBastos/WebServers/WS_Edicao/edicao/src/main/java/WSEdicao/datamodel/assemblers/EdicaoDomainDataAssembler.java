package WSEdicao.datamodel.assemblers;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.datamodel.MomentoAvaliacaoJpa;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EdicaoDomainDataAssembler {

    public EdicaoJpa toData(Edicao edicao) {
        List<MomentoAvaliacaoJpa> listMA = new ArrayList<>();
        for (MomentoAvaliacao ma : edicao.getMomentoAvaliacaoList()) {
            MomentoAvaliacaoJpa momentoAvaliacao = new MomentoAvaliacaoJpa(ma.getCodMomentoAvaliacao(),
                    ma.getDenominacao());
            listMA.add(momentoAvaliacao);
        }

        List<EstudanteJpa> listEstudante = new ArrayList<>();
        for (EstudanteJpa  estudante: edicao.getEstudantesList()) {
            EstudanteJpa estudanteJpa = new EstudanteJpa(estudante.getCodEstudante(),estudante.getCodUtilizador(),
                    estudante.getCodEdicao());
            listEstudante.add(estudanteJpa);
        }

        EdicaoJpa edicaoJpa = new EdicaoJpa(edicao.getCodEdicao(),
                edicao.getUc(),
                edicao.getAnoLetivo(),
                edicao.getCodRUC(),
                edicao.getEstado(),
                listMA,
                listEstudante);

        return edicaoJpa;
    }

    public Edicao toDomain(EdicaoJpa edicaoJpa) {

        List<MomentoAvaliacao> listMA = new ArrayList<>();
        for (MomentoAvaliacaoJpa ma : edicaoJpa.getMomentoAvaliacao()) {
            MomentoAvaliacao momentoAvaliacao = new MomentoAvaliacao(ma.getCodMomentoAvaliacao(),
                    ma.getDenominacao());
            listMA.add(momentoAvaliacao);
        }

        Edicao edicao = new Edicao(edicaoJpa.getCodEdicao(),
                edicaoJpa.getCodUc(), edicaoJpa.getCodAnoLetivo(),
                edicaoJpa.getCodRUC(),
                edicaoJpa.getEstado(),
                edicaoJpa.getListEstudantes());

        edicao.setMomentoAvaliacaoList(listMA);

        return edicao;
    }

}

