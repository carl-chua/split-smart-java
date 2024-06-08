package com.example.split_smart.service;

import org.springframework.stereotype.Service;

@Service
public class WelcomeService {

  public String getWelcomeMessage() {
    return "Welcome to Split Smart!";
  }

  public String getHelloMessage(String name, int age) {
    return "Hello " + name + ", you are " + age + " years old!";
  }
}
