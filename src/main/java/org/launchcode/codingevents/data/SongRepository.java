package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chris Bay
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {
}
