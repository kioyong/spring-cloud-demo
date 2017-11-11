package com.yong.security.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * Created by LiangYong on 2017/10/15.
 */
//@Configuration
public class RoutingConfiguration {

    /**
     *  pending implement
     * **/
//    @Bean
//    public RouterFunction<ServerResponse> monoRouterFunction(TestHandler userHandler) {
//        return route(GET("/{user}").and(accept(APPLICATION_JSON)), userHandler::listUserTest);
//    }
}
