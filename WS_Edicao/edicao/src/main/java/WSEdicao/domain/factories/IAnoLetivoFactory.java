package WSEdicao.domain.factories;


import WSEdicao.domain.entities.AnoLetivo;

import java.util.Date;

public interface IAnoLetivoFactory {

    public AnoLetivo createAnoLetivo(int codAnoLetivo, int ano);
}
