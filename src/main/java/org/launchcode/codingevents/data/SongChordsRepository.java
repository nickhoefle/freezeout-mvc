package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.SongChords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongChordsRepository extends CrudRepository<SongChords, Integer> {

}
