package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableReactiveFeignClients
@RestController
public class DemoApplication {
	
	   @Autowired
	   private IApiProxy reactiveFeignClient;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	 @GetMapping("/greetingReactive")
	    public Mono<List<Book>> greetingReactive() {
	        return reactiveFeignClient.greeting().map(s ->  s);
	    }

}
