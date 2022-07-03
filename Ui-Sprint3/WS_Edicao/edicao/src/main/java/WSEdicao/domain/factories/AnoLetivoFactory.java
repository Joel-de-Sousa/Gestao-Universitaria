package WSEdicao.domain.factories;

import WSEdicao.domain.entities.AnoLetivo;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AnoLetivoFactory implements IAnoLetivoFactory{

    public AnoLetivo createAnoLetivo(String ano) {
        return (new AnoLetivo(ano));
    }
}
