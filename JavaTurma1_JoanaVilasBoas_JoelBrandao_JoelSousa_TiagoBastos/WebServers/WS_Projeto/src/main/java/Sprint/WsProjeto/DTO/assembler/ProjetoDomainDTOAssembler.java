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
        ArrayList<AvaliacaoDTO> list=new ArrayList<>();
        for (Avaliacao avaliacao: projeto.getListaAvaliacoes()) {

            AvaliacaoDTO avaliacaoDTO=avaliacaoDomainDTOAssembler.toDto(avaliacao);
            list.add(avaliacaoDTO);
        }

     return new ProjetoDTO(projeto.getCodProjeto(), projeto.getCodEstudante(), projeto.getCodOrientador(), projeto.getCodProposta(),list);
 }

}
