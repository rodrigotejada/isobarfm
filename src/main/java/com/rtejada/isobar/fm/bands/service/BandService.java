package com.rtejada.isobar.fm.bands.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import com.rtejada.isobar.fm.bands.dto.BandDto;
import com.rtejada.isobar.fm.bands.exception.BandNotFoundException;
import com.rtejada.isobar.fm.bands.model.Album;
import com.rtejada.isobar.fm.bands.model.Band;
import com.rtejada.isobar.fm.bands.types.FilterType;

@Service
@ApplicationScope
public class BandService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Map<String, BandDto> bands;

    public Map<String, BandDto> convertBands(Band[] bands) {
        if(bands == null) {
            this.bands = new HashMap<>();
            return this.bands;
        }
        Map<String, BandDto> bandMap = new HashMap();
        for (Band band : bands) {
            BandDto bandDto = new BandDto();
            Map<String, Album> albums = new HashMap<>();
            int index = 0;
            for (String albumId: band.getAlbums()) {
                albums.put(albumId, band.getAlbumList().get(index).get(0));
                index++;
            }
            bandDto.setAlbumList(albums);
            bandDto.setBiography(band.getBiography());
            bandDto.setGenre(band.getGenre());
            bandDto.setId(band.getId());
            bandDto.setImage(band.getImage());
            bandDto.setName(band.getName());
            bandDto.setNumPlays(band.getNumPlays());
            bandMap.put(bandDto.getName(), bandDto);
        }
        this.bands = bandMap;
        return bandMap;
    }



    public List<BandDto> getBandByName(FilterType filter) {
        logger.info("accessing");
        System.out.println("getting band");
        if (filter != null) {
            switch (filter) {
                case ALPHABETICAL_ORDER:
                    return bands.values().stream()
                        .sorted(Comparator.comparing(BandDto::getName))
                        .collect(Collectors.toList());

                case POPULARITY:
                   return bands.values().stream()
                        .sorted(Comparator.comparing(BandDto::getNumPlays).reversed())
                        .collect(Collectors.toList());
            }
        }
        return new ArrayList<>(bands.values());
    }

    public BandDto getBandByName(String name) throws BandNotFoundException {
        logger.info("accessing");
        System.out.println("getting band");
        BandDto band = bands.get(name);
        if (band == null) {
            throw new BandNotFoundException(String.format("No Band found named: %s", name));
        }
        return band;
    }

}
