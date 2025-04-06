package com.rubenrf.url_shorten;

import org.springframework.boot.SpringApplication;

public class TestUrlShortenApplication {

	public static void main(String[] args) {
		SpringApplication.from(UrlShortenApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
