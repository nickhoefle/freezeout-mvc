package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.SongChords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongChordsRepository extends CrudRepository<SongChords, Integer> {

}
