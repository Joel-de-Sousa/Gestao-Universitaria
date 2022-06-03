package org.sprint.model.repository.REST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.EdicaoRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.Optional;

public class EdicaoRestRepository {

    public Optional<EdicaoRestDTO> createEdicao (EdicaoRestDTO novaEdicao) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://193.136.62.227:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://193.136.62.227:8080"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        EdicaoRestDTO edicaoRestDTO;
        try {
            edicaoRestDTO = webClient
                    .post()
                    .uri("/edicao")
                    .body(Mono.just(novaEdicao), EdicaoRestDTO.class)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(EdicaoRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            edicaoRestDTO = null;
        }

        if( edicaoRestDTO != null )
            return Optional.of(edicaoRestDTO);
        else
            return Optional.empty();
    }

}
