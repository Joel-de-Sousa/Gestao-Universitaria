package WSEdicao.repositories.iRepository;

import WSEdicao.datamodel.REST.UtilizadorRestDTO;

import java.util.Optional;

public interface IUtilizadorWebRepository {

    public Optional<UtilizadorRestDTO> findUtilizadorByCodUtilizador(int codUtilizador);

}
