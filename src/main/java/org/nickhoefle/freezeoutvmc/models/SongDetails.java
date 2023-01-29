package org.nickhoefle.freezeoutvmc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class SongDetails extends AbstractEntity {

    @Size(min = 3, message = "Original Artist Box is Blank")
    private String originalArtist;

    @Size(max = 4, message = "Enter a valid year")
    private String year;

    private String songKey;

    private String songPath;

    @Column(length = 2000)
    private String youtubeURL;

    public SongDetails() {}

    public SongDetails(String originalArtist, String year, String songKey, String youtubeURL) {
        this.originalArtist = originalArtist;
        this.year = year;
        this.songKey = songKey;
        this.youtubeURL = youtubeURL;
    }

    public String getOriginalArtist() {
        return originalArtist;
    }

    public void setOriginalArtist(String originalArtist) {
        this.originalArtist = originalArtist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSongKey() {
        return songKey;
    }

    public void setSongKey(String songKey) {
        this.songKey = songKey;
    }

    public String getSongPath() {
        return songPath;
    }

    public void setSongPath(String songPath) {
        this.songPath = songPath;
    }

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        String[] splitEquals = youtubeURL.split("=");
        String[] splitAndSign = splitEquals[1].split("&");
        String videoID = splitAndSign[0];
        this.youtubeURL = "<iframe width=\"560\" height=\"175\" src=\"https://www.youtube.com/embed/" + videoID.concat("\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
    }
}
