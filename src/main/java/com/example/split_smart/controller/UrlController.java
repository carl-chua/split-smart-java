package com.example.split_smart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.split_smart.model.UrlRequest;
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

  @GetMapping("/{shortUrl}")
  public RedirectView redirect(@PathVariable("shortUrl") String shortUrl) {
    String originalUrl = urlService.getOriginalUrl(shortUrl);
    return new RedirectView(originalUrl);
  }

  @GetMapping("/get_short_url")
  public ResponseEntity<?> getShortUrl(@RequestParam(value = "url", required = true) String url) {
    String shortUrl = urlService.getShortUrl(url);
    return ResponseEntity.ok().body(Map.of("url", shortUrl));
  }

  @GetMapping("/get_original_url")
  public ResponseEntity<?> getOriginalUrl(@RequestParam(value = "url", required = true) String url) {
    String originalUrl = urlService.getOriginalUrl(url);
    return ResponseEntity.ok().body(Map.of("url", originalUrl));
  }

  @PostMapping("/shorten")
  public ResponseEntity<?> shorten(@RequestBody UrlRequest request) {
    String shortenedUrl = urlService.createShortUrl(request.getUrl());
    return ResponseEntity.ok().body(Map.of("url", shortenedUrl));
  }
}