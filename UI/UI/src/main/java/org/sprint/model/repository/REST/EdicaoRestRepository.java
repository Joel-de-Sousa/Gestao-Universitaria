package org.sprint.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint.model.DTO.AnoLetivoRestDTO;
import org.sprint.model.DTO.EdicaoRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
            .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
            .build();
    public Optional<EdicaoRestDTO> createUc (EdicaoRestDTO novaEdicao) {

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
    public Optional<List<EdicaoRestDTO>> getAllUc () {

        try {
            Mono<List<EdicaoRestDTO>> response = webClient.get()
                    .uri("/edicao")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<EdicaoRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<EdicaoRestDTO> tutorials = response.block();
            return Optional.of(tutorials);
        }catch( Exception e) {
            return Optional.empty();
        }

    }
    public Optional<UcRestDTO> findById(int id)
    {
        try {
            Mono<UcRestDTO> response = webClient
                    .get()
                    .uri("/uc/" + id) // idem configuração
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })
                    .bodyToMono(UcRestDTO.class)
                    .onErrorReturn( null )
                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} );

            UcRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        }catch( Exception e) {
            return Optional.empty();
        }
    }

}
