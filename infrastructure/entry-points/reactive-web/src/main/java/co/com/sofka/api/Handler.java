package co.com.sofka.api;

import co.com.sofka.model.galleta.Galleta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Handler {

    public Mono<ServerResponse> listenGETConsultarGalletaPorCodigo(ServerRequest serverRequest) {
        String codigo = serverRequest.queryParam("codigo").orElse("001");
        return ServerResponse.ok().body(consultarGalletaPorCodigo(codigo), Galleta.class);
    }

    private Flux<Galleta> consultarGalletaPorCodigo(String codigo){

        List<Galleta> galletas = Arrays.asList(new Galleta("001", "Chocolate", "Circulo"),
                new Galleta("002", "Chocolate y mani", "Cuadrado"),
                new Galleta("003", "Vainilla", "Triangulo"),
                new Galleta("004", "Fresa", "Cuadrado"),
                new Galleta("005", "Mora", "Circulo"),
                new Galleta("006", "Arequipe", "Triangulo"),
                new Galleta("007", "Coco", "Cuadrado"),
                new Galleta("008", "Uva", "Triangulo"),
                new Galleta("009", "Limon", "Circulo"),
                new Galleta("010", "Leche condensada", "Cuadrado"));

        return Flux.fromIterable(galletas)
                .filter(galleta -> galleta.getCodigo().equals(codigo))
                .defaultIfEmpty(new Galleta("No existe", null, null))
                .onErrorResume(error -> {
                    System.out.println("Error al buscar la galleta " + error.getMessage());
                    return Mono.just(new Galleta("No existe", null, null));
                });

    }

}
