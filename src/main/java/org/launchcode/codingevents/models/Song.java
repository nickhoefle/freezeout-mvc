package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Song extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String songName;

    private String songUploadFileName;

    private String songSheetMusic;

    @OneToMany
    @JoinColumn(name = "song_id")
    private List<SongNote> songNotes;

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

    public String getSongUploadFileName() {
        return songUploadFileName;
    }

    public void setSongUploadFileName(String songUploadFileName) {
        this.songUploadFileName = songUploadFileName;
    }

    public SongDetails getSongDetails() {
        return songDetails;
    }

    public void setSongDetails(SongDetails songDetails) {
        this.songDetails = songDetails;
    }

    public String getSongSheetMusic() {
        return songSheetMusic;
    }

    public void setSongSheetMusic(String songSheetMusic) {
        this.songSheetMusic = songSheetMusic;
    }

    public List<SongNote> getSongNotes() {
        return songNotes;
    }

    public void setSongNotes(List<SongNote> songNotes) {
        this.songNotes = songNotes;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName +
                ", songUploadFileName='" + songUploadFileName + '\'' +
                ", songSheetMusic='" + songSheetMusic + '\'' +
                ", songNotes=" + songNotes +
                ", songDetails=" + songDetails +
                '}';
    }
}