package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

@Entity
public class SongChords extends AbstractEntity {

    @Column(length = 9999)
    private String chordsText;

    @ManyToOne
    private Song song;

    private Timestamp timestamp;

    private String addedBy;

    public SongChords() {}

    public SongChords(String chordsText, Timestamp timestamp) {
        this.chordsText = chordsText;
        this.timestamp = timestamp;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getChordsText() {
        return chordsText;
    }

    public void setChordsText(String chordsText) {
        this.chordsText = chordsText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    @Override
    public String toString() {
        return "SongNote{" +
                "noteText='" + chordsText + '\'' +
                ", song=" + song +
                ", timestamp=" + timestamp +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }
}
