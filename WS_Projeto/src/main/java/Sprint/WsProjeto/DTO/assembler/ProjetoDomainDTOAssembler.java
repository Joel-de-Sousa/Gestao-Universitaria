package Sprint.WsProjeto.DTO.assembler;

import Sprint.WsProjeto.DTO.ProjetoDTO;
import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.stereotype.Service;

@Service
public class ProjetoDomainDTOAssembler {

 public ProjetoDTO toDto(Projeto projeto){

     return new ProjetoDTO(projeto.getCodProjeto(), projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador());
 }

}
