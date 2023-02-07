package org.nickhoefle.freezeoutmvc.services;

import org.nickhoefle.freezeoutmvc.data.SongRepository;
import org.nickhoefle.freezeoutmvc.models.Song;
import org.nickhoefle.freezeoutmvc.models.SongNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongNoteCollectionService {

    @Autowired
    SongRepository songRepository;

    public void removeSongNote(Song song, SongNote songNote) {
        song.removeSongNote(songNote);
        songRepository.save(song);
    }

}
