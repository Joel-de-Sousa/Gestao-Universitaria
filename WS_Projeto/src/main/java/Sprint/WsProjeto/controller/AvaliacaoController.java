package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.AvaliacaoDTO;
import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewAvaliacaoInfoDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;
import Sprint.WsProjeto.domain.entities.Avaliacao;
import Sprint.WsProjeto.service.AvaliacaoService;
import Sprint.WsProjeto.service.JuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/avaliacoes")
public class AvaliacaoController {

        @Autowired
        private AvaliacaoService avaliacaoService;

        public AvaliacaoController(AvaliacaoService avaliacaoService) {
            this.avaliacaoService = avaliacaoService;
        }

        @GetMapping("/{codAvaliacao}")
        @ResponseBody
        //public ResponseEntity<Object> findAvalicaoByCode(@PathVariable int codAvaliacao) {
            public ResponseEntity<Object> findAvalicaoByCode(@RequestParam int codAvaliacao) {

          AvaliacaoDTO oAvaliacao = avaliacaoService.findAvaliacaoByCode(codAvaliacao);


            if (oAvaliacao!=null) {
                return new ResponseEntity<>(oAvaliacao, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        }


      /*  @PostMapping("")
        @ResponseBody
        public ResponseEntity<Object> createAndSaveAvaliacao(@RequestBody NewAvaliacaoInfoDTO avaliacaoInfoDto) {

            try {
                AvaliacaoDTO avaliacaoDTO = avaliacaoService.createAndSaveAvaliacao(avaliacaoInfoDto.getCodMA());
                return new ResponseEntity<>(avaliacaoDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }*/
    }






