package Sprint.WsProjeto.repositories.REST;

import Sprint.WsProjeto.datamodel.REST.UtilizadorRestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;


import java.util.Collections;
import java.util.Optional;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.http.client.HttpClient;

@Repository
public class UtilizadorRestRepository {


    public Optional<UtilizadorRestDTO> findUtilizadorByCode(int code) {

        WebClient webClient = WebClient.builder()
                .baseUrl("")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", ""))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        UtilizadorRestDTO utilizadorRestDTO;
        try {
            utilizadorRestDTO = webClient
                    .get()
                    .uri("//" + code)
                    .retrieve()


                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(UtilizadorRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            utilizadorRestDTO = null;
        }

        if( utilizadorRestDTO != null )
            return Optional.of(utilizadorRestDTO);
        else
            return Optional.empty();
    }
}
