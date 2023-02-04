package com.kafka.producer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kafka.producer.handler.KafkaRouterHandler;

@Configuration
public class WebConfig {

	@Autowired
	KafkaRouterHandler handler;

	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions.route()
				.POST("/api/send",request-> handler.sendMessage(request))
				.build();
	}

}
