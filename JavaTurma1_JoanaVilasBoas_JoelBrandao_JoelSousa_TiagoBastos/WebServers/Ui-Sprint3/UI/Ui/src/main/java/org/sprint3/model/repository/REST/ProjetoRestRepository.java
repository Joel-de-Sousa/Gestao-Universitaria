package org.sprint3.model.repository.REST;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.sprint3.model.DTO.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjetoRestRepository {

    WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8085")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8085"))
            .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
            .build();

    public Optional<ProjetoRestDTO> findByCodEstudante(int codEstudante) {
        ProjetoRestDTO proj = new ProjetoRestDTO(1, 1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Problema do projecto", "Objectivo do projecto", "POO: 2022-2023",1, "Joel", "Sousa", 5, "Paulo", "B", "MA1");
        return Optional.of(proj);

       /* try {
            Mono<ProjetoRestDTO> response = webClient
                    .get()
                    .uri("/projetos/" + codEstudante)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ProjetoRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            ProjetoRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }
*/
    }


    public Optional<ProjetoRestDTO> findByCodOrientador(int codOrientador) {

        ProjetoRestDTO proj = new ProjetoRestDTO(1, 1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Problema do projecto", "Objectivo do projecto","POO: 2022-2023", 5, "Joel", "Sousa", 5, "Paulo", "B", "MA1");
        return Optional.of(proj);

        /*try {
            Mono<ProjetoRestDTO> response = webClient
                    .get()
                    .uri("/projetos/" + codOrientador)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ProjetoRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            ProjetoRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }*/

    }

    public Optional<ProjetoRestDTO> findByCodProjeto(int codProjeto) {

        ProjetoRestDTO proj = new ProjetoRestDTO(1, 1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Problema do projecto", "Objectivo do projecto", "POO: 2022-2023",5, "Joel", "Sousa", 5, "Paulo", "Silva", "MA1");
        return Optional.of(proj);

        /*try {
            Mono<ProjetoRestDTO> response = webClient
                    .get()
                    .uri("/projetos/" + codProjeto)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(ProjetoRestDTO.class)
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            ProjetoRestDTO tutorial = response.block();

            return Optional.of(tutorial);
        } catch (Exception e) {
            return Optional.empty();
        }*/

    }


    public Optional<List<ProjetoRestDTO>> findAllProjetos() {

        ProjetoRestDTO proj = new ProjetoRestDTO(1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Os processos de implantação de software tradicionais tem cada vez mais vindo a revelar-se problemático.", "Identificar as diferenças existentes entre o desenvolvimento serverless e os métodos de desenvolvimento atuais.","POO: 2022-2023", "Joel", "Brandão", "MA1");
        List<ProjetoRestDTO> list = new ArrayList<>();
        list.add(proj);
        return Optional.of(list);
        /*try {
            Mono<List<ProjetoRestDTO>> response = webClient.get()
                    .uri("/projetos")
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<ProjetoRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<ProjetoRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }

    }*/

    }

    public boolean createJuri (JuriRestDTO novoJuri) throws Exception {

        return true;
       /* ResponseEntity<String> result = null;
        try {
            result= webClient
                    .post()
                    .uri("/juri")
                    .body(Mono.just(novoJuri), JuriRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(novoJuri.toString())))
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
            throw new Exception( result.getBody());*/
    }



    public Optional<List<ProjetoRestDTO>> findAllProjetosByEdicao (int codEdicao) {
        ProjetoRestDTO proj = new ProjetoRestDTO(1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Os processos de implantação de software tradicionais tem cada vez mais vindo a revelar-se problemático.", "Identificar as diferenças existentes entre o desenvolvimento serverless e os métodos de desenvolvimento atuais.","POO: 2022-2023", "Joel", "Brandão", "MA1");
        List<ProjetoRestDTO> list = new ArrayList<>();
        list.add(proj);
        return Optional.of(list);

        /*try {
            Mono<List<ProjetoRestDTO>> response = webClient.get()
                    .uri("/projetos/edicao/" +codEdicao)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<ProjetoRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<ProjetoRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }*/
    }


    public Optional<List<ProjetoRestDTO>> findAllProjetosByCodOrientador (int codOrientador) {
        ProjetoRestDTO proj = new ProjetoRestDTO(1, "Desenvolvimento Serverless: Soluções, Impacto e Futuro", "Os processos de implantação de software tradicionais tem cada vez mais vindo a revelar-se problemático.", "Identificar as diferenças existentes entre o desenvolvimento serverless e os métodos de desenvolvimento atuais.","POO: 2022-2023", "Joel", "Brandão", "MA1");
        List<ProjetoRestDTO> list = new ArrayList<>();
        list.add(proj);
        return Optional.of(list);

       /* try {
            Mono<List<ProjetoRestDTO>> response = webClient.get()
                    .uri("/projetos/edicao/" + codOrientador)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, error -> {
                        return Mono.empty();
                    })
                    .bodyToMono(new ParameterizedTypeReference<List<ProjetoRestDTO>>() {
                    })
                    .onErrorReturn(null)
                    .doOnError(throwable -> {
                        System.out.println(throwable.getMessage());
                    });

            List<ProjetoRestDTO> lista = response.block();
            return Optional.of(lista);
        }catch( Exception e) {
            return Optional.empty();
        }*/
    }

    public boolean updateEstadoSubmissao (SubmissaoRestDTO submissaoParcial) throws Exception {
        return true;

       /* ResponseEntity<String> result = null;
        int codSubmissao = submissaoParcial.getCodSubmissao();
        try {
            result= webClient
                    .patch()
                    .uri("/submissoes/estado/" + codSubmissao)
                    .body(Mono.just(submissaoParcial), SubmissaoRestDTO.class).exchange().flatMap(response -> response.toEntity(String.class))
                    .onErrorReturn(ResponseEntity.of(Optional.of(submissaoParcial.toString())))
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
            throw new Exception( result.getBody());*/
    }

}
