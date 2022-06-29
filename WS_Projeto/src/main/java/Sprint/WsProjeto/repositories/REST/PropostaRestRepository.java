package Sprint.WsProjeto.repositories.REST;

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
public class PropostaRestRepository {


    public Optional<PropostaRestDTO> findPropostaByCode(int codProposta) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8090")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8090"))
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(ConnectionProvider.newConnection())))
                .build();

        PropostaRestDTO propostaRestDTO;
        try {
            propostaRestDTO = webClient
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
                    })
                    .block();
        } catch (Exception e) {

            propostaRestDTO = null;
        }

        if (propostaRestDTO != null) {
            return Optional.of(propostaRestDTO);
        } else {
            return Optional.empty();
        }
    }
}
