package com.example.split_smart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
// The @RestController annotation tells Spring that this code describes an
// endpoint that should be made available over the web.
public class SplitSmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitSmartApplication.class, args);
	}

	@GetMapping("/")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/hello")
	public String helloWithName(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
}
