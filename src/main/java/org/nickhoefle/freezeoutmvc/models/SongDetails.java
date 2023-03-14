package org.nickhoefle.freezeoutmvc.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class SongDetails extends AbstractEntity {

    @NotEmpty(message = "Original Artist is required!")
    private String originalArtist;

    private String year;

    private String songKey;

    private String youtubeURL;

    public SongDetails () { }

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

    public String getYoutubeURL() {
        return youtubeURL;
    }

    public void setYoutubeURL(String youtubeURL) {
        if (youtubeURL == "") {
            this.youtubeURL = null;
            return;
        }
        String[] splitEquals = youtubeURL.split("=");
        String[] splitAndSign = splitEquals[1].split("&");
        String videoID = splitAndSign[0];
        this.youtubeURL = "<iframe width=\"560\" height=\"175\" src=\"https://www.youtube.com/embed/" + videoID.concat("\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
    }

}
