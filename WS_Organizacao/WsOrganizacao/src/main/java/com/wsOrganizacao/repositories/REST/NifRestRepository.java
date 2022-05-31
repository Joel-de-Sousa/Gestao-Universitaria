package com.wsOrganizacao.repositories.REST;

import com.wsOrganizacao.datamodel.REST.NifRestDTO;
/*import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;*/
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.http.client.HttpClient;


import java.util.Collections;
import java.util.Optional;


@Repository
public class NifRestRepository {

    public Optional<NifRestDTO> findOrganizacaoByNif(int nNif) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://193.136.62.227:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://193.136.62.227:8080"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        NifRestDTO nifRestDto;
        try {
            nifRestDto = webClient
                    .get()
                    .uri("/NIFS/" + nNif) // idem configuração
                    .retrieve()

                    // uma das formas de lidar com erros
                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(NifRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            nifRestDto = null;
        }

        if( nifRestDto != null )
            return Optional.of(nifRestDto);
        else
            return Optional.empty();
    }

}
