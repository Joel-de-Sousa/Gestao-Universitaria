package Sprint.WsProjeto.datamodel.JPA.assembler;


import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.stereotype.Service;

@Service
public class ProjetoDomainDataAssembler {

    public ProjetoJPA toData (Projeto projeto){

        return new ProjetoJPA( projeto.getCodEstudante(), projeto.getCodOrientador(),projeto.getCodProposta());
    }

    public Projeto toDomain (ProjetoJPA projetoJPA){

        return new Projeto(projetoJPA.getCodProposta(), projetoJPA.getCodEstudante(), projetoJPA.getCodOrientador());
    }
}
