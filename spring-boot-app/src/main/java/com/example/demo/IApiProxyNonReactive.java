package com.example.demo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "non-reactive-app", url = "https://api.lib.harvard.edu/v2")
public interface IApiProxyNonReactive {
	
	@GetMapping("/collections")
    List<Book> fetchBooks();

}
