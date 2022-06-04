package WSEdicao.domain.factories;

import WSEdicao.domain.entities.AnoLetivo;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.Uc;
import org.springframework.stereotype.Service;

@Service
public class EdicaoFactory implements IEdicaoFactory{

    public Edicao createEdicao(int codUc, int codAnoLetivo){
        return (new Edicao(codUc,codAnoLetivo));
    }
}
