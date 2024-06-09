package com.example.split_smart.model;

public class Url {

  private String original_url;
  private String short_url;

  public Url(String originalUrl, String shortUrl) {
    this.original_url = originalUrl;
    this.short_url = shortUrl;
  }

  public String getOriginalUrl() {
    return this.original_url;
  }

  public String getShortUrl() {
    return this.short_url;
  }

  public void setOriginalUrl(String originalUrl) {
    this.original_url = originalUrl;
  }

  public void setShortUrl(String shortUrl) {
    this.short_url = shortUrl;
  }
}