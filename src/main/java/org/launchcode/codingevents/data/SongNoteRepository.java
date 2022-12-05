package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.SongNote;
import org.springframework.data.repository.CrudRepository;

public interface SongNoteRepository extends CrudRepository<SongNote, Integer> {
}
