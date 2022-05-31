package WSEdicao.domain.factories;

import WSEdicao.domain.entities.Uc;
import org.springframework.stereotype.Service;

@Service
public class UcFactory implements IUcFactory{

    public Uc createUc(int codUc, String sSigla, String sDenominacao) {
        return (new Uc(codUc,sSigla,sDenominacao));
    }
}
