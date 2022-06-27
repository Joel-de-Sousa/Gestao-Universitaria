package org.sprint3.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.AnoLetivoRestDTO;
import org.sprint3.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AnoLetivoRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
            .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
            .build();


    public boolean createAnoLetivo (AnoLetivoRestDTO novaAno) throws Exception {

        ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/anoletivo")
                    .body(Mono.just(novaAno), UcRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novaAno.toString())))
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




/*

    public Optional<AnoLetivoRestDTO> createAnoLetivo (AnoLetivoRestDTO novoAno) {

        AnoLetivoRestDTO anoLetivoRestDTO;
        try {
            anoLetivoRestDTO = webClient
                    .post()
                    .uri("/anoletivo")
                    .body(Mono.just(novoAno), AnoLetivoRestDTO.class)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(AnoLetivoRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            anoLetivoRestDTO = null;
        }

        if( anoLetivoRestDTO != null )
            return Optional.of(anoLetivoRestDTO);
        else
            return Optional.empty();
    }*/
    public Optional<List<AnoLetivoRestDTO>> getAllAno () {

        try {
            Mono<List<AnoLetivoRestDTO>> response = webClient.get()
                    .uri("/anoletivo")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<AnoLetivoRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });
            List<AnoLetivoRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }

    }
    public Optional<AnoLetivoRestDTO> findAnoById(int id)
    {
        try {
            Mono<AnoLetivoRestDTO> response = webClient
                    .get()
                    .uri("/anoletivo/" + id) // idem configuração
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })
                    .bodyToMono(AnoLetivoRestDTO.class)
                    .onErrorReturn( null )
                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} );

            AnoLetivoRestDTO ano = response.block();

            return Optional.of(ano);
        }catch( Exception e) {
            return Optional.empty();
        }
    }

}
