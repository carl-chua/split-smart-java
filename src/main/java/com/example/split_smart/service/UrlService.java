package com.example.split_smart.service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.split_smart.model.Url;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

  public String getOriginalUrl(String originalUrl) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("apikey", supabaseKey);
    headers.set("Authorization", "Bearer " + supabaseKey);
    headers.set("Range", "0-9");

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    // Build the URL with the query parameter
    String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(supabaseUrl + "/rest/v1/url")
        .queryParam("original_url", "eq." + originalUrl)
        .toUriString();

    ResponseEntity<String> response = restTemplate.exchange(urlWithQueryParam, HttpMethod.GET, entity,
        String.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      Gson gson = new Gson();
      Type urlListType = new TypeToken<List<Url>>() {
      }.getType();
      List<Url> urls = gson.fromJson(response.getBody(), urlListType);
      return urls.isEmpty() ? "" : urls.get(0).getOriginalUrl();
    } else {
      return "";
    }
  }

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