package com.example.urlshortener.model;

import jakarta.persistence.*;

@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String urlId;

    @Column
    String shortUrl;


    @Column
    String longUrl;


    @Column
    Long shortKey;

    public Url(){}

    public Url(String shortUrl, String longUrl, Long shortKey) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.shortKey = shortKey;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Long getShortKey() {
        return shortKey;
    }

    public void setShortKey(Long shortKey) {
        this.shortKey = shortKey;
    }
}
