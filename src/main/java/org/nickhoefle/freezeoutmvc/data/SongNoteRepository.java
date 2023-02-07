package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.SongNote;
import org.springframework.data.repository.CrudRepository;

public interface SongNoteRepository extends CrudRepository<SongNote, Integer> {
}
