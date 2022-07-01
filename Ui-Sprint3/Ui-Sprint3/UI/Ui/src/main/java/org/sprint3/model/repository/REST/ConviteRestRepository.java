package org.sprint3.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.ConviteRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ConviteRestRepository {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8085")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8085"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();

    public boolean createConvite (ConviteRestDTO novoConvite) throws Exception {

        ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/convites")
                    .body(Mono.just(novoConvite), ConviteRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novoConvite.toString())))
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .block();
            System.out.println(result);
        }
        catch( Exception e) {

            System.out.println(e.getMessage());
        }

        if (result.getStatusCode().is2xxSuccessful())
            return true;
        else
            throw new Exception( result.getBody());
    }


    public Optional<ConviteRestDTO> getListaConvitesByCodDocente (int codDocente) {

        try {
            Mono<ConviteRestDTO> response = webClient
                    .get()
                    .uri("/convites/codDocente/" + codDocente)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ConviteRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            ConviteRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public Optional<ConviteRestDTO> findConviteByCodConvite (int codConvite) {

       try {
            Mono<ConviteRestDTO> response = webClient
                    .get()
                    .uri("/convites/" + codConvite)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ConviteRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            ConviteRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }

    }


    public boolean updateEstadoConvite (ConviteRestDTO conviteParcial) throws Exception {


        ResponseEntity<String> result = null;
        int codConvite = conviteParcial.getCodConvite();
        try {
            result= webClient
                    .patch()
                    .uri("/convites/estado/" + codConvite)
                    .body(Mono.just(conviteParcial), ConviteRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(conviteParcial.toString())))
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    })
                    .block();
            System.out.println(result);
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
