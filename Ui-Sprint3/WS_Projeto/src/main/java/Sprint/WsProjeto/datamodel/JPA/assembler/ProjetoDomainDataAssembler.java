package Sprint.WsProjeto.datamodel.JPA.assembler;


import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoDomainDataAssembler {

    AvaliacaoDomainDataAssembler avaliacaoDomainDataAssembler;

    public ProjetoJPA toData (Projeto projeto){



        return new ProjetoJPA(projeto.getCodProjeto(), projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador());

    }

    public Projeto toDomain (ProjetoJPA projetoJPA){

       /* List<Avaliacao> listAvaliacao= new ArrayList<>();
        for (AvaliacaoJPA avaliacaoJPA : projetoJPA.getListaAvaliacoes() ) {
            Avaliacao avaliacao=avaliacaoDomainDataAssembler.toDomain(avaliacaoJPA);
            listAvaliacao.add(avaliacao);

        }*/

        return new Projeto(projetoJPA.getCodProjeto(), projetoJPA.getCodProposta(), projetoJPA.getCodEstudante(), projetoJPA.getCodOrientador());
    }
}
