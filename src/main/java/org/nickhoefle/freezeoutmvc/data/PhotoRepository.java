package org.nickhoefle.freezeoutmvc.data;

import org.nickhoefle.freezeoutmvc.models.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Integer> {

    List<Photo> findAllByOrderByOrderNumberAsc();
}
