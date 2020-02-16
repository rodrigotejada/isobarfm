package com.rtejada.isobar.fm.bands.controller;

import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rtejada.isobar.fm.bands.dto.BandDto;
import com.rtejada.isobar.fm.bands.exception.BandNotFoundException;
import com.rtejada.isobar.fm.bands.service.BandService;
import com.rtejada.isobar.fm.bands.types.FilterType;


@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @Cacheable(value = "band-all")
    @GetMapping
    public List<BandDto> findAll(@QueryParam("filter") FilterType filter){
        return bandService.getBandByName(filter);
    }

    @Cacheable(value = "band-single", key = "#name")
    @GetMapping("{name}")
    public BandDto findById(@PathVariable String name) throws BandNotFoundException {
        return bandService.getBandByName(name);
    }
}
