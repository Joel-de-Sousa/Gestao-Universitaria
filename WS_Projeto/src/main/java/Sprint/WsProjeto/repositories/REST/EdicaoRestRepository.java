package Sprint.WsProjeto.repositories.REST;

import Sprint.WsProjeto.datamodel.REST.EdicaoRestDTO;
import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Collections;
import java.util.Optional;

@Repository
public class EdicaoRestRepository {


    public Optional<EdicaoRestDTO> findEdicaoByCode(int codEdicao) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8081")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8081"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        EdicaoRestDTO edicaoRestDTO;
        try {
            edicaoRestDTO = webClient
                    .get()
                    .uri("/edicao/allargs/" + codEdicao)
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
}
