package wsproposta.proposta.repositories.iRepositories;

import wsproposta.proposta.domain.entities.Proposta;

import java.util.Optional;

public interface IPropostaRepository {

    public Proposta save (Proposta proposta);

    public Optional<Proposta> findById (int id);
}
