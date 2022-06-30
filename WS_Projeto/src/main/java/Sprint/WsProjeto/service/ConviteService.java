package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.DTO.assembler.ConviteDomainDTOAssembler;
import Sprint.WsProjeto.DTO.assembler.JuriDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.ProjetoNotExists;
import Sprint.WsProjeto.domain.entities.Convite;
import Sprint.WsProjeto.domain.entities.Juri;
import Sprint.WsProjeto.domain.entities.Projeto;
import Sprint.WsProjeto.domain.factories.IConviteFactory;
import Sprint.WsProjeto.domain.factories.IJuriFactory;
import Sprint.WsProjeto.repositories.ConviteRecusadoRepository;
import Sprint.WsProjeto.repositories.ConviteRepository;
import Sprint.WsProjeto.repositories.JPA.ProjetoJPARepository;
import Sprint.WsProjeto.repositories.JuriRepository;
import Sprint.WsProjeto.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class ConviteService {
    @Autowired
    ConviteDomainDTOAssembler conviteDomainDTOAssembler;

    @Autowired
    IConviteFactory conviteFactory;

    @Autowired
    ConviteRepository conviteRepository;

    @Autowired
    ConviteRecusadoRepository conviteRecusadoRepository;

    @Autowired
    ProjetoJPARepository projetoJPARepository;

    @Autowired
    ProjetoRepository projetoRepository;


    public ConviteService() {
    }

    public ConviteDTO createAndSaveConvite(NewConviteInfoDTO conviteInfoDto) throws ProjetoNotExists, SQLException {

        if(projetoJPARepository.existsById(conviteInfoDto.getCodProjeto())) {

            Convite convite = conviteFactory.createConvite(conviteInfoDto.getCodProjeto(), conviteInfoDto.getCodEstudante(), conviteInfoDto.getCodDocente());

            Convite oConviteSaved = conviteRepository.save(convite);

            ConviteDTO oConviteDTO = conviteDomainDTOAssembler.toDto(oConviteSaved);

            return oConviteDTO;
        }else

            throw new ProjetoNotExists("O Projeto a qual o convite foi enviado não existe");


    }



    public ConviteDTO updateEstadoConvite (ConvitePartialDTO conviteUpdate) throws Exception {

        if(conviteUpdate.getEstado().equals("ACEITE")) {
            Optional<Convite> opConvite = conviteRepository.findById(conviteUpdate.getCodConvite());

            opConvite.get().setCodConvite(conviteUpdate.getCodConvite());
            opConvite.get().setEstado(Convite.Estado.valueOf(conviteUpdate.getEstado()));

            Convite conviteSaved = conviteRepository.save(opConvite.get());
            ConviteDTO conviteSavedDTO = conviteDomainDTOAssembler.toDto(conviteSaved);

            Optional<Projeto> opProjeto = projetoRepository.findById(conviteSavedDTO.getCodProjeto());
            if (opProjeto.isPresent()) {
                opProjeto.get().setCodOrientador(conviteSavedDTO.getCodDocente());
                Projeto projetoSaved = projetoRepository.save(opProjeto.get());
            } else
                throw new ProjetoNotExists("O Projeto a qual o convite foi enviado não existe");

            return conviteSavedDTO;
        }
       else{
           if (conviteUpdate.getEstado().equals("REJEITADO")){
               Optional<Convite> opConvite = conviteRepository.findById(conviteUpdate.getCodConvite());

               opConvite.get().setCodConvite(conviteUpdate.getCodConvite());
               opConvite.get().setEstado(Convite.Estado.valueOf(conviteUpdate.getEstado()));

               Convite conviteSaved = conviteRepository.save(opConvite.get());
               Convite conviteSavedRecusado=conviteRecusadoRepository.save(conviteSaved);
               conviteRepository.deleteByCodConvite(conviteSaved.getCodConvite());
               ConviteDTO conviteSavedDTO = conviteDomainDTOAssembler.toDto(conviteSavedRecusado);
               return conviteSavedDTO;
           }
           throw new Exception("Não foi alterado o estado do Convite");
        }
    }

    public ConviteDTO findConviteByCode(int codConvite) throws Exception {

        Optional<Convite> opConvite = conviteRepository.findById(codConvite);

        if (opConvite.isPresent()) {
            Convite oConvite = opConvite.get();
            ConviteDTO oConviteDTO = conviteDomainDTOAssembler.toDto(oConvite);

            return oConviteDTO;
        } else throw new Exception("Convite não encontrado");
    }
}
