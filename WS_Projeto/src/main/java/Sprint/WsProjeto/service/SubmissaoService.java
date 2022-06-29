package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.NewSubmissaoInfoDTO;
import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.DTO.assembler.SubmissaoDomainDTOAssembler;
import Sprint.WsProjeto.Exceptions.IncorrectFileFormat;
import Sprint.WsProjeto.repositories.SubmissaoRepository;
import com.detectlanguage.DetectLanguage;
import com.detectlanguage.errors.APIError;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.domain.factories.ISubmissaoFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@Service
public class SubmissaoService {


    @Autowired
    SubmissaoDomainDTOAssembler submissaoDomainDTOAssembler;

    @Autowired
    ISubmissaoFactory submissaoFactory;

    @Autowired
    SubmissaoRepository submissaoRepository;

    public SubmissaoDTO findSubmissaoBycode(int codSubmissao) throws IOException {

        Optional<Submissao> opSubmissao = submissaoRepository.findById(codSubmissao);

        if (opSubmissao.isPresent()) {
            Submissao submissao = opSubmissao.get();
            SubmissaoDTO submissaoDTO = submissaoDomainDTOAssembler.toDto(submissao);

            return submissaoDTO;
        } else return null;
    }




    public SubmissaoDTO createAndSaveSubmissao(NewSubmissaoInfoDTO submissaoInfoDto) throws Exception {
        String path = "ficheiros/";  //Caminho para a pasta onde serão guardados os ficheiros
        String nomeFicheiro=nomeNovoFicheiro(path,"submissao-",".pdf"); //nome do novo ficheiro usando o metodo para incrementar
        String linguagem;


        try {

            if (verificarPDF(submissaoInfoDto.getFicheiro())) {
                guardarFicheiroNoDiretorio(submissaoInfoDto.getFicheiro(),nomeFicheiro);
                File ficheiro= new File(nomeFicheiro);
                linguagem=linguagemFicheiro(ficheiro);


                Submissao submissao = submissaoFactory.createSubmissao(submissaoInfoDto.getTitulo(),ficheiro,linguagem);
                Submissao oSubmissaoSaved = submissaoRepository.save(submissao);
                SubmissaoDTO oSubmissaoDTO = submissaoDomainDTOAssembler.toDto(oSubmissaoSaved);
                return oSubmissaoDTO;
            } else
                throw new IncorrectFileFormat("Ficheiro não está em formato PDF");


        } catch (IOException a) {
            throw new IOException("Não foi possivel guardar o ficheiro");
        } catch (IncorrectFileFormat e) {
            throw new IncorrectFileFormat(e.getMessage());
        }



    }

    public boolean verificarPDF(byte[] ficheiro) {
        return ficheiro[0] == 0x25 && ficheiro[1] == 0x50 && ficheiro[2] == 0x44 && ficheiro[3] == 0x46;   //%PDF magicNumber

    }

    public void guardarFicheiroNoDiretorio(byte[] ficheiro, String nome) throws IOException {

        OutputStream out = new FileOutputStream(nome);
        out.write(ficheiro);
        out.close();

    }


    public static String nomeNovoFicheiro(String path,String nome,String tipoFicheiro) {
        String[] pathnames;
        File f = new File(path);
        int max = 0;
        int valor;
        String[] split;
        String[] segundoSplit;

        pathnames = f.list();                                      // cria uma lista de nomes de ficheiros
        for (String pathname : pathnames) {

            split = pathname.split("-");                   // faz o split pelo - do nome
            segundoSplit = split[1].split("\\.");         // faz o split que separa o numero do ".pdf"
            valor = Integer.parseInt(segundoSplit[0]);         // inteiro do valor lido
            if (max < valor) {
                max = valor;                                 //verificar o maximo
            }
        }
        int incremental= max + 1;
        String nomeFicheiro=String.format(path+nome+incremental+tipoFicheiro);


        return nomeFicheiro;
    }

    public String linguagemFicheiro(File file) throws IOException, APIError {
        DetectLanguage.apiKey = "1a62c63100c888919b120a87a99fe8ec";


        PDDocument document = PDDocument.load(file);
        //Instantiate PDFTextStripper class
        PDFTextStripper pdfStripper = new PDFTextStripper();
        //Retrieving text from PDF document
        pdfStripper.setParagraphStart("/t");

        pdfStripper.setSortByPosition(true);
        String texto = pdfStripper.getText(document);
        String paragrafo = "";
        for (String line : texto.split(pdfStripper.getParagraphStart())) {
            System.out.println(line);
            System.out.println("----------------------------------------------------------------");
            if (line.contains(", ")) {
                paragrafo = line;
                break;
            }
        }
        String language= DetectLanguage.simpleDetect(paragrafo);
        return  language;
    }
}
