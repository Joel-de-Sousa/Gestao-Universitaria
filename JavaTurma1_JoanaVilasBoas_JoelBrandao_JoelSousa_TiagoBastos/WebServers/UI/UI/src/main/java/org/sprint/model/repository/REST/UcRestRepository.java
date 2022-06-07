package org.sprint.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import org.sprint.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UcRestRepository {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();
  /*  public Optional<UcRestDTO> createUc (UcRestDTO novaUc) {

        UcRestDTO uCRestDTO;
        try {
            uCRestDTO = webClient
                    .post()
                    .uri("/uc")
                    .body(Mono.just(novaUc), UcRestDTO.class)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(UcRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            uCRestDTO = null;
        }


        if( uCRestDTO != null )
            return Optional.of(uCRestDTO);
        else
            return Optional.empty();
    }
    */


    public boolean createUc (UcRestDTO novaUc) throws Exception {

        ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/uc")
                    .body(Mono.just(novaUc), UcRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novaUc.toString())))
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


    public Optional<List<UcRestDTO>> getAllUc() {

        try {
            Mono<List<UcRestDTO>> response = webClient.get()
                    .uri("/uc")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<UcRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<UcRestDTO> tutorials = response.block();
            return Optional.of(tutorials);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public Optional<UcRestDTO> findById(int id) {
        try {
            Mono<UcRestDTO> response = webClient
                    .get()
                    .uri("/uc/" + id) // idem configuração
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(UcRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            UcRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }


}



/*
    public Optional<List<UcRestDTO>> getAllUc () {


        try {
            Mono<List<UcRestDTO>> response = webClient
                    .get()
                    .uri("/uc")
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(new ParameterizedTypeReference<List<UcRestDTO>>() {
                    })

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );
                    });
            List<UcRestDTO> listRestDto = response.block();
            return Optional.of(listRestDto);
        }
        catch( Exception e) {


            return Optional.empty();
        }*/



