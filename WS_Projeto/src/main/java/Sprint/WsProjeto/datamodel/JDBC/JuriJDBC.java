package Sprint.WsProjeto.datamodel.JDBC;

import lombok.*;


@AllArgsConstructor
@Getter
@Setter
public class JuriJDBC {
    //ordem retornada pelo PLSQL
    public static final int COD_JURI = 1;
    public static final int COD_PRESIDENTE = 2;
    public static final int COD_ORIENTADOR = 3;
    public static final int COD_ARGUENTE = 4;

    private int codJuri;

    private int codPresidente;

    private int codOrientador;

    private int codArguente;

    public JuriJDBC(int codPresidente, int codOrientador, int codArguente) {
        this.codPresidente = codPresidente;
        this.codOrientador = codOrientador;
        this.codArguente = codArguente;
    }
}
