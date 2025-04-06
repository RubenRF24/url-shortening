package com.rubenrf.url_shorten.dto.UrlShorten;

import java.time.LocalDateTime;

public record DatosEstadisticaUrlShorten(String id, String url, String shortCode, LocalDateTime createdAt,
        LocalDateTime updatedAt, Integer accessCount) {

}
