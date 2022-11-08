package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Song;
import org.launchcode.codingevents.models.SongDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chris Bay
 */
@Repository
public interface SongDetailsRepository extends CrudRepository<SongDetails, Integer> {
}