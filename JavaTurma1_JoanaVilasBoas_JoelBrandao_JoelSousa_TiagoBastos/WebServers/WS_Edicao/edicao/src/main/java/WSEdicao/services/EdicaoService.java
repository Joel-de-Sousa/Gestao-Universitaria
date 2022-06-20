package WSEdicao.services;

import WSEdicao.datamodel.EdicaoJpa;
import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.datamodel.REST.UtilizadorRestDTO;
import WSEdicao.datamodel.assemblers.EdicaoDomainDataAssembler;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.domain.factories.IEdicaoFactory;
import WSEdicao.dto.*;
import WSEdicao.dto.assemblers.AnoLetivoDomainDTOAssembler;
import WSEdicao.dto.assemblers.EdicaoDomainDTOAssembler;
import WSEdicao.dto.assemblers.UcDomainDTOAssembler;
import WSEdicao.repositories.*;
import WSEdicao.repositories.REST.UtilizadorRestRepository;
import WSEdicao.repositories.jpa.EdicaoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EdicaoService {

    @Autowired
    IEdicaoFactory edicaoFactory;

    @Autowired
    EdicaoRepository edicaoRepository;

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;

    @Autowired
    UcDomainDTOAssembler ucDTOAssember;

    @Autowired
    AnoLetivoDomainDTOAssembler anoLetivoDTOAssembler;

    @Autowired
    EdicaoDomainDTOAssembler edicaoDTOAssembler;

    @Autowired
    MomentoAvaliacaoService momentoAvaliacaoService;

    @Autowired
    MomentoAvaliacaoRepository momentoAvaliacaoRepository;

    @Autowired
    EdicaoJpaRepository edicaoJpaRepository;

    @Autowired
    EdicaoDomainDataAssembler edicaoDomainDataAssembler;

    @Autowired
    UtilizadorRestRepository utilizadorRestRepository;

    public EdicaoService() {
    }

    public EdicaoDTO createAndSaveEdicao(int codUc, int codAnoLetivo, int codRUC) throws Exception {

        Optional<UcDTO> optionalUc = ucRepository.findBycodUc(codUc);
        Optional<AnoLetivoDTO> optionalAnoLetivo = anoLetivoRepository.findBycodAnoLetivo(codAnoLetivo);

        if (optionalUc.isPresent() && optionalAnoLetivo.isPresent()) {

            Edicao edicao = edicaoFactory.createEdicao(codUc, codAnoLetivo, codRUC);
            Edicao savedEdicao = edicaoRepository.save(edicao);
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(savedEdicao);

            return edicaoDTO;
        } else
            throw new IllegalArgumentException();
    }

    public Optional<EdicaoDTO> getEdicaoByCode(int codEdicao) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);
        EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(opEdicao.get());

        return Optional.of(edicaoDTO);
    }

    public List<EdicaoDTO> getAllEdicao() {
        List<Edicao> listEdicao = edicaoRepository.findAll();

        List<EdicaoDTO> listaDto = new ArrayList<>();
        for (Edicao edicao : listEdicao) {
            EdicaoDTO edicaoDTO = edicaoDTOAssembler.toDTO(edicao);
            listaDto.add(edicaoDTO);
        }
        return listaDto;
    }

    public List<EdicaoAllArgsDTO> getEdicaoAllArgs() {
        List<Edicao> listEdicao = edicaoRepository.findAll();

        List<EdicaoAllArgsDTO> listaDto = new ArrayList<>();
        for (Edicao edicao : listEdicao) {

            EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(edicao);
            listaDto.add(edicaoAllArgsDTO);
        }
        return listaDto;
    }

    public Optional<EdicaoAllArgsDTO> getEdicaoAllArgsByCode(int codEdicao) {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(codEdicao);
        EdicaoAllArgsDTO edicaoAllArgsDTO = edicaoDTOAssembler.toDTOAllArgs(opEdicao.get());

        return Optional.of(edicaoAllArgsDTO);
    }

    //MÉTODO PATCH ESTADO EDICAO
    public EdicaoDTO updateEstadoEdicao(EdicaoDTOParcial edicaoUpdate) throws Exception {

        Optional<Edicao> opEdicao = edicaoRepository.findBycodEdicao(edicaoUpdate.getCodEdicao());

        if(!(Objects.equals(edicaoUpdate.getEstado(), opEdicao.get().getEstado().toString()))) {
            opEdicao.get().setEstado(Edicao.Estado.valueOf(edicaoUpdate.getEstado()));

        } else
            throw new IllegalArgumentException("A proposta já se encontra " + opEdicao.get().getEstado().toString());

        Edicao edicaoSaved = edicaoRepository.saveWithoutValidation(opEdicao.get());
        EdicaoDTO edicaoSavedDTO = edicaoDTOAssembler.toDTO(edicaoSaved);

        return edicaoSavedDTO;
    }

    public EdicaoDTO addEstudantes(AddStudentDTO addStudent) throws Exception {

        EdicaoJpa opEdicao = edicaoRepository.findBycodEdicaoJpa(addStudent.getCodEdicao());
        EstudanteJpa estudanteJpa = new EstudanteJpa(addStudent.getCodEdicao(),addStudent.getCodEstudante());

        Optional<UtilizadorRestDTO> utilizadorRestDTO =
                utilizadorRestRepository.findUtilizadorByCodUtilizador(addStudent.getCodEstudante());

        if(!opEdicao.getListEstudantes().contains(estudanteJpa) && utilizadorRestDTO.get().getTipoUtilizador() == "ESTUDANTE") {

            opEdicao.getListEstudantes().add(estudanteJpa);

        } else
            throw new IllegalArgumentException(" O estudante já se encontra inscrito na Edição");
        Edicao edicao = edicaoDomainDataAssembler.toDomain(opEdicao);
        Edicao edicaoSaved = edicaoRepository.saveWithoutValidation(edicao);
        EdicaoDTO edicaoSavedDTO = edicaoDTOAssembler.toDTO(edicaoSaved);

        return edicaoSavedDTO;
    }

    public List<AddStudentDTO> getAllEstudantesByCodEdicao(int codEdicao) {

        return edicaoRepository.findEstudantesByCodEdicao(codEdicao);
    }

}



