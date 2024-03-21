package com.example.urlshortener.services;

import com.example.urlshortener.StringUtil;
import com.example.urlshortener.model.Url;
import com.example.urlshortener.repository.UrlShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UrlShortenerService {

    final static String BASE_URL = "http://muzz-urlshortener/";
    final static String DUPLICATE_KEY = "DUPP";

    @Autowired
    UrlShortenerRepository urlShortenerRepository;


    public Map shortenUrl(String url){
        Map responseMap = new HashMap();

        if(!StringUtil.isValidStr(url)){
            responseMap.put("STATUS", "304");
            responseMap.put("DESCRIPTION","Invalid request");
        }

        if(urlShortenerRepository.existsByLongUrl(url)) {
            Url entry = urlShortenerRepository.findByLongUrl(url);
            responseMap.put("response", entry);
        }
        else{
            long newHashEntry = url.hashCode();
            if(urlShortenerRepository.existsByShortKey(newHashEntry)){
                newHashEntry = DUPLICATE_KEY.concat(String.valueOf(newHashEntry)).hashCode();
            }
            newHashEntry = Math.abs(newHashEntry);
            String shortUrl = BASE_URL+newHashEntry;

            Url newEntry = new Url(shortUrl, url,newHashEntry);
            responseMap.put("response", urlShortenerRepository.save(newEntry));
        }
        return  responseMap;
    }

    public Map deleteShortenUrl(String url){
        Map responseMap = new HashMap();

        if(urlShortenerRepository.existsByShortUrl(url)){
            Url entry = urlShortenerRepository.findByShortUrl(url);
            urlShortenerRepository.delete(entry);
            responseMap.put("STATUS", "200");
            responseMap.put("DESCRIPTION","Short url: "+url+" deleted against "+entry.getLongUrl());
        }
        else{
            responseMap.put("STATUS", "100");
            responseMap.put("DESCRIPTION",url+" not found in the system");
        }

        return responseMap;
    }

    public Map getAllUrls() {
        Map responseMap = new HashMap();
        List<Url> urls = urlShortenerRepository.findAll();
        responseMap.put("STATUS", "200");
        responseMap.put("DESCRIPTION","Success");
        responseMap.put("DATA", urls);
        return responseMap;
    }

    public ModelAndView redirectUrl(Long shortKey) {
        String redirecturl;
        if(urlShortenerRepository.existsByShortKey(shortKey)){
           Url entry = urlShortenerRepository.findByShortKey(shortKey);
           redirecturl = entry.getLongUrl();
        }
        else{
            redirecturl = "github.com/Muzamil-Nawaz";
        }
        return new ModelAndView(new RedirectView(redirecturl));
    }
}
