package WSEdicao.repositories.REST;

import WSEdicao.datamodel.REST.UtilizadorRestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Optional;
import java.util.Collections;

@Repository
public class UtilizadorRestRepository {

    public Optional<UtilizadorRestDTO> findUtilizadorByCodUtilizador(int codUtilizador){

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8083")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8083"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        UtilizadorRestDTO utilizadorRestDTO;
        try {
            utilizadorRestDTO = webClient
                    .get()
                    .uri("/utilizadores/" + codUtilizador)
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
            return java.util.Optional.of(utilizadorRestDTO);
        else
            return java.util.Optional.empty();
    }
}
