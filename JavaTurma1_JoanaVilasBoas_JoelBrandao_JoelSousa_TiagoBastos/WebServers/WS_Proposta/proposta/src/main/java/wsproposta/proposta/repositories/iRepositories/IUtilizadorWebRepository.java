package wsproposta.proposta.repositories.iRepositories;

import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;

import java.util.Optional;

public interface IUtilizadorWebRepository {

    public Optional<UtilizadorRestDTO> findUtilizadorByCodUtilizador(int codUtilizador);
}
