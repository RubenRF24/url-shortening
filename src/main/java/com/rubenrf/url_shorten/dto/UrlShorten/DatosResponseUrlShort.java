package com.rubenrf.url_shorten.dto.UrlShorten;

import java.time.LocalDateTime;

public record DatosResponseUrlShort(String id, String url, String shortCode, LocalDateTime createdAt,
                LocalDateTime updatedAt) {

}
