package Sprint.WsProjeto.datamodel.JDBC.assembler;

import Sprint.WsProjeto.datamodel.JDBC.ConviteJDBC;
import Sprint.WsProjeto.datamodel.JDBC.ConviteRecusadoJDBC;
import Sprint.WsProjeto.datamodel.JPA.ConviteJPA;
import Sprint.WsProjeto.datamodel.JPA.ConviteRecusadoJPA;
import Sprint.WsProjeto.domain.entities.Convite;

public class ConviteJDBCDomainDataAssembler {
    public ConviteJDBC toJDBC (Convite convite){

        return new ConviteJDBC (convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado().ordinal());
    }

    public Convite toDomain (ConviteJDBC conviteJDBC){
       Convite.Estado[] estados= Convite.Estado.values();
       Convite.Estado estado=estados[conviteJDBC.getEstado()];

        return new Convite(conviteJDBC.getCodConvite(),conviteJDBC.getCodProjeto(),conviteJDBC.getCodEstudante(),conviteJDBC.getCodDocente(),estado);
    }

    public ConviteRecusadoJDBC toDataRecusado (Convite convite){

        return new ConviteRecusadoJDBC (convite.getCodConvite(),convite.getCodProjeto(),convite.getCodEstudante(),convite.getCodDocente(),convite.getEstado().ordinal());
    }

    public Convite toDomainRecusado (ConviteRecusadoJDBC conviteJDBC){

        Convite.Estado[] estados= Convite.Estado.values();
        Convite.Estado estado=estados[conviteJDBC.getEstado()];
        return new Convite(conviteJDBC.getCodConvite(),conviteJDBC.getCodProjeto(),conviteJDBC.getCodEstudante(),conviteJDBC.getCodDocente(),estado);
    }
}

