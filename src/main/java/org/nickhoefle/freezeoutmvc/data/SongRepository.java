package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    Optional<Song> findBySongName(String songName);

}
