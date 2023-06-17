package com.microservice.gateway.filters.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class EjemploGatewayFilterFactory extends AbstractGatewayFilterFactory<EjemploGatewayFilterFactory.Configuracion> {
    private final Logger logger = LoggerFactory.getLogger(EjemploGatewayFilterFactory.class);
     public EjemploGatewayFilterFactory(){
         super(Configuracion.class);
     }

    @Override
    public GatewayFilter apply(EjemploGatewayFilterFactory.Configuracion config) {
        return ((exchange, chain) -> {
            logger.info("Ejecutando prefiltro gateway factory" + config.message);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                Optional.ofNullable(config.cookieValue).ifPresent(cokie ->{
                    exchange.getResponse().addCookie(ResponseCookie.from(config.cookieName, cokie).build());
                });
                logger.info("Ejecutando filtro post gateway factory" + config.message);
            }));
        });
    }


    public class Configuracion{
         private String message;

        private String cookieName;
         private String cookieValue;


         public String getMessage() {
             return message;
         }

         public void setMessage(String message) {
             this.message = message;
         }

        public String getCookieName() {
            return cookieName;
        }

        public void setCookieName(String cookieName) {
            this.cookieName = cookieName;
        }

         public String getCookieValue() {
             return cookieValue;
         }

         public void setCookieValue(String cookieValue) {
             this.cookieValue = cookieValue;
         }


     }

}
