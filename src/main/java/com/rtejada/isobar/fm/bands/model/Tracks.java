package com.rtejada.isobar.fm.bands.model;

import java.io.Serializable;

public class Tracks  implements Serializable {

    private Integer duration;
    private String id;
    private String name;

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
