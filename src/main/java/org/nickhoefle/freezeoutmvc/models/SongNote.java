package org.nickhoefle.freezeoutmvc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
public class SongNote extends AbstractEntity {

    @ManyToOne
    private Song song;

    @Size(min = 2, max = 999)
    private String noteText;

    private Timestamp timestamp;

    private String addedBy;

    public SongNote() { }

    public SongNote(String noteText, Timestamp timestamp) {
        this.noteText = noteText;
        this.timestamp = timestamp;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
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
                "noteText='" + noteText + '\'' +
                ", song=" + song +
                ", timestamp=" + timestamp +
                ", addedBy='" + addedBy + '\'' +
                '}';
    }
}
