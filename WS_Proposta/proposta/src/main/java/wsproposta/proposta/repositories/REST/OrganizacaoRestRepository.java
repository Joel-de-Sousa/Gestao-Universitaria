package wsproposta.proposta.repositories.REST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.http.client.HttpClient;

import java.util.Collections;
import java.util.Optional;

@Repository
public class OrganizacaoRestRepository {

    public Optional<OrganizacaoRestDTO> findOrganizacaoByNifOrganizacao (int nifOrganizacao) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:8080")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        OrganizacaoRestDTO organizacaoRestDTO;
        try {
            organizacaoRestDTO = webClient
                    .get()
                    .uri("/organizacoes/" + nifOrganizacao)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(OrganizacaoRestDTO.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            organizacaoRestDTO = null;
        }

        if( organizacaoRestDTO != null )
            return Optional.of(organizacaoRestDTO);
        else
            return Optional.empty();
    }
}
