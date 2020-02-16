package com.rtejada.isobar.fm.bands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import com.rtejada.isobar.fm.bands.dto.BandDto;
import com.rtejada.isobar.fm.bands.exception.BandNotFoundException;
import com.rtejada.isobar.fm.bands.model.Album;
import com.rtejada.isobar.fm.bands.model.Band;
import com.rtejada.isobar.fm.bands.model.Tracks;
import com.rtejada.isobar.fm.bands.service.BandService;
import com.rtejada.isobar.fm.bands.types.FilterType;

@SpringBootTest
class BandServiceTest {

    @InjectMocks
    @Spy
    private BandService bandService;

    @Test
    public void shouldConvertBandsSuccessfully() {
        Band[] bands = buildBands();
        bandService.convertBands(bands);
        FilterType filterType = null;
        List<BandDto> bandsList = bandService.getBandByName(filterType);
        assertEquals(bands[0].getName(),bandsList.get(0).getName());
        assertEquals(bands[0].getId(),bandsList.get(0).getId());
        assertEquals(bands[0].getAlbumList().size(), bandsList.get(0).getAlbumList().size());
    }

    @Test
    public void shouldConvertBandsSuccessfullyWhenNoBandsFound() {
        bandService.convertBands(null);
        FilterType filterType = null;
        List<BandDto> bandsList = bandService.getBandByName(filterType);
        assertTrue(bandsList.isEmpty());
    }

    @Test
    public void shouldOrdenateBandsByAlphabeticalOrder() {
        Band[] bands = buildBands();
        bandService.convertBands(bands);
        List<BandDto> bandsList = bandService.getBandByName(FilterType.ALPHABETICAL_ORDER);
        assertEquals("AnyBand",bandsList.get(0).getName());
        assertEquals("New Band",bandsList.get(1).getName());
    }

    @Test
    public void shouldOrdenateBandsByNumPlays() {
        Band[] bands = buildBands();
        bandService.convertBands(bands);
        List<BandDto> bandsList = bandService.getBandByName(FilterType.POPULARITY);
        assertEquals(new Integer(100), bandsList.get(0).getNumPlays());
        assertEquals(new Integer(10), bandsList.get(1).getNumPlays());
    }

    @Test
    public void shouldGetBandByName() throws BandNotFoundException {
        Band[] bands = buildBands();
        bandService.convertBands(bands);
        BandDto band = bandService.getBandByName("AnyBand");
        assertEquals("AnyBand", band.getName());
    }

    private Band[] buildBands() {
        Band[] bands = new Band[2];
        Band band = new Band();
        band.setId("id");
        band.setBiography("new band");
        band.setGenre("rock");
        band.setImage("www.image.com");
        band.setNumPlays(10);
        band.setName("New Band");

        List<List<Album>> albumList = new ArrayList<>();
        albumList.add(buildAlbums());
        band.setAlbumList(albumList);

        List<String> albumsIds = new ArrayList<>();
        albumsIds.add("1");
        band.setAlbums(albumsIds);

        Band band2 = new Band();
        band2.setId("id");
        band2.setBiography("new band");
        band2.setGenre("rock");
        band2.setImage("www.image.com");
        band2.setNumPlays(100);
        band2.setName("AnyBand");

        List<List<Album>> albumList2 = new ArrayList<>();
        albumList2.add(buildAlbums());
        band2.setAlbumList(albumList);

        List<String> albumsIds2 = new ArrayList<>();
        albumsIds2.add("1");
        band2.setAlbums(albumsIds);

        bands[0] = band;
        bands[1] = band2;
        return bands;
    }

    private List<Album> buildAlbums() {
        List<Album> albums = new ArrayList<>();
        Album album = new Album();
        album.setBand("band");
        album.setId("1");
        album.setImage("www.image.com");
        album.setName("name");
        album.setReleaseDate(new Date());
        album.setTracks(buildTracks());
        albums.add(album);
        return albums;
    }

    private List<Tracks> buildTracks() {
        List<Tracks> tracks = new ArrayList<>();
        Tracks track = new Tracks();
        track.setId("a");
        track.setName("track");
        track.setDuration(100);
        tracks.add(track);
        return tracks;
    }

}
