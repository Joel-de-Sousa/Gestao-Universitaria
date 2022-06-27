package org.sprint3.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.EdicaoRestDTO;
import org.sprint3.model.DTO.UcRestDTO;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8081")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();

    public Optional<EdicaoRestDTO> findByEdicaoByCodRUC(int codRUC) {

        /*EdicaoRestDTO ed = new EdicaoRestDTO(1,"POO", "Programação OO", 1,"2021-2022",2, "ATIVA");
        return Optional.of(ed);*/

        try {
            Mono<EdicaoRestDTO> response = webClient
                    .get()
                    .uri("/edicao/ruc/" + codRUC)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(EdicaoRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            EdicaoRestDTO tutorial = response.block();


            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public Optional<EdicaoRestDTO> findEdicaoByCodEstudante (int codEstudante) {

        try {
            Mono<EdicaoRestDTO> response = webClient
                    .get()
                    .uri("/edicao/estudante/" + codEstudante)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(EdicaoRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            EdicaoRestDTO tutorial = response.block();

            System.out.println(response);

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<List<EdicaoRestDTO>> getAllEdicoesByCodRUC(int codRuc) {

        /*EdicaoRestDTO ed1 = new EdicaoRestDTO(1, "POO", "2022-2023");
        EdicaoRestDTO ed2 = new EdicaoRestDTO(1, "PDS", "2022-2023");
        EdicaoRestDTO ed3 = new EdicaoRestDTO(1, "BD", "2022-2023");
        List<EdicaoRestDTO> list = new ArrayList<>();
        list.add(ed1);
        list.add(ed2);
        list.add(ed3);
        return Optional.of(list);*/
        try {
            Mono<List<EdicaoRestDTO>> response = webClient.get()
                    .uri("/edicao/listEdicaoByCodRUC/" + codRuc)
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

            List<EdicaoRestDTO> lista = response.block();
            return Optional.of(lista);
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public boolean createEdicao (EdicaoRestDTO novaEdicao) throws Exception {

       ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/edicao")
                    .body(Mono.just(novaEdicao), UcRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novaEdicao.toString())))
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

    public Optional<List<EdicaoRestDTO>> getAllEdicoes () {
        try {
            Mono<List<EdicaoRestDTO>> response = webClient.get()
                    .uri("/edicao/allargs")
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

            List<EdicaoRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }
    }
}
