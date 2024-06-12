package com.example.split_smart.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public final class SupabaseUtil {

  private static String supabaseUrl;
  private static String supabaseKey;
  private static RestTemplate restTemplate = new RestTemplate();

  private SupabaseUtil() {
    // Private constructor to prevent instantiation
  }

  @Value("${SUPABASE_URL}")
  public void setSupabaseUrl(String url) {
    SupabaseUtil.supabaseUrl = url;
  }

  @Value("${SUPABASE_KEY}")
  public void setSupabaseKey(String key) {
    SupabaseUtil.supabaseKey = key;
  }

  public static ResponseEntity<String> getItem(String name, String value) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("apikey", supabaseKey);
    headers.set("Authorization", "Bearer " + supabaseKey);
    headers.set("Range", "0-9");

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    // Build the URL with the query parameter
    String urlWithQueryParam = UriComponentsBuilder.fromHttpUrl(supabaseUrl + "/rest/v1/url")
        .queryParam(name, "eq." + value)
        .toUriString();

    // Trigger the HTTP GET request to supabase
    ResponseEntity<String> response = restTemplate.exchange(urlWithQueryParam, HttpMethod.GET, entity,
        String.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      System.out.println("Response::::::::::: " + response.getBody());
      return response;
    } else {
      System.err.println("Error::::::::::: " + response.getStatusCode() + " - " + response.getBody());
      return response;
    }
  }

}