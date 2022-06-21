package org.sprint3.model.repository.REST;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.CandidaturaRestDTO;
import org.sprint3.model.DTO.PropostaRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class CandidaturaRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8090")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();

    public boolean createCandidatura (CandidaturaRestDTO novaCandidatura) throws Exception {

        ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/candidaturas")
                    .body(Mono.just(novaCandidatura), CandidaturaRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novaCandidatura.toString())))
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .block();
        }
        catch( Exception e) {

            System.out.println(e.getMessage());
        }

        if (result.getStatusCode().is2xxSuccessful())
            return true;
        else
            throw new Exception( result.getBody());
    }

    public Optional<List<CandidaturaRestDTO>> findAllCandidaturas() {

       /* CandidaturaRestDTO cand1 = new CandidaturaRestDTO(1,"Tecnologias Assistidas para Terapia da Fala", "Joel", "Sousa", "PENDENTE");
        CandidaturaRestDTO cand2 = new CandidaturaRestDTO(1,"Applicant Tracking System", "Joel", "Sousa", "PENDENTE");

        List<CandidaturaRestDTO> list = new ArrayList<>();
        list.add(cand1);
        list.add(cand2);
        return Optional.of(list);*/
        try {
            Mono<List<CandidaturaRestDTO>> response = webClient
                    .get()
                    .uri("/candidaturas/allArgs")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<CandidaturaRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<CandidaturaRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }

    }

    public Optional<CandidaturaRestDTO> findById(int codCandidatura) {

      /*  CandidaturaRestDTO c1  = new CandidaturaRestDTO(1, 1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Os processos de implantação de software tradicionais,baseados num modelo on-premises, com um conjunto de servidores mais ou menos estático.", "aferir este impacto foi desenvolvido um caso de estudo, inserido no contexto de uma loja e-Commerce de artigos de arte", "PDS 2022-2023", 1, "Joel", "Sousa", "PENDENTE");
        return Optional.of(c1);*/
        try {
            Mono<CandidaturaRestDTO> response = webClient
                    .get()
                    .uri("/candidaturas/allArgs/" + codCandidatura)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(CandidaturaRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            CandidaturaRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public Optional<CandidaturaRestDTO> findByCodEstudante(int codEstudante) {

        /*CandidaturaRestDTO c1  = new CandidaturaRestDTO(1, 1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Os processos de implantação de software tradicionais,baseados num modelo on-premises, com um conjunto de servidores mais ou menos estático.", "aferir este impacto foi desenvolvido um caso de estudo, inserido no contexto de uma loja e-Commerce de artigos de arte", "PDS 2022-2023", 1, "Joel", "Sousa", "PENDENTE");
        return Optional.of(c1);*/
        try {
            Mono<CandidaturaRestDTO> response = webClient
                    .get()
                    .uri("/candidaturas/estudante/" + codEstudante)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(CandidaturaRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            CandidaturaRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean updateEstadoCandidatura (CandidaturaRestDTO candidaturaParcial) throws Exception {


        ResponseEntity<String> result = null;
        int codCandidatura = candidaturaParcial.getCodCandidatura();
        try {
            result= webClient
                    .patch()
                    .uri("/candidaturas/estado/" + codCandidatura)
                    .body(Mono.just(candidaturaParcial), PropostaRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(candidaturaParcial.toString())))
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .block();
        }
        catch( Exception e) {

            System.out.println(e.getMessage());
        }

        if (result.getStatusCode().is2xxSuccessful())
            return true;
        else
            throw new Exception( result.getBody());
    }


}
