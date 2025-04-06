package com.rubenrf.url_shorten.model;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("url_short")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlShorten {

    @Id
    private String id;
    @Field(name = "url")
    private String url;
    @Field(name = "short_code")
    @Indexed(unique = true)
    private String shortCode;
    @Field(name = "created_at")
    private LocalDateTime createdAt;
    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
    @Field(name = "access_count")
    private Integer accestCount;

    public UrlShorten(String url) {
        this.url = url;
        this.shortCode = UUID.randomUUID().toString().substring(0, 7);
        this.createdAt = LocalDateTime.now().atOffset(ZoneOffset.of("-05:00")).toLocalDateTime();
        this.updatedAt = LocalDateTime.now().atOffset(ZoneOffset.of("-05:00")).toLocalDateTime();
        this.accestCount = 0;
    }

    public void UrlShortenActualizar(String url) {
        this.url = url;
        this.updatedAt = LocalDateTime.now().atOffset(ZoneOffset.of("-05:00")).toLocalDateTime();
    }

}
