package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.SongDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongDetailsRepository extends CrudRepository<SongDetails, Integer> {
}