package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.JuriDTO;
import Sprint.WsProjeto.DTO.NewJuriInfoDTO;

import Sprint.WsProjeto.service.JuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RestController
@RequestMapping(path = "/juris")
public class JuriController {

        @Autowired
        private JuriService juriService;

        public JuriController(JuriService juriService) {
            this.juriService = juriService;
        }

        @GetMapping("/{codJuri}")
        @ResponseBody
        public ResponseEntity<Object> findJuriByCode(@PathVariable int codJuri) throws SQLException {

          JuriDTO oJuri = juriService.findJuriByCode(codJuri);


            if (oJuri!=null) {
                return new ResponseEntity<>(oJuri, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        }


        @PostMapping("")
        @ResponseBody
        public ResponseEntity<Object> createAndSaveJuri(@RequestBody NewJuriInfoDTO juriInfoDto) {

            try {
                JuriDTO juriDTO = juriService.createAndSaveJuri(juriInfoDto);
                return new ResponseEntity<>(juriDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    }






