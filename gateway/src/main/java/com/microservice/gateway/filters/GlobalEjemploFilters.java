package com.microservice.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GlobalEjemploFilters implements GlobalFilter , Ordered {
    private Logger logger =LoggerFactory.getLogger(GlobalEjemploFilters.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Ejecucion previo a ejecutar elñ request");
        exchange.getRequest().mutate().headers(h -> h.add("token","probandofiltersRequest"));

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            logger.info("ejecutando filtro póst");
            Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor ->{
                exchange.getResponse().getHeaders().add("token", valor);
            });

                exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
            }));

    }


    @Override
    public int getOrder() {
        return 1;
    }
}
