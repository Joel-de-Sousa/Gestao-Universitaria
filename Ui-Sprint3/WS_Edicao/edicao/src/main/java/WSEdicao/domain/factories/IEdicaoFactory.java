package WSEdicao.domain.factories;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;

import java.util.List;

public interface IEdicaoFactory {

    public Edicao createEdicao(int codUc, int codAnoLetivo, int codRUC);
}
