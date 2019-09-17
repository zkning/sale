package com.ning.sale.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        System.out.println("服务降级...");
        return Mono.just("Auth Fallback");
    }

    @Bean
    public InDatabaseouteDefinitionRepository inDatabaseouteDefinitionRepository() {
        return new InDatabaseouteDefinitionRepository();
    }
}


@Component
class AuthorizService {

    public boolean doAuth(String sessionId) {
        System.out.println("[" + sessionId + "]授权通过");
        return true;
    }
}

/**
 * 自定义鉴权工厂
 */
@Component
class AuthorizGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthorizGatewayFilterFactory.Config> {

    @Autowired
    AuthorizService authorizService;

    public AuthorizGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(AuthorizGatewayFilterFactory.Config config) {
        return ((exchange, chain) -> {
            if (config.isValve()) {
                authorizService.doAuth(exchange.getRequest().getURI().getSchemeSpecificPart());
                return chain.filter(exchange);
            }
            ServerHttpResponse serverHttpResponse = exchange.getResponse();
            System.out.println("[" + exchange.getRequest().getURI().getSchemeSpecificPart() + "]未授权");
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return serverHttpResponse.setComplete();
        });
    }

    @Override
    public ShortcutType shortcutType() {
        return ShortcutType.DEFAULT;
    }

    public static class Config {
        private boolean valve;

        public boolean isValve() {
            return valve;
        }

        public void setValve(boolean valve) {
            this.valve = valve;
        }
    }
}

/**
 * custodatabase define
 */
class InDatabaseouteDefinitionRepository implements RouteDefinitionRepository {

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.empty();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return Mono.empty();
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.empty();
    }
}


