package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {

    Optional<Song> findBySongName(String songName);

}
