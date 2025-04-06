package com.rubenrf.url_shorten.repository.UrlShorten;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rubenrf.url_shorten.model.UrlShorten;

@Repository
public interface UrlShortenRepository extends MongoRepository<UrlShorten, String> {

    UrlShorten findByShortCode(String shortCode);

}
