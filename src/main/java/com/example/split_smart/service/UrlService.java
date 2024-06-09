package com.example.split_smart.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.split_smart.model.Url;
import com.google.gson.Gson;

@Service
public class UrlService {

  @Value("${SUPABASE_URL}")
  private String supabaseUrl;

  @Value("${SUPABASE_KEY}")
  private String supabaseKey;

  private static final int NUM_CHARS_SHORT_LINK = 7;

  private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

  private Random random = new Random();

  private RestTemplate restTemplate = new RestTemplate();

  public String createShortUrl(String originalUrl) {
    String shortUrl = generateRandomShortUrl();
    saveUrl(shortUrl, originalUrl);
    return shortUrl;
  }

  private String generateRandomShortUrl() {
    char[] result = new char[NUM_CHARS_SHORT_LINK];
    for (int i = 0; i < NUM_CHARS_SHORT_LINK; i++) {
      // get a random character from ALPHABET
      int randomIndex = random.nextInt(ALPHABET.length() - 1);
      result[i] = ALPHABET.charAt(randomIndex);
    }
    String shortLink = new String(result);
    return shortLink;
  }

  private String saveUrl(String shortUrl, String originalUrl) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("apikey", supabaseKey);
    headers.set("Authorization", "Bearer " + supabaseKey);
    headers.set("Content-Type", "application/json");
    headers.set("Prefer", "return=minimal");

    Url url = new Url(originalUrl, shortUrl);
    Gson gson = new Gson();
    // convert object to JSON string
    String jsonString = gson.toJson(url);
    HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

    ResponseEntity<String> response = restTemplate.exchange(supabaseUrl + "/rest/v1/url", HttpMethod.POST, entity,
        String.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      return shortUrl;
    } else {
      return "";
    }
  }

}