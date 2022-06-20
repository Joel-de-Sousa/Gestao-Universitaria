package Sprint.WsProjeto.controller;

import Sprint.WsProjeto.DTO.*;
import Sprint.WsProjeto.service.ConviteService;
import Sprint.WsProjeto.service.JuriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(path = "/convites")
public class ConviteController {

        @Autowired
        private ConviteService conviteService;

        public ConviteController(ConviteService conviteService) {
            this.conviteService = conviteService;
        }

      /*  @GetMapping("/{codDocente}")
        @ResponseBody
        public ResponseEntity<Object> findJuriByCode(@PathVariable int codJuri) {

          JuriDTO oJuri = juriService.findJuriByCode(codJuri);


            if (oJuri!=null) {
                return new ResponseEntity<>(oJuri, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        }*/


        @PostMapping("")
        @ResponseBody
        public ResponseEntity<Object> createAndSaveConvite(@RequestBody NewConviteInfoDTO conviteInfoDto) {

            try {
                ConviteDTO conviteDTO = conviteService.createAndSaveConvite(conviteInfoDto);
                return new ResponseEntity<>(conviteDTO, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

    @PatchMapping("/estado/{codConvite}")
    public ResponseEntity<Object> partialUpdateEstadoConviteOrientador (@RequestBody ConvitePartialDTO conviteUpdate, @PathVariable int codConvite) throws Exception {

        ConviteDTO updatedConvite = conviteService.updateEstadoConvite (conviteUpdate);
        return new ResponseEntity<>(updatedConvite, HttpStatus.OK);
    }

    }






