package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Song extends AbstractEntity {

    @NotBlank(message = "Song name is required!")
    private String songName;

    private String fileName;

    private String songSheetMusic;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song")
    private List<SongNote> songNotes;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "song")
    private List<SongChords> songChords;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private SongDetails songDetails;

    public Song(String songName, String fileName, String songSheetMusic, List<SongNote> songNotes, List<SongChords> songChords, String status, SongDetails songDetails) {
        this.songName = songName;
        this.fileName = fileName;
        this.songSheetMusic = songSheetMusic;
        this.songNotes = songNotes;
        this.songChords = songChords;
        this.status = status;
        this.songDetails = songDetails;
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public List<SongChords> getSongChords() {
        return songChords;
    }

    public void setSongChords(List<SongChords> songChords) {
        this.songChords = songChords;
    }

    public void removeSongNote(SongNote songNote){
        this.songNotes.remove(songNote);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songName='" + songName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", songSheetMusic='" + songSheetMusic + '\'' +
                ", songNotes=" + songNotes +
                ", songChords=" + songChords +
                ", status='" + status + '\'' +
                ", songDetails=" + songDetails +
                '}';
    }
}
