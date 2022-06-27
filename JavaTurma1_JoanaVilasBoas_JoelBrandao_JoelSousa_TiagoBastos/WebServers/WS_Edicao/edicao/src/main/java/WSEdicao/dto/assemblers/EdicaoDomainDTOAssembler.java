package WSEdicao.dto.assemblers;

import WSEdicao.datamodel.EstudanteJpa;
import WSEdicao.domain.entities.Edicao;
import WSEdicao.domain.entities.MomentoAvaliacao;
import WSEdicao.dto.*;
import WSEdicao.repositories.AnoLetivoRepository;
import WSEdicao.repositories.UcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EdicaoDomainDTOAssembler {

    @Autowired
    UcRepository ucRepository;

    @Autowired
    AnoLetivoRepository anoLetivoRepository;


    private EdicaoDomainDTOAssembler() {
    }

    public EdicaoDTO toDTO(Edicao edicao) {
        return new EdicaoDTO(edicao.getCodEdicao(),edicao.getUc(),edicao.getAnoLetivo(), edicao.getCodRUC(), edicao.getEstado().toString());
    }

    public EdicaoAllArgsDTO toDTOAllArgs(Edicao edicao){
        Optional<UcDTO> ucDTO= ucRepository.findBycodUc(edicao.getUc());
        Optional<AnoLetivoDTO> anoLetivoDTO= anoLetivoRepository.findBycodAnoLetivo(edicao.getAnoLetivo());

        ArrayList<MomentoAvaliacaoDTO> listMA = new ArrayList<>();
        for (MomentoAvaliacao ma: edicao.getMomentoAvaliacaoList()){
            MomentoAvaliacaoDTO momentoAvaliacaoDTO = new MomentoAvaliacaoDTO(ma.getCodMomentoAvaliacao(), ma.getDenominacao());
            listMA.add(momentoAvaliacaoDTO);
        }

        ArrayList<AddStudentDTO> listEstudante = new ArrayList<>();
        for (EstudanteJpa estudante: edicao.getEstudantesList()){
            AddStudentDTO estudanteDTO = new AddStudentDTO(
                    estudante.getCodEstudante(),
                    estudante.getCodUtilizador(),
                    estudante.getCodEdicao());
            listEstudante.add(estudanteDTO);
        }


        return new EdicaoAllArgsDTO(edicao.getCodEdicao(),
                ucDTO.get().getCodUc(),
                ucDTO.get().getSigla(),
                ucDTO.get().getDenominacao(),
                anoLetivoDTO.get().getCodAnoLetivo(),
                anoLetivoDTO.get().getAno(),
                edicao.getCodRUC(),
                edicao.getEstado().toString(),
                listMA,
                listEstudante);
    }
}
