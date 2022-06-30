package Sprint.WsProjeto.DTO.assembler;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoDomainDTOAssembler {

    @Autowired
    AvaliacaoDomainDTOAssembler avaliacaoDomainDTOAssembler;

    public ProjetoDomainDTOAssembler() {
    }

    public ProjetoDTO toDto(Projeto projeto){


     return new ProjetoDTO(projeto.getCodProjeto(), projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador(),projeto.getEstado().name());
 }

}
