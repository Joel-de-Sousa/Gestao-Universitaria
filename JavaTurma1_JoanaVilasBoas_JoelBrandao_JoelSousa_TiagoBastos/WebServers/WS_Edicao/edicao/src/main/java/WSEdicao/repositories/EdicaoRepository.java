package WSEdicao.repositories;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.dto.AddStudentDTO;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import WSEdicao.repositories.jpa.EstudanteJpaRepository;
import WSEdicao.repositories.jpa.MomentoAvaliacaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRepository {

    @Autowired
    EdicaoJpaRepository edicaoJpaRepository;
    @Autowired
    EdicaoDomainDataAssembler edicaoAssembler;

   @Autowired
   EstudanteJpaRepository estudanteJpaRepository;

    public Edicao save(Edicao edicao) throws Exception {
        EdicaoJpa edicaoJpa1 = edicaoAssembler.toData(edicao);
        if (!(edicaoJpaRepository.existsByCodUc(edicaoJpa1.getCodUc()) && edicaoJpaRepository.existsByCodAnoLetivo(edicaoJpa1.getCodAnoLetivo()))) {
            EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

            EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save(edicaoJpa);
            Edicao savedEdicao = edicaoAssembler.toDomain(savedEdicaoJpa);

            return savedEdicao;
        } else
            throw new Exception("Ja existe uma edição com a unidade curricular e ano letivo selecionados!");
    }

    public Edicao saveWithoutValidation(Edicao edicao) throws Exception {


        EdicaoJpa edicaoJpa = edicaoAssembler.toData(edicao);

        EdicaoJpa savedEdicaoJpa = edicaoJpaRepository.save(edicaoJpa);

        return edicaoAssembler.toDomain(savedEdicaoJpa);
    }

    public Optional<Edicao> findBycodEdicao(int codEdicao) {
        Optional<EdicaoJpa> opEdicao = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if (opEdicao.isPresent()) {
            Edicao edicao = edicaoAssembler.toDomain(opEdicao.get());
            return Optional.of(edicao);
        } else
            return Optional.empty();
    }

    public EdicaoJpa findBycodEdicaoJpa(int codEdicao) {
        Optional<EdicaoJpa> opEdicao = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if (opEdicao.isPresent()) {
            return opEdicao.get();
        } else
            return null;
    }

    public List<Edicao> findAll() {
        List<EdicaoJpa> setEdicaoJpa = edicaoJpaRepository.findAll();

        List<Edicao> setEdicao = new ArrayList<Edicao>();
        for (EdicaoJpa edicaoJpa : setEdicaoJpa) {
            Edicao edicao = edicaoAssembler.toDomain(edicaoJpa);
            setEdicao.add(edicao);
        }
        return setEdicao;
    }

    public List<AddStudentDTO> findEstudantesByCodEdicao(int codEdicao) {
        Optional<EdicaoJpa> opEdicaoJpa = edicaoJpaRepository.findBycodEdicao(codEdicao);

        if (opEdicaoJpa.isPresent()) {
            EdicaoJpa edicaoJpa = opEdicaoJpa.get();

            List<AddStudentDTO> listEstudante = new ArrayList<>();
            for (EstudanteJpa estudante : edicaoJpa.getListEstudantes()) {
                AddStudentDTO estudanteDTO = new AddStudentDTO(estudante.getCodEdicao(), estudante.getCodEstudante());
                listEstudante.add(estudanteDTO);
            }
            return listEstudante;
        } else
            return null; // it should throw a descriptive exception
    }

    public List<Edicao> findListEdicaoBycodRUC(int codRUC) {
        List<EdicaoJpa> listEdicaoJPA = edicaoJpaRepository.findListEdicaoBycodRUC(codRUC);
        List<Edicao> listEdicao = new ArrayList<>();
        for (EdicaoJpa edicaoJpa : listEdicaoJPA) {
            listEdicao.add(edicaoAssembler.toDomain(edicaoJpa));
        }
        return listEdicao;
    }

    public List<EdicaoDTO> findEdicaoByCodEstudante(int codEstudante) {

        List<EstudanteJpa> estudanteJpaList = estudanteJpaRepository.findListEstudanteByCodEstudante(codEstudante);
        //List<Integer> codEdicao = estudanteJpaList.stream().map(EstudanteJpa::getCodEdicao).collect(Collectors.toList());

        //List<EdicaoJpa> listEdicaoJPA = edicaoJpaRepository.findListEdicaoBycodEdicao(codEstudante);



        List<EdicaoDTO> listEdicaoDTO = new ArrayList<>();

        for (EstudanteJpa estudanteJpa : estudanteJpaList) {

            EdicaoDTO edicaoDTO = new EdicaoDTO(estudanteJpa.getCodEdicao());
            listEdicaoDTO.add(edicaoDTO);
        }
        return listEdicaoDTO;
    }
}
