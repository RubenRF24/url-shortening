package com.rubenrf.url_shorten.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rubenrf.url_shorten.dto.UrlShorten.DatosCrearUrlShort;
import com.rubenrf.url_shorten.dto.UrlShorten.DatosEstadisticaUrlShorten;
import com.rubenrf.url_shorten.dto.UrlShorten.DatosResponseUrlShort;
import com.rubenrf.url_shorten.model.UrlShorten;
import com.rubenrf.url_shorten.service.UrlShorten.UrlShortenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/shorten")
public class ShortenController {

    @Autowired
    private UrlShortenService urlShortenService;

    @PostMapping
    public ResponseEntity<?> saveUrl(@RequestBody @Valid DatosCrearUrlShort datosCrearUrlShort,
            UriComponentsBuilder uriComponentsBuilder) {

        UrlShorten urlSaved = urlShortenService.saveShortenUrl(datosCrearUrlShort.url());
        URI path = uriComponentsBuilder.path("/shorten/{shortCode}").buildAndExpand(urlSaved.getShortCode()).toUri();

        return ResponseEntity.created(path).body(new DatosResponseUrlShort(urlSaved.getId(), urlSaved.getUrl(),
                urlSaved.getShortCode(), urlSaved.getCreatedAt(), urlSaved.getUpdatedAt()));
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<?> getUrl(@PathVariable String shortCode) {

        UrlShorten urlShorten = urlShortenService.getOriginalUrl(shortCode);
        if (urlShorten == null) {
            return ResponseEntity.notFound().build();
        }

        urlShortenService.incrementAccessCount(urlShorten);

        return ResponseEntity.ok().body(new DatosResponseUrlShort(urlShorten.getId(), urlShorten.getUrl(),
                urlShorten.getShortCode(), urlShorten.getCreatedAt(), urlShorten.getUpdatedAt()));
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<?> updateUrl(@PathVariable String shortCode,
            @RequestBody @Valid DatosCrearUrlShort datosCrearUrlShort) {

        UrlShorten urlShorten = urlShortenService.getOriginalUrl(shortCode);
        if (urlShorten == null) {
            return ResponseEntity.notFound().build();
        }
        UrlShorten updatedUrlShorten = urlShortenService.updateUrl(urlShorten, datosCrearUrlShort.url());

        return ResponseEntity.ok().body(new DatosResponseUrlShort(updatedUrlShorten.getId(), updatedUrlShorten.getUrl(),
                updatedUrlShorten.getShortCode(), updatedUrlShorten.getCreatedAt(), updatedUrlShorten.getUpdatedAt()));
    }

    @DeleteMapping("/{shortCode}")
    public ResponseEntity<?> deleteUrl(@PathVariable String shortCode) {

        UrlShorten urlShorten = urlShortenService.getOriginalUrl(shortCode);
        if (urlShorten == null) {
            return ResponseEntity.notFound().build();
        }

        urlShortenService.deleteUrl(urlShorten);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{codeShort}/stats")
    public ResponseEntity<?> getStats(@PathVariable String codeShort) {
        UrlShorten urlShorten = urlShortenService.getOriginalUrl(codeShort);

        if (urlShorten == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new DatosEstadisticaUrlShorten(urlShorten.getId(), urlShorten.getUrl(),
                urlShorten.getShortCode(), urlShorten.getCreatedAt(), urlShorten.getUpdatedAt(),
                urlShorten.getAccestCount()));
    }

}
