package wsproposta.proposta.repositories.REST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;
import wsproposta.proposta.datamodel.REST.EdicaoAllArgsDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class EdicaoRestRepository {

    public Optional<EdicaoAllArgsDTO> findAllEdicaoByCodRUC(int codRUC){

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        EdicaoAllArgsDTO edicaoAllArgsDTO;
        try {
            edicaoAllArgsDTO = webClient
                    .get()
                    .uri("/edicao/listEdicaoByCodRUC/" + codRUC)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(EdicaoAllArgsDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            edicaoAllArgsDTO = null;
        }

        if( edicaoAllArgsDTO != null )
            return Optional.of(edicaoAllArgsDTO);
        else
            return Optional.empty();
    }
}
