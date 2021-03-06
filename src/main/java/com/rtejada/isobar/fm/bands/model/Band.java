package com.rtejada.isobar.fm.bands.model;

import java.io.Serializable;
import java.util.List;

public class Band  implements Serializable {

    private List<List<Album>> albumList;
    private List<String> albums;
    private String biography;
    private String genre;
    private String id;
    private String image;
    private String name;
    private Integer numPlays;


    public List<List<Album>> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(List<List<Album>> albumList) {
        this.albumList = albumList;
    }

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumPlays() {
        return numPlays;
    }

    public void setNumPlays(Integer numPlays) {
        this.numPlays = numPlays;
    }
}
