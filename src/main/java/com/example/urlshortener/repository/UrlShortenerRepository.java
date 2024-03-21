package com.example.urlshortener.repository;

import com.example.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlShortenerRepository extends JpaRepository<Url, String> {

    public Boolean existsByLongUrl(String longUrl);

    public Boolean existsByShortUrl(String shortUrl);

    public Url findByShortUrl(String shortUrl);


    public Url findByLongUrl(String longUrl);



    public Url findByShortKey(Long shortKey);




    public Boolean existsByShortKey(Long shortKey);
}
