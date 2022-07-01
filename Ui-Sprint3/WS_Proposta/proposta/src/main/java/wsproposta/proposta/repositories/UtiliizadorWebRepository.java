package wsproposta.proposta.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import wsproposta.proposta.datamodel.REST.UtilizadorRestDTO;
import wsproposta.proposta.repositories.REST.UtilizadorRestRepository;
import wsproposta.proposta.repositories.iRepositories.IUtilizadorWebRepository;

import java.util.Optional;

@Repository
public class UtiliizadorWebRepository implements IUtilizadorWebRepository {

    @Autowired
    UtilizadorRestRepository utilizadorRestRepository;

    /**
     * Metodo permite encontrar um utlizador atraves do codigo de utilizador, comunica com
     * o RestRepositorio
     * @param codUtilizador codUtilizador é o identificador ddo utilizador na BD, definido
     * como o código de utilizador
     * @return retorna um utilizadorRestDto
     */

    public Optional<UtilizadorRestDTO> findUtilizadorByCodUtilizador(int codUtilizador) {
        Optional<UtilizadorRestDTO> opUtilizador = utilizadorRestRepository.findUtilizadorByCodUtilizador(codUtilizador);

        if ( opUtilizador.isPresent() ) {
            return opUtilizador;
        }
        else
            return Optional.empty();
    }
}
