package com.example.urlshortener.controllers;

import com.example.urlshortener.services.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/urlshortener")
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("/shortenUrl")
    public Map shortenUrl(@RequestParam("url") String url){
        return urlShortenerService.shortenUrl(url);
    }

    @DeleteMapping("/shortenUrl")
    public Map deleteShortenUrl(@RequestParam("url") Map requestMap){
        return urlShortenerService.shortenUrl(requestMap);
    }
}
