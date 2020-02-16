package com.rtejada.isobar.fm.bands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BandsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BandsApplication.class, args);
    }
}
