package org.nickhoefle.freezeoutvmc.services;

import org.nickhoefle.freezeoutvmc.data.SongRepository;
import org.nickhoefle.freezeoutvmc.models.Song;
import org.nickhoefle.freezeoutvmc.models.SongNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongNoteCollectionService {

    @Autowired
    SongRepository songRepository;

    public void removeSongNote(Song song, SongNote songNote) {
        song.removeSongNote(songNote);
        songRepository.save(song);
    }

}
