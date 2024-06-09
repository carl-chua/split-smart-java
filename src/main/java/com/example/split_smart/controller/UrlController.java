package com.example.split_smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.split_smart.service.UrlService;

@RestController
public class UrlController {

  private final UrlService urlService;

  // The @Autowired annotation in Spring is used for automatic dependency
  // injection. It instructs the Spring Framework to inject an instance of a bean
  // (component or service) when it initializes a class.
  @Autowired
  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }

  @GetMapping("/abc")
  public String abc() {
    return "abc";
  }

  @PostMapping("/shorten")
  public String shorten(@RequestParam(value = "url", required = true) String url) {
    return urlService.createShortUrl(url);
  }
}