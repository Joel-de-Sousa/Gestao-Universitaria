package Sprint.WsProjeto.DTO.assembler;


import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.service.SubmissaoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SubmissaoDomainDTOAssembler {


    public SubmissaoDomainDTOAssembler() {
    }

    public SubmissaoDTO toDto(Submissao submissao) throws IOException {

        Path pdfPath = Paths.get(submissao.getFile().getPath());// definir o caminho onde está o ficheiro
        byte[] pdf = Files.readAllBytes(pdfPath); //cria o vetor de bytes com o conteudo do ficheiro e toda a sua estrutura
       /* System.out.println(Arrays.toString(pdf));

        File file= submissao.getFile();

        FileInputStream ficheiro = new FileInputStream(file);

        byte[] ficheiroArray = new byte[(int)file.length()];
        ficheiro.read(ficheiroArray);*/

     return new SubmissaoDTO(submissao.getCodSubmissao(),submissao.getTitulo(),pdf,submissao.getLinguagemFile());
 }








}
