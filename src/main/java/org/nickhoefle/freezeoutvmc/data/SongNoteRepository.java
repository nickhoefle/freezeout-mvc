package org.nickhoefle.freezeoutvmc.data;

import org.nickhoefle.freezeoutvmc.models.SongNote;
import org.springframework.data.repository.CrudRepository;

public interface SongNoteRepository extends CrudRepository<SongNote, Integer> {
}
