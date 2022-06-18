package WSEdicao.domain.factories;

import WSEdicao.domain.entities.MomentoAvaliacao;

public interface IMomentoAvaliacaoFactory {

    public MomentoAvaliacao createMomentoAvaliacao(int codEdicao,String denominacao);
}
