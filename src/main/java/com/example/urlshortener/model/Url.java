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




}
