package com.sevi.cadegouline.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "track")
@Entity
public class Track implements Serializable {

    public Track() {

    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonProperty(value="ID")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Lob
    @JsonProperty(value="path_to_file")
    @Column(name = "path_to_file")
    private String pathToFile;

    @Lob
    @JsonProperty(value="title")
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "artist")
    @JsonProperty(value="artist")
    private String artist;

    @Lob
    @Column(name = "album")
    @JsonProperty(value="album")
    private String album;

    @Lob
    @Column(name = "featuring")
    @JsonProperty(value="featuring")
    private String featuring;

    @Column(name = "old_or_new", length = 3)
    @JsonProperty(value="old_or_new")
    private String oldOrNew;

    @Column(name = "counter")
    @JsonProperty(value="counter")
    private Integer counter;

    @Column(name = "is_blocked")
    @JsonProperty(value="is_blocked")
    private Boolean isBlocked;

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public String getOldOrNew() {
        return oldOrNew;
    }

    public void setOldOrNew(String oldOrNew) {
        this.oldOrNew = oldOrNew;
    }

    public String getFeaturing() {
        return featuring;
    }

    public void setFeaturing(String featuring) {
        this.featuring = featuring;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return "track = {\n\tid = " + this.getId()
                + ";\n\tpathToFile = " + this.getPathToFile()
                + ";\n\ttitle = " + this.getTitle()
                + ";\n\tartist = " + this.getArtist()
                + ";\n\talbum = " + this.getAlbum()
                + ";\n\toldOrNew = " + this.getOldOrNew()
                + ";\n\tcounter = " + this.getCounter()
                + ";\n\tisBlocked = " + this.getIsBlocked()
                + "\n}";
    }
}