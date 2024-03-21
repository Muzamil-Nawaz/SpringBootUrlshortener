package com.example.urlshortener.controllers;

import com.example.urlshortener.services.UrlShortenerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/urlshortener")
public class UrlShortenerController {

    @Autowired
    UrlShortenerService urlShortenerService;

    @PostMapping("/shortenUrl")
    public Map shortenUrl(@RequestParam(name = "url") String url){
        return urlShortenerService.shortenUrl(url);
    }

    @DeleteMapping("/shortenUrl")
    public Map deleteShortenUrl(@RequestParam(name = "url") String url){
        return urlShortenerService.deleteShortenUrl(url);
    }

//    @GetMapping("/{shortUrl}")
//    public ModelAndView redirectUrl(@PathParam("shortUrl") String url){
//        return urlShortenerService.redirectUrl(url);
//    }
}
