package com.rubenrf.url_shorten.service.UrlShorten;

import com.rubenrf.url_shorten.model.UrlShorten;

public interface UrlShortenService {

    UrlShorten saveShortenUrl(String url);

    UrlShorten getOriginalUrl(String shortCode);

    void deleteUrl(UrlShorten urlShorten);

    UrlShorten updateUrl(UrlShorten urlShorten, String newUrl);

    void incrementAccessCount(UrlShorten urlShorten);

}
