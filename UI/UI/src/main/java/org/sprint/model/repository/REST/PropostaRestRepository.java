package org.sprint.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint.model.DTO.PropostaRestDTO;
import org.sprint.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRestRepository {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8090")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
            .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
            .build();




    public boolean createProposta (PropostaRestDTO novaProposta) throws Exception {

        ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/propostas")
                    .body(Mono.just(novaProposta), PropostaRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novaProposta.toString())))
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

    /*

    public Optional<PropostaRestDTO> createProposta (PropostaRestDTO novaProposta) {

        PropostaRestDTO propostaRestDTO;
        try {
            propostaRestDTO = webClient
                    .post()
                    .uri("/propostas")
                    .body(Mono.just(novaProposta), PropostaRestDTO.class)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(PropostaRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            propostaRestDTO = null;
        }

        if( propostaRestDTO != null )
            return Optional.of(propostaRestDTO);
        else
            return Optional.empty();
    }*/
    public Optional<List<PropostaRestDTO>> getAllPropostas () {

        try {
            Mono<List<PropostaRestDTO>> response = webClient.get()
                    .uri("/propostas")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<PropostaRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<PropostaRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }

    }
    public Optional<PropostaRestDTO> findPropostaById(int id)
    {
        try {
            Mono<PropostaRestDTO> response = webClient
                    .get()
                    .uri("/propostas/" + id) // idem configuração
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })
                    .bodyToMono(PropostaRestDTO.class)
                    .onErrorReturn( null )
                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} );

            PropostaRestDTO propostaRestDTO = response.block();

            return Optional.of(propostaRestDTO);
        }catch( Exception e) {
            return Optional.empty();
        }
    }

}
