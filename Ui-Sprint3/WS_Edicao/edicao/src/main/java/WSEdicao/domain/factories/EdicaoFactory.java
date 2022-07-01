package WSEdicao.domain.factories;

import WSEdicao.domain.entities.Edicao;
import org.springframework.stereotype.Service;

@Service
public class EdicaoFactory implements IEdicaoFactory{

    public Edicao createEdicao(int codUc, int codAnoLetivo, int codRUC){
        return (new Edicao(codUc,codAnoLetivo,codRUC));
    }
}
