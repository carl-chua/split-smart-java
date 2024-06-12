package com.example.split_smart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.split_smart.service.UrlService;

@RestController
@RequestMapping("/url")
public class UrlController {

  private final UrlService urlService;

  // The @Autowired annotation in Spring is used for automatic dependency
  // injection. It instructs the Spring Framework to inject an instance of a bean
  // (component or service) when it initializes a class.
  @Autowired
  public UrlController(UrlService urlService) {
    this.urlService = urlService;
  }

  @GetMapping("/get_short_url")
  public String getShortUrl(@RequestParam(value = "url", required = true) String url) {
    return urlService.getShortUrl(url);
  }

  @GetMapping("/get_original_url")
  public String getOriginalUrl(@RequestParam(value = "url", required = true) String url) {
    return urlService.getOriginalUrl(url);
  }

  @PostMapping("/shorten")
  public String shorten(@RequestParam(value = "url", required = true) String url) {
    return urlService.createShortUrl(url);
  }
}