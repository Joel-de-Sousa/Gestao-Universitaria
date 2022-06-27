package WSEdicao.repositories;

import WSEdicao.datamodel.REST.UtilizadorRestDTO;
import WSEdicao.repositories.REST.UtilizadorRestRepository;
import WSEdicao.repositories.iRepository.IUtilizadorWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UtilizadorWebRepository implements IUtilizadorWebRepository {

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
