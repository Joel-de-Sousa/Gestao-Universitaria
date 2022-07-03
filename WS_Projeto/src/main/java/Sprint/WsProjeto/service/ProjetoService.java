package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.AvaliacaoDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.ProjetoDomainDTOAssembler;
import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IProjetoFactory;
import Sprint.WsProjeto.repositories.EdicaoWebRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import Sprint.WsProjeto.repositories.PropostaWebRepository;
import Sprint.WsProjeto.repositories.UtilizadorWebRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProtocolException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoDomainDTOAssembler projetoDomainDTOAssembler;

    @Autowired
    IProjetoFactory projetoFactory;

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    UtilizadorWebRepository utilizadorWebRepository;

    @Autowired
    PropostaWebRepository propostaWebRepository;

    @Autowired
    EdicaoWebRepository edicaoWebRepository;

    @Autowired
    AvaliacaoService avaliacaoService;

    @Autowired
    AvaliacaoDomainDTOAssembler avaliacaoDomainDTOAssembler;


    public ProjetoService() {
    }

    public ProjetoDTO createAndSaveProjeto(NewProjetoInfoDto projetoInfoDto) throws Exception {


        Optional<PropostaRestDTO> propostaRestDTO = propostaWebRepository.findPropostaByCode(projetoInfoDto.getCodProposta());
        if (propostaRestDTO.isPresent()) {
            Projeto projeto = projetoFactory.createProjeto(projetoInfoDto.getCodProposta(), projetoInfoDto.getCodEstudante());

            Projeto oProjetoSaved = projetoRepository.save(projeto);


            Optional<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.findEdicaoByCode(propostaRestDTO.get().getCodEdicao());

            List<MomentoAvaliacaoDTO> momentoAvaliacaoList = edicaoRestDTO.get().getMomentoAvaliacaoList();
            for (MomentoAvaliacaoDTO momentoAvaliacaoDTO : momentoAvaliacaoList) {

                Avaliacao avaliacao = avaliacaoService.createAndSaveAvaliacao(momentoAvaliacaoDTO.getCodMomentoAvaliacao(), oProjetoSaved.getCodProjeto());

            }
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjetoSaved);

            return oProjetoDTO;
        } else
            throw new ProtocolException("A Proposta não existe");


    }

    public ProjetoDTO findProjetoByCode(int codProjeto) throws SQLException {

        Optional<Projeto> opProjeto = projetoRepository.findById(codProjeto);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }

    public ProjetoDTO findProjetoByCodeEstudante(int codEstudante) throws SQLException {

        Optional<Projeto> opProjeto = projetoRepository.findByCodEstudante(codEstudante);

        if (opProjeto.isPresent()) {
            Projeto oProjeto = opProjeto.get();
            ProjetoDTO oProjetoDTO = projetoDomainDTOAssembler.toDto(oProjeto);

            return oProjetoDTO;
        } else return null;
    }


    public Optional<UtilizadorRestDTO> findUtilizadorByCode(int codUtilizador) {

        Optional<UtilizadorRestDTO> oUtilizadorCode = utilizadorWebRepository.findUtilizadorByCode(codUtilizador);

        return oUtilizadorCode;
    }

    public Optional<PropostaRestDTO> findPropostaByCode(int codProposta) {

        Optional<PropostaRestDTO> oPropostaCode = propostaWebRepository.findPropostaByCode(codProposta);

        return oPropostaCode;
    }

    public List<ProjetoDTO> findProjetosPorCodigoRUC(int codRUC) throws Exception {
        List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(codRUC);
        if (!edicaoRestDTO.isEmpty()) {
            List<ProjetoDTO> listProjeto = projetoRepository.findProjetosPorCodigoRUC(codRUC);
            return listProjeto;
        } else
            throw new Exception("O Codigo introduzido não pertence a um RUC");
        }

        public List<ProjetoDTO> findProjetosConcluidos ( int codRuc) throws Exception {

            List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
            if (!edicaoRestDTO.isEmpty()) {
                List<Projeto> listProjetos = projetoRepository.findProjetosConcluidos();

                List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
                for (Projeto projeto : listProjetos) {
                    ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                    listProjetoDTO.add(projetoDTO);
                }
                return listProjetoDTO;
            } else
                throw new Exception("O Codigo introduzido não pertence a um RUC");

        }

        public List<ProjetoDTO> findProjetosByCodDocente ( int codRuc, int codDocente) throws Exception {
            List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
            if (!edicaoRestDTO.isEmpty()) {
                List<Projeto> listProjetos = projetoRepository.findProjetosByCodDocente(codDocente);

                List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
                for (Projeto projeto : listProjetos) {
                    ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                    listProjetoDTO.add(projetoDTO);
                }
                return listProjetoDTO;
            } else {
                throw new Exception("O Codigo introduzido não pertence a um RUC");
            }
        }



        public List<ProjetoDTO> findProjetosDatasAvaliacao ( int codRuc, int codMA, Date fromDate, Date toDate) throws
        Exception {
            List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
            if (!edicaoRestDTO.isEmpty()) {

                List<Projeto> listProjetos = projetoRepository.findProjetosDatasAvaliacao(codMA, fromDate, toDate);

                List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
                for (Projeto projeto : listProjetos) {
                    ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                    listProjetoDTO.add(projetoDTO);
                }
                return listProjetoDTO;
            } else
                throw new Exception("O Codigo introduzido não pertence a um RUC");
        }

        public List<ProjetoDTO> findProjetosByNifOrganizacao ( int codRUC, long nifOrganizacao) throws Exception {

            List<EdicaoRestDTO> edicaoRestDTO = edicaoWebRepository.getListaEdicoesByCodRUC(codRUC);
            if (!edicaoRestDTO.isEmpty()) {

                List<ProjetoDTO> listFiltradaProjetos = new ArrayList<>();

                List<PropostaRestDTO> listPropostas = propostaWebRepository.findAllPropostasAceitesByNif(nifOrganizacao);
                for (PropostaRestDTO proposta : listPropostas) {
                    Projeto projeto = projetoRepository.findProjetoByCodProposta(proposta.getCodProposta());
                    ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                    listFiltradaProjetos.add(projetoDTO);

                }
                return listFiltradaProjetos;
            } else throw new Exception("O Codigo introduzido não pertence a um RUC");

        }



    public List<ProjetoDTO> findProjetosComDeterminadoMACompleto(int codRuc,int codMA) throws Exception {

        List<EdicaoRestDTO> edicaoRestDTO=edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
        if(!edicaoRestDTO.isEmpty()) {
            List<Projeto> listProjetos = projetoRepository.findProjetosComDeterminadoMACompleto(codMA);

            List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
            for (Projeto projeto : listProjetos) {
                ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                listProjetoDTO.add(projetoDTO);
            }
            return listProjetoDTO;
        }
        else
            throw new Exception("O Codigo introduzido não pertence a um RUC");
    }
    



    public List<ProjetoDTO> findProjetosByNifOrganizacao(long nifOrganizacao) throws Exception {

        List<ProjetoDTO> listFiltradaProjetos = new ArrayList<>();

        List<PropostaRestDTO> listPropostas = propostaWebRepository.findAllPropostasAceitesByNif (nifOrganizacao);
        for (PropostaRestDTO proposta : listPropostas) {
            Projeto projeto = projetoRepository.findProjetoByCodProposta(proposta.getCodProposta());
            ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
            listFiltradaProjetos.add(projetoDTO);

        }return listFiltradaProjetos;

    }



    public List<ProjetoDTO> filtroOperadoresLogicos(int codRuc,String query) throws Exception {
        List<EdicaoRestDTO> edicaoRestDTO=edicaoWebRepository.getListaEdicoesByCodRUC(codRuc);
        if(!edicaoRestDTO.isEmpty()) {


            String jdbc = "SELECT DISTINCT * FROM projetos WHERE ";
            System.out.println(query);


            String nova = query.replaceAll("&", " AND ");
            nova = nova.replaceAll(";", " OR ");
            nova = nova.replaceAll("\\$", " NOT ");

            String[] list = query.split("[&;$]");
            for (String str : list) {
                String[] param = str.split("=");

                switch (param[0]) {
                    case "filtroRUC":
                        String formatF1 = String.format("cod_projeto in (select   avaliacoes.cod_projeto\n" +
                                "                        from ((avaliacoes\n" +
                                "                        inner join Projetos on avaliacoes.cod_projeto = projetos.cod_projeto)\n" +
                                "                        inner join Juris on avaliacoes.cod_juri = juris.cod_juri)\n" +
                                "                        where juris.cod_presidente =" + Integer.parseInt(param[1]) + "\n" +
                                "                        or juris.cod_orientador =" + Integer.parseInt(param[1]) + "\n" +
                                "                        or juris.cod_arguente = " + Integer.parseInt(param[1]) + ")");
                        nova = nova.replaceAll(str, formatF1);
                        System.out.println(param[1]);
                        break;
                    case "filtroDocente":
                        String formatF2 = String.format("cod_projeto in\n" +
                                "\t                    ( select avaliacoes.cod_projeto\n" +
                                "                        from (avaliacoes\n" +
                                "                        inner join Projetos on avaliacoes.cod_projeto = projetos.cod_projeto)\n" +
                                "                        where " + Integer.parseInt(param[1]) + " = AVALIACOES.cod_MA and AVALIACOES.estado = 1");
                        nova = nova.replaceAll(str, formatF2);
                        System.out.println(param[1]);
                        break;
                    case "filtroDatas":
                        String[] datas= param[1].split("to");
                        String formatF3 = String.format("cod_projeto in (select avaliacoes.cod_projeto\n" +
                                "                        from (avaliacoes\n" +
                                "                        inner join Projetos on avaliacoes.cod_projeto = projetos.cod_projeto)\n" +
                                "                        where p_cod_ma = AVALIACOES.cod_MA AND avaliacoes.data_avaliacao BETWEEN "+datas[0]+" and "+datas[1]+")");
                        nova = nova.replaceAll(str, formatF3);
                        System.out.println(param[1]);
                        System.out.println(datas[0]);
                        System.out.println(datas[1]);
                        break;

                }

                System.out.println("######################################");

            }
            System.out.println(list[0]);
            String SQL = String.format(jdbc + nova + ")");
            System.out.println(SQL);


            List<Projeto> listProjetos = projetoRepository.listaFiltrada(SQL);

            List<ProjetoDTO> listProjetoDTO = new ArrayList<>();
            for (Projeto projeto : listProjetos) {
                ProjetoDTO projetoDTO = projetoDomainDTOAssembler.toDto(projeto);
                listProjetoDTO.add(projetoDTO);
            }
            return listProjetoDTO;}
        else{
            throw new Exception("O Codigo introduzido não pertence a um RUC");}
    }



    /*public void updateEstado(Projeto.Estado estado, int codProjeto) throws Exception {

        Optional<Projeto> opProjeto = projetoRepository.findById(codProjeto);

        if (opProjeto.isPresent()) {
            if (!opProjeto.get().getEstado().equals(Projeto.Estado.CONCLUIDO)) {

                opProjeto.get().setCodProjeto(codProjeto);
                opProjeto.get().setEstado(estado);

                Projeto projetoUpdated = projetoRepository.update(opProjeto.get());
                ProjetoDTO projetoUpdatedDTO = projetoDomainDTOAssembler.toDto(projetoUpdated);


            }
            throw new Exception("O projeto já está com estado concluído");
        }
        throw new Exception("O projeto não consta na base de dados");
    }*/

    }