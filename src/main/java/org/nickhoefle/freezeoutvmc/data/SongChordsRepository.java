package org.nickhoefle.freezeoutvmc.data;

import org.nickhoefle.freezeoutvmc.models.SongChords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongChordsRepository extends CrudRepository<SongChords, Integer> {

}
