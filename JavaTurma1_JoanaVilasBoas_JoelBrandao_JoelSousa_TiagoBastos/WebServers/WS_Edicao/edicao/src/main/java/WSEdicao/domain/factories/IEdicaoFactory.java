package WSEdicao.domain.factories;
import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;

public interface IEdicaoFactory {

    public Edicao createEdicao(int codUc, int codAnoLetivo,int codRUC);
}
