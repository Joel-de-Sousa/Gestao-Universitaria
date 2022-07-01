package Sprint.WsProjeto.service;

import Sprint.WsProjeto.DTO.SubmissaoDTO;
import Sprint.WsProjeto.DTO.assembler.SubmissaoDomainDTOAssembler;
import Sprint.WsProjeto.domain.entities.Submissao;
import Sprint.WsProjeto.domain.factories.SubmissaoFactory;
import Sprint.WsProjeto.repositories.SubmissaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class SubmissaoServiceTest {
    File file = new File("teste");

    @MockBean
    SubmissaoFactory submissaoFactory;

    @MockBean
    SubmissaoDomainDTOAssembler submissaoDomainDTOAssembler;

    @MockBean
    SubmissaoRepository submissaoRepository;

    @InjectMocks
    SubmissaoService submissaoService;

    @BeforeEach
    public void setUp()throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindSubmissaoByCode() throws IOException, SQLException {
        //assert
        Submissao submissao = mock(Submissao.class);
        Optional<Submissao> optionalSubmissao = Optional.of(submissao);
        when(submissaoRepository.findById(1)).thenReturn(optionalSubmissao);
        SubmissaoDTO submissaoDTO = mock(SubmissaoDTO.class);
        when(submissaoDomainDTOAssembler.toDto(optionalSubmissao.get())).thenReturn(submissaoDTO);


        //act
        SubmissaoDTO result = submissaoService.findSubmissaoBycode(1);

        //assert
        assertEquals(result,submissaoDTO);
    }
/*

    @Test
    void createAndSaveSubmissao() throws Exception {
        NewSubmissaoInfoDTO newSubmissaoInfoDTO = mock(NewSubmissaoInfoDTO.class);
        String path = "ficheiros/";  //Caminho para a pasta onde serão guardados os ficheiros
        String nomeFicheiro= nomeNovoFicheiro (path,"submissao-",".pdf"); //nome do novo ficheiro usando o metodo para incrementar
        String linguagem;

        File ficheiro = new File(nomeFicheiro);
        linguagem=linguagemFicheiro(ficheiro);
        Submissao submissao = mock(Submissao.class);

        when(submissaoFactory.createSubmissao(newSubmissaoInfoDTO.getTitulo(), ficheiro,linguagem)).thenReturn(submissao);
        Submissao submissaoSaved = mock(Submissao.class);
        when(submissaoRepository.save(submissao)).thenReturn(submissaoSaved);
        SubmissaoDTO submissaoDTO = mock(SubmissaoDTO.class);
        when(submissaoDomainDTOAssembler.toDto(submissaoSaved)).thenReturn(submissaoDTO);

        SubmissaoDTO  result = submissaoService.createAndSaveSubmissao(newSubmissaoInfoDTO);

        assertEquals(result,submissaoDTO);
    }

    private String linguagemFicheiro(File ficheiro) throws IOException, APIError {
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
*/


}