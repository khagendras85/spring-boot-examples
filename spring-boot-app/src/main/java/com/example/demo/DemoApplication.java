package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableReactiveFeignClients
@RestController
@EnableFeignClients
public class DemoApplication {
	
	   @Autowired
	   private IApiProxy reactiveFeignClient;
	   
	   @Autowired
	   private IApiProxyNonReactive nonReactiveFeignClient;
	   
		/*
		 * @Bean public Decoder decoder() { return new JacksonDecoder(); }
		 * 
		 * @Bean public Encoder encoder() { return new JacksonEncoder(); }
		 */
	   
	   @Bean
	    @ConditionalOnMissingBean
	    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
	        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
	    }

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	 @GetMapping("/reactivecollection")
	    public Mono<List<Book>> reactive() {
	        return reactiveFeignClient.fetchBooks().map(s ->  s);
	    }
	 
	 
	 @GetMapping("/collection")
	    public List<Book> nonReactive() {
	        return nonReactiveFeignClient.fetchBooks();
	    }

}
