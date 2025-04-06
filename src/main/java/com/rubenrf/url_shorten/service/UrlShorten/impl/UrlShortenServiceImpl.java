package com.rubenrf.url_shorten.service.UrlShorten.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubenrf.url_shorten.model.UrlShorten;
import com.rubenrf.url_shorten.repository.UrlShorten.UrlShortenRepository;
import com.rubenrf.url_shorten.service.UrlShorten.UrlShortenService;

@Service
public class UrlShortenServiceImpl implements UrlShortenService {

    @Autowired
    private UrlShortenRepository urlShortenRepository;

    @Override
    public UrlShorten saveShortenUrl(String url) {
        UrlShorten urlShorten = new UrlShorten(url);
        UrlShorten urlShortenSaved = urlShortenRepository.save(urlShorten);
        return urlShortenSaved;
    }

    @Override
    public UrlShorten getOriginalUrl(String shortCode) {
        return urlShortenRepository.findByShortCode(shortCode);
    }

    @Override
    public void deleteUrl(UrlShorten urlShorten) {
        urlShortenRepository.delete(urlShorten);
    }

    @Override
    public UrlShorten updateUrl(UrlShorten urlShorten, String newUrl) {
        urlShorten.UrlShortenActualizar(newUrl);
        return urlShortenRepository.save(urlShorten);

    }

    @Override
    public void incrementAccessCount(UrlShorten urlShorten) {
        urlShorten.setAccestCount(urlShorten.getAccestCount() + 1);
        urlShortenRepository.save(urlShorten);
    }

}
