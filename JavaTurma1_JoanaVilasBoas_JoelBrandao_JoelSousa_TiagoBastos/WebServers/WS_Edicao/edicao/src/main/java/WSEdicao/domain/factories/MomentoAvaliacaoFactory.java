package WSEdicao.domain.factories;

import WSEdicao.domain.entities.MomentoAvaliacao;
import org.springframework.stereotype.Service;

@Service
public class MomentoAvaliacaoFactory implements IMomentoAvaliacaoFactory{

    public MomentoAvaliacao createMomentoAvaliacao(int codEdicao,String denominacao){
        return (new MomentoAvaliacao(codEdicao,denominacao));
    }


}
