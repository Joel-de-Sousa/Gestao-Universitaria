package org.sprint.model.repository.REST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.Optional;

public class UcRestRepository {

    public Optional<UcRestDTO> createUc (UcRestDTO novaUc) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://193.136.62.227:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://193.136.62.227:8080"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

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
}
