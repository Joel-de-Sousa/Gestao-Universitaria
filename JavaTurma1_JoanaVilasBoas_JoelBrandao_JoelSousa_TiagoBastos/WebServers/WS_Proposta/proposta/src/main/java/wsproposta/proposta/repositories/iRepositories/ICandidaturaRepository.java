package wsproposta.proposta.repositories.iRepositories;

import wsproposta.proposta.domain.entities.Candidatura;
import wsproposta.proposta.domain.entities.Proposta;

import java.util.List;
import java.util.Optional;

public interface ICandidaturaRepository {

    public Candidatura save (Candidatura candidatura);

    public Optional<Candidatura> findById (int id);

    public List<Candidatura> findAll();
}
