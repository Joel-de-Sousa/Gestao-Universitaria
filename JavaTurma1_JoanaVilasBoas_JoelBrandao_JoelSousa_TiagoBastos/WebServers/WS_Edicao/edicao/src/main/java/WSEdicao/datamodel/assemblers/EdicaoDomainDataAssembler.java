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

    @Autowired
    MomentoAvaliacaoDomainDataAssembler domainAssembler;

    /*public EdicaoJpa toData(Edicao edicao){
        List<MomentoAvaliacaoJpa> oMomentoAvaliacao= (List<MomentoAvaliacaoJpa>) domainAssembler.toData((MomentoAvaliacao) edicao.getMomentoAvaliacao());
        return new EdicaoJpa(edicao.getUc(), edicao.getAnoLetivo(), edicao.getCodRUC(),edicao.getEstado(),oMomentoAvaliacao);
    }*/

    /*public Edicao toDomain(EdicaoJpa edicaoJpa){
        List<Integer> oMomentoAvaliacao1= (List<Integer>) domainAssembler.toDomain((MomentoAvaliacaoJpa) edicaoJpa.getMomentoAvaliacao());

        return new Edicao(edicaoJpa.getCodEdicao(), edicaoJpa.getCodUc(), edicaoJpa.getCodAnoLetivo(), edicaoJpa.getCodRUC(), edicaoJpa.getEstado(),oMomentoAvaliacao1);
    }*/

    public EdicaoJpa toData(Edicao edicao) {
        List<MomentoAvaliacaoJpa> listMA = new ArrayList<>();
        for (MomentoAvaliacao ma : edicao.getMomentoAvaliacaoList()) {
            MomentoAvaliacaoJpa momentoAvaliacao = new MomentoAvaliacaoJpa(ma.getCodMomentoAvaliacao(),
                    ma.getCodEdicao(),
                    ma.getDenominacao());
            listMA.add(momentoAvaliacao);
        }

        List<EstudanteJpa> listEstudante = new ArrayList<>();
        for (EstudanteJpa  estudante: edicao.getEstudantesList()) {
            EstudanteJpa estudanteJpa = new EstudanteJpa(estudante.getCodEdicao(),
                    estudante.getCodEstudante());
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
                    ma.getCodEdicao(),
                    ma.getDenominacao());
            listMA.add(momentoAvaliacao);
        }

        //nao funfa
        /*List<EstudanteJpa> listEstudante = new ArrayList<>();
        for (EstudanteJpa estudante : edicaoJpa.getListEstudantes()) {
            EstudanteJpa estudanteJpa = new EstudanteJpa(estudante.getCodEdicao(),estudante.getCodEstudante());
            listEstudante.add(estudanteJpa);
        }*/

        Edicao edicao = new Edicao(edicaoJpa.getCodEdicao(),
                edicaoJpa.getCodUc(), edicaoJpa.getCodAnoLetivo(),
                edicaoJpa.getCodRUC(),
                edicaoJpa.getEstado(),
                edicaoJpa.getListEstudantes());

        edicao.setMomentoAvaliacaoList(listMA);
        //edicao.setEstudantesList(listEstudante);

        return edicao;
    }

}

