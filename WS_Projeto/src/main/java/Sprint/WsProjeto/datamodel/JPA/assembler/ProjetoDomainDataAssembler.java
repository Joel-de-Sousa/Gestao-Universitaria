package Sprint.WsProjeto.datamodel.JPA.assembler;


import Sprint.WsProjeto.datamodel.JPA.AvaliacaoJPA;
import Sprint.WsProjeto.datamodel.JPA.JuriJPA;
import Sprint.WsProjeto.datamodel.JPA.ProjetoJPA;
import Sprint.WsProjeto.datamodel.JPA.SubmissaoJPA;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjetoDomainDataAssembler {
    @Autowired
    AvaliacaoRepository avaliacaoRepository;
    @Autowired
    AvaliacaoDomainDataAssembler avaliacaoDomainDataAssembler;

    public ProjetoJPA toData (Projeto projeto){
        if(projeto.getListaAvaliacoes()==null){
           return new ProjetoJPA(projeto.getCodProjeto(),projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador());
        }
        List<AvaliacaoJPA> list=new ArrayList<>();
        for (Avaliacao avaliacao: projeto.getListaAvaliacoes()) {
            Avaliacao avaliacaoSaved =avaliacaoRepository.save(avaliacao);
            AvaliacaoJPA avaliacaoJPA=avaliacaoDomainDataAssembler.toData(avaliacaoSaved);
            list.add(avaliacaoJPA);
        }
        ProjetoJPA projetoJPA = new ProjetoJPA(projeto.getCodProjeto(),projeto.getCodProposta(), projeto.getCodEstudante(), projeto.getCodOrientador());
        projetoJPA.setListaAvaliacoes(list);

        return projetoJPA;

    }

    public Projeto toDomain (ProjetoJPA projetoJPA){

        List<Avaliacao> listAvaliacao= new ArrayList<>();
        for (AvaliacaoJPA avaliacaoJPA : projetoJPA.getListaAvaliacoes() ) {
            Avaliacao avaliacao=avaliacaoDomainDataAssembler.toDomain(avaliacaoJPA);
            listAvaliacao.add(avaliacao);

        }

        return new Projeto(projetoJPA.getCodProjeto(),projetoJPA.getCodProposta(),  projetoJPA.getCodEstudante(), projetoJPA.getCodOrientador(),listAvaliacao);
    }
}
