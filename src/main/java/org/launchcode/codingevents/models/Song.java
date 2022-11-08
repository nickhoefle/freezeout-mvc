package org.launchcode.codingevents.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Chris Bay
 */
@Entity
public class Song extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String songName;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private SongDetails songDetails;

    public Song() {}

    public Song(String songName) {
        this.songName = songName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public SongDetails getSongDetails() {
        return songDetails;
    }

    public void setSongDetails(SongDetails songDetails) {
        this.songDetails = songDetails;
    }

    @Override
    public String toString() {
        return songName;
    }

}
