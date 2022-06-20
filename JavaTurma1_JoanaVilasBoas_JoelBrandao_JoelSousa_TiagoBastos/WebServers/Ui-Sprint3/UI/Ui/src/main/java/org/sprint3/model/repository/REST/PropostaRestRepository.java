package org.sprint3.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.PropostaRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class PropostaRestRepository {
    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8090")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();
    public Optional<List<PropostaRestDTO>> findAllPropostas() {

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


    public Optional<List<PropostaRestDTO>> findAllPropostasAceitesByCodEdicao (int codEdicao) {

        PropostaRestDTO propo1 = new PropostaRestDTO(1, "Desenvolvimento Software Têxtil", "ACEITE");
        PropostaRestDTO propo2 = new PropostaRestDTO(2, "Novo Canal de Comunicação On-site Messaging", "ACEITE");
        List<PropostaRestDTO> list = new ArrayList<>();
        list.add(propo1);
        list.add(propo2);
        return Optional.of(list);

        /*try {
            Mono<List<PropostaRestDTO>> response = webClient.get()
                    .uri("/propostas/edicao/" +codEdicao)
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
*/
    }
    public Optional<PropostaRestDTO> findById(int codProposta) {

        PropostaRestDTO prop = new PropostaRestDTO(1, 1, "Joana", "Vilas Boas", 500000025, 1, "Titulo desta proposta", "Problema desta propostas", "Objectivo detsa proposta", "PENDENTE");
        return Optional.of(prop);
       /* try {
            Mono<PropostaRestDTO> response = webClient
                    .get()
                    .uri("/propostas/" + codProposta)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(PropostaRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            PropostaRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }
*/
    }


    public boolean updateEstadoProposta (PropostaRestDTO propostaParcial) throws Exception {

        ResponseEntity<String> result = null;
        int codProposta = propostaParcial.getCodProposta();
        try {
            result= webClient
                    .patch()
                    .uri("/propostas/" + codProposta)
                    .body(Mono.just(propostaParcial), PropostaRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(propostaParcial.toString())))
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
}
