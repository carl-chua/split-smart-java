package com.example.split_smart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.split_smart.service.WelcomeService;

@SpringBootApplication
@RestController
public class SplitSmartApplication {

	private final WelcomeService welcomeService;

	// The @Autowired annotation in Spring is used for automatic dependency
	// injection. It instructs the Spring Framework to inject an instance of a bean
	// (component or service) when it initializes a class.
	@Autowired
	public SplitSmartApplication(WelcomeService welcomeService) {
		this.welcomeService = welcomeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SplitSmartApplication.class, args);
	}

	@GetMapping("/")
	public String welcome() {
		return welcomeService.getWelcomeMessage();
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", required = false, defaultValue = "bro") String name,
			@RequestParam(value = "age", required = false, defaultValue = "20") int age) {
		return welcomeService.getHelloMessage(name, age);
	}
}