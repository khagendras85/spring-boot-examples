package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "web-flux-app", url = "https://api.lib.harvard.edu/v2")
public interface IApiProxy {
	
	@GetMapping("/collections")
    Mono<List<Book>> fetchBooks();

}
