package com.rtejada.isobar.fm.bands.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.rtejada.isobar.fm.bands.model.Band;
import com.rtejada.isobar.fm.bands.service.BandService;

@Configuration
public class BeansConfiguration {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BandService bandService;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            logger.info("Start fetching bands");
            Band[] bands = null;
            try {
                bands = restTemplate
                    .getForObject("https://iws-recruiting-bands.herokuapp.com/api/full", Band[].class);
            } catch (Exception e) {
                bandService.convertBands(null);
            }
            bandService.convertBands(bands);
            logger.info("Done fetching bands");
        };
    }
}
