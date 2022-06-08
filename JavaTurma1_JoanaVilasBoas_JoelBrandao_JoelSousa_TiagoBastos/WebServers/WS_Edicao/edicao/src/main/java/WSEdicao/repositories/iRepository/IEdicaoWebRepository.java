package WSEdicao.repositories.iRepository;

import WSEdicao.domain.entities.Edicao;
import java.util.Optional;

public interface IEdicaoWebRepository {

    public Edicao save(Edicao edicao);

    public Optional<Edicao> findBycodEdicao(int codEdicao);
}
