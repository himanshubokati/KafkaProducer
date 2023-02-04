package com.kafka.producer.handler;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;

import reactor.core.publisher.Mono;

@Component
public class KafkaRouterHandler {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Value("${kafkatopic}")
	String kafkatopic;

	public Mono<ServerResponse> sendMessage(ServerRequest request) {

		return request.bodyToMono(String.class).flatMap(v -> {
			JSONObject jsonObject = new JSONObject(v);
			System.out.println(v);
			kafkaTemplate.send(kafkatopic, jsonObject.getString("message"));
			return ServerResponse.ok().bodyValue("Message Sent");
		});
	}

}
