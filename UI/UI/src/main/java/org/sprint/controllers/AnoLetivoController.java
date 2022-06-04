package org.sprint.controllers;

import org.sprint.model.service.AnoLetivoService;
import org.sprint.model.service.UcService;

import java.util.ArrayList;
import java.util.List;

public class AnoLetivoController {
    AnoLetivoService anoLetivoService;

    public AnoLetivoController() {
        anoLetivoService=new AnoLetivoService();
    }

    public List<String> listaAnosGerada(){

        List<String> lista= new ArrayList<>();
        for( int ano=1950;ano<2051;ano++){
            lista.add(String.valueOf(ano));
        }
        return lista;
    }

    public String formatarAno(String ano){

        int anoIncial= Integer.parseInt(ano);
        String anoLetivo=String.format(anoIncial+"-"+(anoIncial+1));
        return anoLetivo;
    }


    public boolean criarNovoAnoLetivo(String ano){
        boolean create=anoLetivoService.criarNovoAnoLetivo(ano);
        return create;
    }
}
