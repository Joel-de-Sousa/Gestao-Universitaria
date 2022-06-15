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
import wsproposta.proposta.datamodel.REST.OrganizacaoRestDTO;
import wsproposta.proposta.datamodel.REST.ProjetoRestDto;

import java.util.Collections;
import java.util.Optional;

@Repository
public class ProjetoRestRepository {

    public Optional<ProjetoRestDto> createAndSaveProjeto (ProjetoRestDto projetoRestDto) {

        WebClient webClient = WebClient.builder()
                .baseUrl("http://193.136.62.227:8085")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://193.136.62.227:8085"))
                .clientConnector( new ReactorClientHttpConnector( HttpClient.create(ConnectionProvider.newConnection())) )
                .build();

        ProjetoRestDto projetoRestDto1;
        try {
           projetoRestDto1 = webClient
                    .post()
                    .uri("/projetos/" + projetoRestDto)
                    .retrieve()

                    .onStatus(HttpStatus::is4xxClientError, error -> { return Mono.empty(); })

                    .bodyToMono(ProjetoRestDto.class)

                    .onErrorReturn( null )

                    .doOnError(throwable -> { System.out.println( throwable.getMessage() );} )
                    .block();
        }
        catch( Exception e) {

            projetoRestDto1 = null;
        }

        if( projetoRestDto1 != null )
            return Optional.of(projetoRestDto1);
        else
            return Optional.empty();
    }
}
