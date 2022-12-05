package org.launchcode.codingevents.models;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class SongDetails extends AbstractEntity {

    @Size(min = 3, message = "Original Artist Box is Blank")
    private String originalArtist;

    @Size(max = 4, message = "Enter a valid year")
    private String year;

    private String songKey;

    private String songPath;

    public SongDetails() {}

    public SongDetails(String originalArtist, String year, String songKey) {
        this.originalArtist = originalArtist;
        this.year = year;
        this.songKey = songKey;
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
}
