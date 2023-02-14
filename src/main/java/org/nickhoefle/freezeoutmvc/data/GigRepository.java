package org.nickhoefle.freezeoutmvc.data;


import org.nickhoefle.freezeoutmvc.models.Gig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GigRepository extends CrudRepository<Gig, Integer> {
}
