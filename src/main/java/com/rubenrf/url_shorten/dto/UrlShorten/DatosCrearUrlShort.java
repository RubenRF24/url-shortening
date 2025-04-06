package com.rubenrf.url_shorten.dto.UrlShorten;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public record DatosCrearUrlShort(@NotBlank @URL String url) {

}
