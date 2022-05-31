package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;

import java.util.Optional;

public class UtiliizadorWebRepository {

    @Autowired
    UtilizadorRestRepository utilizadorRestRepository;

    public Optional<UtilizadorRestDTO> findUtilizadorByCodUtilizador(int codUtilizador) {
        Optional<UtilizadorRestDTO> opUtilizador = utilizadorRestRepository.findUtilizadorByCodUtilizador(codUtilizador);

        if ( opUtilizador.isPresent() ) {
            return opUtilizador;
        }
        else
            return Optional.empty();
    }
}
