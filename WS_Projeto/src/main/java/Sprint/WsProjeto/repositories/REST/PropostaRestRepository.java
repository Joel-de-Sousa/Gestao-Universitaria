package Sprint.WsProjeto.repositories.REST;

import Sprint.WsProjeto.datamodel.REST.PropostaRestDTO;
import org.springframework.core.ParameterizedTypeReference;
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

    public Optional<PropostaRestDTO> findPropostaByCode(int codProposta) {

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

    public Optional<List<PropostaRestDTO>> findAllPropostasAceitesByCodEdicao (int codEdicao) {

        try {
            Mono<List<PropostaRestDTO>> response = webClient.get()
                    .uri("/propostas/edicao/aceite/" +codEdicao)
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
}
