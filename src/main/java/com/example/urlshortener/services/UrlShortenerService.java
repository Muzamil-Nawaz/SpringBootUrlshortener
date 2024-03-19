package com.example.urlshortener.services;

import com.example.urlshortener.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class UrlShortenerService {

    final static String BASE_URL = "http://muzz-urlshortener/";
    final static String DUPLICATE_KEY = "DUPP";
    Map shortenedUrls;

    UrlShortenerService(){
        shortenedUrls = new HashMap();
    }

    public Map shortenUrl(String url){
        Map responseMap = new HashMap();

        if(StringUtil.isValidStr(url)){
            responseMap.put("STATUS", "304");
            responseMap.put("DESCRIPTION","Invalid request");
        }

        if(!shortenedUrls.containsKey(url)) {
            int newHashEntry = url.hashCode();
            if(shortenedUrls.values().contains(newHashEntry)){
                newHashEntry = DUPLICATE_KEY.concat(String.valueOf(newHashEntry)).hashCode();
            }
            shortenedUrls.put(url, newHashEntry);
        }
        responseMap.put("shortUrl", BASE_URL+shortenedUrls.get(url));
        responseMap.put("longUrl",url);



        return  responseMap;
    }

    public Map deleteShortenUrl(String url){

    }
}
