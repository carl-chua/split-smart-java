package com.example.split_smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.split_smart.service.WelcomeService;

@RestController
public class WelcomeController {

  private final WelcomeService welcomeService;

  // The @Autowired annotation in Spring is used for automatic dependency
  // injection. It instructs the Spring Framework to inject an instance of a bean
  // (component or service) when it initializes a class.
  @Autowired
  public WelcomeController(WelcomeService welcomeService) {
    this.welcomeService = welcomeService;
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
