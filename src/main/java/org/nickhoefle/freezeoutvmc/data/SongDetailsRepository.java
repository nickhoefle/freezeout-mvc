package org.nickhoefle.freezeoutvmc.data;

import org.nickhoefle.freezeoutvmc.models.SongDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Chris Bay
 */
@Repository
public interface SongDetailsRepository extends CrudRepository<SongDetails, Integer> {
}